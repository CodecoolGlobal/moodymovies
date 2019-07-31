package com.peterpal.demo.controller;

import com.netflix.discovery.converters.Auto;
import com.peterpal.demo.model.Questionnaire;
import com.peterpal.demo.model.ScreenFun;
import com.peterpal.demo.repository.SeriesRepository;
import com.peterpal.demo.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesRepository seriesRepository;
    @Autowired
    private SeriesService seriesService;



    @GetMapping("/all")
    public List<ScreenFun> getAllScreenFun() {
        return seriesRepository.findAll();
    }

    @GetMapping("/order")
    public List<ScreenFun> getOrderByRating() {
        return seriesRepository.findByOrderByRatingAsc();
    }


    @GetMapping("/random")
    public ScreenFun getRandom() {
        List<ScreenFun> allScreenFun = seriesRepository.findAll();
        int size = allScreenFun.size();
        return allScreenFun.get(seriesService.randomPicker(size));
    }

    @GetMapping("/questionnaire")
    public List<ScreenFun> getPersonal(@RequestBody Questionnaire questionnaire) {
        List<ScreenFun> chooseFor = seriesRepository.findAll();
        if (questionnaire.getMasochist() == 1){
            chooseFor = seriesRepository.findByOrderByRatingAsc();
            return chooseFor.subList(0, 2);
        }

        return chooseFor;
    }
}
