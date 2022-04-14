package com.example.practice_2022_04_08.controller.admin;

import com.example.practice_2022_04_08.domain.Point;
import com.example.practice_2022_04_08.domain.UserLogin;
import com.example.practice_2022_04_08.domain.dto.FinalPointDTO;
import com.example.practice_2022_04_08.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;

@Controller
@RequestMapping("/admin/studentPointManagement")
public class StudentPointManagementController {
    private final RegisteredSubjectRepository registeredSubjectRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final PointRepository pointRepository;
    private final AdministratorRepository administratorRepository;


    public StudentPointManagementController(RegisteredSubjectRepository registeredSubjectRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, PointRepository pointRepository, AdministratorRepository administratorRepository) {
        this.registeredSubjectRepository = registeredSubjectRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.pointRepository = pointRepository;
        this.administratorRepository = administratorRepository;
    }

    @GetMapping
    public String showDashboard(@RequestParam(name = "subjectId", required = false, defaultValue = "0") Long subjectId,
                                @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                Model model) {
        Page<Point> studentPoints = null;
        if (subjectId == 0) { // kiểm tra id môn học nếu tồn tại hay không
            studentPoints = Page.empty();
        } else {
            model.addAttribute("subject_Id", subjectId); // lưu lại id môn học vào request
            studentPoints = pointRepository.getTotalPointOfStudentsBySubject(subjectId, PageRequest.of(page, 30)); // lấy thông tin điểm của sinh viên theo môn học
        }

        model.addAttribute("subjects", this.subjectRepository.findAll()); // set attribute toàn bộ môn học lên request
        model.addAttribute("studentPoints", studentPoints); //  set attribure danh sách thông tin điểm sinh viên đăng ký môn học
        return "admin/StudentPointManagement";
    }

    @PostMapping("setpoint") // lên điểm cho student
    public String setPoint(Point point,
                           @RequestParam String redirect, RedirectAttributes redirectAttributes,
                           @SessionAttribute("adminstratorLogin") UserLogin userLogin) {
        String toastType = "error";
        Point original = pointRepository.findById(point.getId()).orElse(null); // kiểm tra nếu id điểm  có tồn tại hay không
        if (original != null) { // nếu tồn tại thì thực hiện set điểm
            original.setPoint(point.getPoint()); // set điểm
            original.setPointType(point.getPointType()); // set loại điểm
            original.setPointDate(Calendar.getInstance().getTime()); // set time lên điểm
            original.setAdminstrator(this.administratorRepository.getById(userLogin.getId())); // set người thực hiện hiện tại
            this.pointRepository.save(original); // lưu lên database
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Point updated successfully");
        } else
            redirectAttributes.addFlashAttribute("message", "Not found point");
        redirectAttributes.addFlashAttribute("toastType", toastType);
        return "redirect:".concat(redirect);
    }

    @GetMapping("export") // export thông in điểm của toàn bộ sinh viên theo từng môn học ra file csv
    public void export(@RequestParam("subjectId") Long id,
                       HttpServletResponse response) throws IOException {
        pointRepository.getTotalPointOfStudentsBySubject(id, PageRequest.of(0, 90000)); // lấy toàn bộ thông tin điểm của sinh viên theo môn học

        response.setContentType("text/csv");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=POINTS_SUBJECT_" + id + ".csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"SUBJECT ID", "SUBJECT NAME", "STUDENT ID", "STUDENT NAME", "POINT TYPE", "POINT", "POINT DATE"};
        String[] nameMapping = {"subjectId", "subjectName", "studentId", "studentName", "pointType", "point", "pointDate"};

        csvWriter.writeHeader(csvHeader);
        pointRepository.getTotalPointOfStudentsBySubject(id, PageRequest.of(0, 90000)).forEach(point -> {
            FinalPointDTO dto = FinalPointDTO.toFinalPointDTO(point);
            dto.setStudentId(point.getRegisteredSubject().getStudent().getId());
            dto.setStudentName(point.getRegisteredSubject().getStudent().getStudentName());
            try {
                csvWriter.write(dto, nameMapping);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        csvWriter.close();
    }
}
