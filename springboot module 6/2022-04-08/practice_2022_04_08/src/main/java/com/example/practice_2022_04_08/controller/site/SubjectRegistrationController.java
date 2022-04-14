package com.example.practice_2022_04_08.controller.site;

import com.example.practice_2022_04_08.domain.AvailSubject;
import com.example.practice_2022_04_08.domain.Point;
import com.example.practice_2022_04_08.domain.RegisteredSubject;
import com.example.practice_2022_04_08.domain.UserLogin;
import com.example.practice_2022_04_08.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;

@Controller
@RequestMapping("/subjects/registration")
public class SubjectRegistrationController {

    private final AvailableSubjectRepository availableSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;
    private final RegisteredSubjectRepository registeredSubjectRepository;
    private final AdministratorRepository administratorRepository;
    private final PointRepository pointRepository;

    public SubjectRegistrationController(AvailableSubjectRepository availableSubjectRepository, SubjectRepository subjectRepository, StudentRepository studentRepository, RegisteredSubjectRepository registeredSubjectRepository, AdministratorRepository administratorRepository, PointRepository pointRepository) {
        this.availableSubjectRepository = availableSubjectRepository;
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
        this.registeredSubjectRepository = registeredSubjectRepository;
        this.administratorRepository = administratorRepository;
        this.pointRepository = pointRepository;
    }

    @GetMapping
    public String showRegistrationPage(Model model,
                                       @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                       @SessionAttribute("studentLogin") UserLogin userLogin) {
        // get toàn bộ thông tin môn học đăng ký mà student chưa đăng ký
        model.addAttribute("availSubjects", this.availableSubjectRepository.findTotalUnRegisteredAvailSubjectForStudent(userLogin.getId(), PageRequest.of(page, 30)));
        return "site/SubjectRegistration";
    }

    @GetMapping("register/{id}") // student đăng ký môn học
    public String registerSubject(@PathVariable(name = "id") Long availSubjectId, // id môn học đăng ký
                                  @SessionAttribute("studentLogin") UserLogin userLogin, // student sử dụng hiện tịa
                                  RedirectAttributes redirectAttributes) {
        String toastType = "error";
        AvailSubject availSubject = this.availableSubjectRepository.findById(availSubjectId).orElse(null);
        if (availSubject != null) { // kiểm tra nếu môn học tồn tại hay không
            RegisteredSubject registeredSubject = new RegisteredSubject(); // nếu môn học đăng ký tồn tại thì tạo đối tượng đăng ký môn học
            registeredSubject.setStudent(this.studentRepository.findById(userLogin.getId()).get()); // set student hiện tại đăng ký
            registeredSubject.setAvailSubject(availSubject); // set thông tin môn học đăng ký
            registeredSubject.setRegisteredTime(Calendar.getInstance().getTime()); // set thời gian đăng ký
            registeredSubject.setStatus(true); // set tình trạng đăng ký
            registeredSubject = this.registeredSubjectRepository.save(registeredSubject); // lưu đăng ký môn học lên database
            Point point = new Point(); // tạo mới điểm theo môn học đăng ký
            point.setAdminstrator(null); // set người cho điểm = null, do đây là student sử dụng lên không có admin
            point.setRegisteredSubject(registeredSubject); // set thông tin đăng ký môn học
            this.pointRepository.save(point); // lưu điểm lên database
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Subject registered successfully!");
        }
        else
            redirectAttributes.addFlashAttribute("message", "Subject not found!");
        redirectAttributes.addFlashAttribute("toastType", toastType);
        return "redirect:/subjects/registration";
    }

    @GetMapping("cancel/{id}") // hủy đăng ký môn học
    public String cancelRegisteredSubject(@PathVariable(name = "id") Long registeredSubjectId, // id của đăng ký môn học
                                          RedirectAttributes redirectAttributes) {
        String toastType = "error";
        RegisteredSubject myRegisteredSubject = this.registeredSubjectRepository.findById(registeredSubjectId).orElse(null);
        if (myRegisteredSubject != null) { // kiểm tra nếu đăng ký môn học tồn tại hay không
            myRegisteredSubject.setStatus(false); // set trạng thái false (hủy)
            this.pointRepository.deleteByRegisteredSubjectId(registeredSubjectId); // xóa điểm của đăng ký môn học, do mình hủy đăng ký nên điểm sẽ bị xóa theo
            this.registeredSubjectRepository.save(myRegisteredSubject); // lưu lại lên database
            toastType = "success";
            redirectAttributes.addFlashAttribute("message", "Subject cancelled successfully!");
        }
        else
            redirectAttributes.addFlashAttribute("message", "Subject not found!");
        redirectAttributes.addFlashAttribute("toastType", toastType);
        return "redirect:/subjects/registration/my-registration";
    }

    @GetMapping("my-registration")
    public String showMyRegistrationSubject(Model model,
                                            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                            @SessionAttribute("studentLogin") UserLogin userLogin) {
        // get toàn bộ thông tin môn học đã đăng ký
        model.addAttribute("registeredSubjects", this.registeredSubjectRepository.findAllByStudentId(userLogin.getId(), PageRequest.of(page, 30)));
        return "site/MySubjectRegistration";
    }
}
