package com.controller;

import com.dao.PersonRepositoryDataJDBC;
import com.domain.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("person")
public class HomeController {
    private final PersonRepositoryDataJDBC personRepositoryDataJDBCl;

    public HomeController(PersonRepositoryDataJDBC personRepositoryDataJDBCl) {
        this.personRepositoryDataJDBCl = personRepositoryDataJDBCl;
    }

    @GetMapping("{id}")
    @ResponseBody
    Object findById(@PathVariable Long id) {
        return this.personRepositoryDataJDBCl.findById(id);
    }

    @GetMapping("findByName/{name}")
    @ResponseBody
    Object findByName(@PathVariable String name) {
        return this.personRepositoryDataJDBCl.findByName(name);
    }

    @GetMapping
    Object findAll(@RequestParam(name ="page", defaultValue = "0", required = false)Integer page,  Model model) {
        model.addAttribute("personPage", this.personRepositoryDataJDBCl.findAll(PageRequest.of(page, 5)));
        return "person";
    }

    @PostMapping
    @ResponseBody
    Object save(Person obj) {
        return this.personRepositoryDataJDBCl.save(obj);
    }

    @GetMapping("delete/{id}")
    Object delete(@PathVariable Long id) {
        this.personRepositoryDataJDBCl.deleteById(id);
        return "redirect:/person";
    }
}
