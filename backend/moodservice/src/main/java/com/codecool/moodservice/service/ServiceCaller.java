package com.codecool.moodservice.service;

import com.codecool.moodservice.model.Questionnaire;
import com.codecool.moodservice.model.ScreenFun;
import com.codecool.moodservice.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceCaller {

    @Autowired
    private RestTemplate restTemplate;

    private String currentUrl;

    public ScreenFun getOneItem(ServiceType serviceType) {
        this.currentUrl = serviceType.baseUrl + "/random";
        ScreenFun randomItem = this.restTemplate.getForEntity(this.currentUrl, ScreenFun.class).getBody();
        return randomItem;
    }

    public List<ScreenFun> getAllItems(ServiceType serviceType) {
        this.currentUrl = serviceType.baseUrl + "/all";
        ArrayList allItems = this.restTemplate.getForEntity(this.currentUrl, ArrayList.class).getBody();
        return allItems;
    }

    public ScreenFun getItemBasedOnQuestionnaire(ServiceType serviceType, Questionnaire questionnaire) {
        this.currentUrl = serviceType.baseUrl + "/questionnaire";
        ScreenFun item = this.restTemplate.postForEntity(this.currentUrl, questionnaire, ScreenFun.class).getBody();
        return item;
    }
}
