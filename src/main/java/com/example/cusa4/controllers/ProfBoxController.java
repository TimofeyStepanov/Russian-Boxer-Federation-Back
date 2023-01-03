package com.example.cusa4.controllers;

import com.example.cusa4.exceptions.WrongDateFormatException;
import com.example.cusa4.models.FightInFightNight;
import com.example.cusa4.models.Judge;
import com.example.cusa4.models.ProfBoxEvent;
import com.example.cusa4.models.WrongFightNightIdException;
import com.example.cusa4.services.ProfBoxService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/prof")
public class ProfBoxController {
    private final ProfBoxService profBoxService;

    public ProfBoxController(ProfBoxService profBoxService) {
        this.profBoxService = profBoxService;
    }

    @GetMapping("/public/pastEvents")
    public List<ProfBoxEvent> getPastEvents() {
        return profBoxService.getPastEvents();
    }

    @GetMapping("/public/futureEvents")
    public List<ProfBoxEvent> getFutureEvents() {
        return profBoxService.getFutureEvents();
    }

    @GetMapping("/public/fights")
    public List<FightInFightNight> getFights(@RequestParam int fightNightId) {
        return profBoxService.getFights(fightNightId);
    }

    @GetMapping("/public/judges")
    public List<Judge> getJudges(@RequestParam int fightNightId) {
        return profBoxService.getJudges(fightNightId);
    }

    @GetMapping("/public/currentFights")
    public List<ProfBoxEvent> getFights(@RequestParam String dateString) {
        return profBoxService.getFights(dateString);
    }

    @GetMapping("/public/events")
    public List<ProfBoxEvent> getAllFights() {
        return profBoxService.getAllFightNights();
    }

    @DeleteMapping("/private/delete")
    public void deleteFightNight(@RequestParam int fightNightId) {
        profBoxService.deleteFightNight(fightNightId);
    }

}
