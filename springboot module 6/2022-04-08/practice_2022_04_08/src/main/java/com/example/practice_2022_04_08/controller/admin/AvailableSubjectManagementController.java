package com.example.practice_2022_04_08.controller.admin;

import com.example.practice_2022_04_08.domain.AvailSubject;
import com.example.practice_2022_04_08.domain.Subject;
import com.example.practice_2022_04_08.domain.UserLogin;
import com.example.practice_2022_04_08.repository.AdministratorRepository;
import com.example.practice_2022_04_08.repository.AvailableSubjectRepository;
import com.example.practice_2022_04_08.repository.SubjectRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.expression.Numbers;

import java.util.List;

@RequestMapping("/admin/availableSubjects")
@Controller
public class AvailableSubjectManagementController {

    private final AvailableSubjectRepository availableSubjectRepository;
    private final SubjectRepository subjectRepository;
    private final AdministratorRepository administratorRepository;


    public AvailableSubjectManagementController(AvailableSubjectRepository availableSubjectRepository, SubjectRepository subjectRepository, AdministratorRepository administratorRepository) {
        this.availableSubjectRepository = availableSubjectRepository;
        this.subjectRepository = subjectRepository;
        this.administratorRepository = administratorRepository;
    }

    @GetMapping // hiển thị toàn bộ môn học đăng ký mà student chưa đăng ký
    public String showAvailSubjects(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") int page) {
        model.addAttribute("availSubjects", this.availableSubjectRepository.findAll(PageRequest.of(page, 30)));
        return "admin/AvailableSubjectManagement";
    }

    // không dùng
//    @GetMapping("{id}")
//    @ResponseBody
//    public Object getById(@PathVariable Long id){
//        return this.availableSubjectRepository.findById(id);
//    }

    @PostMapping
    public String createNew(AvailSubject availSubject,
                            @RequestParam("subject_id") Long subjectId, RedirectAttributes redirectAttributes,
                            @SessionAttribute("adminstratorLogin")UserLogin userLogin) {
        // check nếu môn học đăng ký  đã có lịch
        AvailSubject checkSubject = this.availableSubjectRepository.findByStartDateAndStartTime(availSubject.getStartDate(), availSubject.getStartTime()).orElse(null);
        String toastType = "error";
        if (checkSubject == null) {
            availSubject.setSubject(this.subjectRepository.findById(subjectId).get()); // tạo mới môn đăng ký
            availSubject.setAdminstrator(this.administratorRepository.findById(userLogin.getId()).get()); // set người admin hiện tại thực hiện chức năng
            this.availableSubjectRepository.save(availSubject);
            toastType= "success";
            redirectAttributes.addFlashAttribute("message", "New available subject created successfully");
        }
        else
            redirectAttributes.addFlashAttribute("message", "This subject is already available at this time");
        redirectAttributes.addFlashAttribute("toastType", "success");
        return "redirect:/admin/availableSubjects";
    }

    // khong su dung
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        this.availableSubjectRepository.deleteById(id);
//        return "redirect:/admin/availableSubjects";
//    }

    // tạo attribute cho request cua view file AvailableSubjectManagement.html dòng 92
    @ModelAttribute
    public List<Subject> subjectList() {
        return this.subjectRepository.findAll();
    }

}
