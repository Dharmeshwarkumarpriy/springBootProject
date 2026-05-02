package com.hospital.controller;

import com.hospital.entity.Billing;
import com.hospital.entity.Patient;
import com.hospital.service.BillingService;
import com.hospital.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/billings")
public class BillingController {
    
    @Autowired
    private BillingService billingService;
    
    @Autowired
    private PatientService patientService;
    
    @GetMapping
    public String listBillings(Model model) {
        List<Billing> billings = billingService.getAllBillings();
        model.addAttribute("billings", billings);
        model.addAttribute("totalRevenue", billings.stream()
                .map(Billing::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return "billings/list";
    }
    
    @GetMapping("/new")
    public String newBillingForm(Model model) {
        model.addAttribute("billing", new Billing());
        model.addAttribute("patients", patientService.getAllPatients());
        return "billings/form";
    }
    
    @PostMapping
    public String saveBilling(@Valid @ModelAttribute Billing billing, Model model) {
        billing.setBillDate(LocalDateTime.now());
        billing.setBillNo("BILL-" + System.currentTimeMillis());
        BigDecimal remaining = billing.getTotalAmount().subtract(billing.getPaidAmount());
        billing.setRemainingAmount(remaining.compareTo(BigDecimal.ZERO) > 0 ? remaining : BigDecimal.ZERO);
        billing.setStatus(remaining.compareTo(BigDecimal.ZERO) > 0 ? "Pending" : "Paid");
        billingService.saveBilling(billing);
        return "redirect:/billings";
    }
    
    @GetMapping("/edit/{id}")
    public String editBillingForm(@PathVariable Long id, Model model) {
        Billing billing = billingService.getBillingById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));
        model.addAttribute("billing", billing);
        model.addAttribute("patients", patientService.getAllPatients());
        return "billings/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteBilling(@PathVariable Long id) {
        billingService.deleteBilling(id);
        return "redirect:/billings";
    }
}