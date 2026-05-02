package com.hospital.controller;

import com.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports")
public class ReportsController {
    
    @Autowired private PatientService patientService;
    @Autowired private DoctorService doctorService;
    @Autowired private AppointmentService appointmentService;
    @Autowired private BillingService billingService;
    
    @GetMapping
    public String reportsDashboard(Model model) {
        model.addAttribute("totalPatients", patientService.getAllPatients().size());
        model.addAttribute("totalDoctors", doctorService.getAllDoctors().size());
        model.addAttribute("totalAppointments", appointmentService.getAllAppointments().size());
        model.addAttribute("totalRevenue", billingService.getAllBillings().stream()
                .map(b -> b.getTotalAmount()).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        return "reports/index";
    }
    
    @GetMapping("/daily")
    public String dailyReport() {
        return "reports/daily";
    }
    
    @GetMapping("/monthly")
    public String monthlyReport() {
        return "reports/monthly";
    }
}