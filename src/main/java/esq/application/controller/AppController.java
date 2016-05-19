package esq.application.controller;

import esq.application.model.ServiceQualitySurvey;
import esq.application.model.ServiceQualitySurveyResult;
import esq.application.repository.LinguisticTermRepository;
import esq.application.repository.ServiceQualitySurveyRepository;
import esq.application.repository.ServiceQualitySurveyResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Основной контроллер приложения
 */

@Controller
public class AppController {

    @Autowired
    ServiceQualitySurveyRepository serviceQualitySurveyRepository;
    @Autowired
    ServiceQualitySurveyResultRepository serviceQualitySurveyResultRepository;
    @Autowired
    LinguisticTermRepository linguisticTermRepository;

    @RequestMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @RequestMapping(value = "/survey/list")
    public String surveyList(Model model) {
        Iterable<ServiceQualitySurvey> results = serviceQualitySurveyRepository.findAll();
        model.addAttribute("surveys", results);
        return "survey_list";
    }

}
