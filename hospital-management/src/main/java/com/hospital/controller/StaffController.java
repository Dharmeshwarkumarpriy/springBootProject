package com.hospital.controller;

import com.hospital.entity.Staff;
import com.hospital.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {
    
    @Autowired
    private StaffService staffService;
    
    @GetMapping
    public String listStaff(Model model) {
        model.addAttribute("staff", staffService.getAllStaff());
        return "staff/list";
    }
    
    @GetMapping("/new")
    public String newStaffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "staff/form";
    }
    
    @PostMapping
    public String saveStaff(@Valid @ModelAttribute Staff staff) {
        staffService.saveStaff(staff);
        return "redirect:/staff";
    }
    
    @GetMapping("/edit/{id}")
    public String editStaffForm(@PathVariable Long id, Model model) {
        Staff staff = staffService.getStaffById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        model.addAttribute("staff", staff);
        return "staff/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return "redirect:/staff";
    }
}