package com.example.tablemanagement.controller;

import com.example.tablemanagement.domain.Table;
import com.example.tablemanagement.repository.TableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class homeController {
    public final TableRepository tableRepository;

    public homeController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping("insert")
    @ResponseBody
    public String insert(@RequestParam(name = "check", required = false, defaultValue = "0") Long id) {
        List<Table> tables = new ArrayList<>();
        for (int i = 0; i < 500; ++i) {
            tables.add(new Table(null, false, null, (int) (Math.random() * 500)));
        }
        tables.forEach(System.out::println);
        if (id == 1) {
            this.tableRepository.saveAll(tables);
        }
        return null;
    }
}
