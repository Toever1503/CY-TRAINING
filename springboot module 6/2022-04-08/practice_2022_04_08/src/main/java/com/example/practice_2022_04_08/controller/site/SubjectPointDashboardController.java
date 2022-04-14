package com.example.practice_2022_04_08.controller.site;


import com.example.practice_2022_04_08.domain.Point;
import com.example.practice_2022_04_08.domain.UserLogin;
import com.example.practice_2022_04_08.domain.dto.FinalPointDTO;
import com.example.practice_2022_04_08.repository.PointRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("subjects/my-points")
@Controller
public class SubjectPointDashboardController {

    private final PointRepository pointRepository;

    public SubjectPointDashboardController(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @GetMapping
    public String showPointDashboard(Model model,
                                     @SessionAttribute("studentLogin") UserLogin userLogin,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        // get toàn bộ thông tin điểm của student
        model.addAttribute("studentPoints", pointRepository.getTotalPointOfStudents(userLogin.getId(), PageRequest.of(page, 30)));
        return "site/StudentPointDashboard";
    }

    @GetMapping("export") // export bảng điểm toàn bộ môn học của student
    public void exportPoint(@SessionAttribute("studentLogin") UserLogin userLogin,
                            HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=POINTS_" + userLogin.getFullname() + ".csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"SUBJECT ID", "SUBJECT NAME", "POINT TYPE", "POINT", "POINT DATE"};
        String[] nameMapping = {"subjectId", "subjectName", "pointType", "point", "pointDate"};

        csvWriter.writeHeader(csvHeader);
        this.pointRepository.getTotalFinalPointOfStudents(userLogin.getId()).forEach(point -> {
            try {
                csvWriter.write(FinalPointDTO.toFinalPointDTO(point), nameMapping);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        csvWriter.close();
    }


}
