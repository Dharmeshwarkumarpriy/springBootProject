package com.hospital.controller;

import com.hospital.entity.Room;
import com.hospital.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    
    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("availableRooms", roomService.getAllRooms().stream()
                .filter(r -> "Available".equals(r.getStatus())).count());
        return "rooms/list";
    }
    
    @GetMapping("/new")
    public String newRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "rooms/form";
    }
    
    @PostMapping
    public String saveRoom(@Valid @ModelAttribute Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }
    
    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        model.addAttribute("room", room);
        return "rooms/form";
    }
    
    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}