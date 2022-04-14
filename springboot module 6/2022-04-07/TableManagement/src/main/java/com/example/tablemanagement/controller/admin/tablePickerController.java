package com.example.tablemanagement.controller.admin;

import com.example.tablemanagement.domain.Order;
import com.example.tablemanagement.domain.Table;
import com.example.tablemanagement.repository.OrderRepository;
import com.example.tablemanagement.repository.StaffRepository;
import com.example.tablemanagement.repository.TableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/pick_tables")
public class tablePickerController {
    public final TableRepository tableRepository;
    public final StaffRepository staffRepository;
    public final OrderRepository orderRepository;

    public tablePickerController(TableRepository tableRepository, StaffRepository staffRepository, OrderRepository orderRepository) {
        this.tableRepository = tableRepository;
        this.staffRepository = staffRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String showTables(Model model) {
        return "admin/table/tablePick";

    }

    @ModelAttribute("tables")
    public Page<Table> getTables(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return this.tableRepository.findAll(PageRequest.of(page, 40));
    }

    @PostMapping("add")
    public String addTable(Table table, Model model) {
        this.tableRepository.save(table);
        return "admin/table/tablePick";
    }

    @GetMapping("pick/{id}")
    public String pickTable(Order order, @RequestParam("tbl_id") Long tableId, Model model) {
        Table table = this.tableRepository.findById(tableId).orElse(null);
        Date currentDate = Calendar.getInstance().getTime();
        order.setOrderTable(table);
        order.setOrderStaff(this.staffRepository.getById(1l));
        order.setTotalPrice(table.getPrice());
        order.setOrderDate(currentDate);

        if (order.getDiscount() != null)
            order.setFinalPrice(table.getPrice() - order.getDiscount());

        table.setStatus(true);
        table.setDatePicker(currentDate);
        this.tableRepository.save(table);
        this.orderRepository.save(order);
        return "admin/table/tablePick";
    }

    @GetMapping("cancel{id}")
    public String cancelTable(Table table, Model model) {
        this.tableRepository.save(table);
        return "admin/table/tablePick";
    }
}
