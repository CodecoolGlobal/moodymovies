package com.codecool.moodservice.controller;

import com.codecool.moodservice.model.Questionnaire;
import com.codecool.moodservice.model.ScreenFun;
import com.codecool.moodservice.model.ServiceType;
import com.codecool.moodservice.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/randomizer")
public class MoodController {

    @Autowired
    private MoodService moodService;

    @GetMapping("/random-choice")
    public ScreenFun getRandomItem() {
        return this.moodService.getRandomItem();
    }

    @GetMapping("/fun-type/{serviceType}")
    public List<ScreenFun> getAllFromChosenCategory(@PathVariable String serviceType) {
        return this.moodService.getAllFromChosenCategory(ServiceType.valueOf(serviceType.toUpperCase()));
    }

    @GetMapping("/one-from-each")
    public List<ScreenFun> getOneItemFromEveryType() {
        return this.moodService.getOneItemFromEveryType();
    }

    @GetMapping("/all")
    public List<ScreenFun> getAllItems() {
        return this.moodService.getAllItems();
    }

    @PostMapping("/questionnaire")
    public ScreenFun evaluateQuestionnaire(@RequestBody Questionnaire questionnaire) {
        return this.moodService.getItemBasedOnQuestionnaire(questionnaire);
    }

}
