package com.hospital.controller;

import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;
import com.hospital.service.BillingService;
import com.hospital.service.DoctorService;
import com.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    
    // All Services properly injected
    @Autowired
    private PatientService patientService;
    
    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private AppointmentService appointmentService;
    
    @Autowired
    private BillingService billingService;
    
    @GetMapping("/")
    public String dashboard(Model model) {
        try {
            // Safe stats calculation
            long totalPatients = patientService.getAllPatients().size();
            long totalDoctors = doctorService.getAllDoctors().size();
            long totalAppointments = appointmentService.getAllAppointments().size();
            
            // Safe billing calculation (null check)
            double totalRevenue = 0;
            List<?> billings = billingService.getAllBillings();
            if (billings != null) {
                totalRevenue = billings.stream()
                    .map(billing -> ((com.hospital.entity.Billing) billing).getTotalAmount().doubleValue())
                    .reduce(0.0, Double::sum);
            }
            
            // Recent appointments (safe)
            List<Appointment> recentAppointments = appointmentService.getAllAppointments().stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDate).reversed())
                .limit(5)
                .collect(Collectors.toList());
            
            // Add to model
            model.addAttribute("totalPatients", totalPatients);
            model.addAttribute("totalDoctors", totalDoctors);
            model.addAttribute("totalAppointments", totalAppointments);
            model.addAttribute("totalRevenue", totalRevenue);
            model.addAttribute("recentAppointments", recentAppointments);
            
        } catch (Exception e) {
            // Default values if any service fails
            model.addAttribute("totalPatients", 0);
            model.addAttribute("totalDoctors", 0);
            model.addAttribute("totalAppointments", 0);
            model.addAttribute("totalRevenue", 0.0);
            model.addAttribute("recentAppointments", java.util.List.of());
        }
        
        return "dashboard/index";
    }
}