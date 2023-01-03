package com.example.cusa4.controllers;

import com.example.cusa4.models.*;
import com.example.cusa4.services.BoxerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boxer")
public class BoxerController {
    private final BoxerService boxerService;

    public BoxerController(BoxerService boxerService) {
        this.boxerService = boxerService;
    }

    @GetMapping("/public/getAll")
    public List<Boxer> getBoxers() {
        return this.boxerService.getBoxers();
    }

    @GetMapping("/public/get")
    public Boxer getBoxer(@RequestParam int serviceNumber) throws WrongBoxerIdException {
        return this.boxerService.getBoxer(serviceNumber);
    }

    @GetMapping("/public/fights")
    public List<Fight> getFights(@RequestParam int serviceNumber) {
        return boxerService.getFights(serviceNumber);
    }

    @GetMapping("/public/coaches")
    public List<Coach> getCoaches(@RequestParam int serviceNumber) {
        return boxerService.getCoaches(serviceNumber);
    }

    @GetMapping("/public/titles")
    public List<Title> getTitles(@RequestParam int serviceNumber) {
        return boxerService.getTitles(serviceNumber);
    }

    @GetMapping("/public/respectedBoxers")
    public List<Boxer> getRespectedBoxers() {
        return boxerService.getRespectedBoxers();
    }
}
