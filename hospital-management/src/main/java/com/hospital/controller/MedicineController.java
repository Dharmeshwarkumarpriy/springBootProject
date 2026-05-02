package com.hospital.controller;

import com.hospital.entity.Medicine;
import com.hospital.service.MedicineService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medicines")
public class MedicineController {
    
    @Autowired
    private MedicineService medicineService;
    
    @GetMapping
    public String listMedicines(Model model) {
        model.addAttribute("medicines", medicineService.getAllMedicines());
        return "medicines/list";
    }
    
    @GetMapping("/new")
    public String newMedicineForm(Model model) {
        model.addAttribute("medicine", new Medicine());
        return "medicines/form";
    }
    
    @PostMapping
    public String saveMedicine(@Valid @ModelAttribute Medicine medicine) {
        medicineService.saveMedicine(medicine);
        return "redirect:/medicines";
    }
    
    @GetMapping("/edit/{id}")
    public String editMedicineForm(@PathVariable Long id, Model model) {
        Medicine medicine = medicineService.getMedicineById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));
        model.addAttribute("medicine", medicine);
        return "medicines/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return "redirect:/medicines";
    }
}