package com.example.cusa4.controllers;

import com.example.cusa4.exceptions.WrongOlympiadIdException;
import com.example.cusa4.models.Boxer;
import com.example.cusa4.models.OlympiadDto;
import com.example.cusa4.services.OlympiadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/olympiad")
public class OlympiadController {
    private final OlympiadService olympiadService;

    public OlympiadController(OlympiadService olympiadService) {
        this.olympiadService = olympiadService;
    }

    @GetMapping("public/currentOlympiadCrew")
    public List<Boxer> getCurrentOlympiadCrew(@RequestParam String crewPole) {
        return olympiadService.getCurrentOlympiadCrew(crewPole);
    }

    @GetMapping("public/allOlympiad")
    public List<OlympiadDto> getAllOlympiad()  {
        return olympiadService.getAllOlympiad();
    }

//    @GetMapping("public/olympiadCrew")
//    public List<Boxer> getOlympiadCrew(@RequestParam int olympiadId) throws WrongOlympiadIdException {
//        return olympiadService.getOlympiadCrew(olympiadId);
//    }
}
