package esq.application.controller;

import esq.application.model.ESQSettingsProfile;
import esq.application.model.ServiceQualitySurvey;
import esq.application.repository.ESQSettingsProfileRepository;
import esq.application.repository.LinguisticTermRepository;
import esq.application.repository.ServiceQualitySurveyRepository;
import esq.core.ESQCalculator;
import esq.core.ESQSurveyResultGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Основной контроллер приложения
 */

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    @Autowired
    private ServiceQualitySurveyRepository serviceQualitySurveyRepository;
    @Autowired
    private ESQSettingsProfileRepository settingsProfileRepository;
    @Autowired
    private LinguisticTermRepository linguisticTermRepository;
    @Autowired
    private ESQCalculator esqCalculator;

    @RequestMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @RequestMapping(value = "/survey/list")
    public String surveyList(Model model) {
        log.debug("ENTER");
        Iterable<ServiceQualitySurvey> results = serviceQualitySurveyRepository.findAll();
        model.addAttribute("surveys", results);
        log.debug("EXIT");
        return "survey_list";
    }

    @RequestMapping(value = "/esq/group")
    public String evalServiceQuality(Model model) {
        log.debug("ENTER");
        ESQSettingsProfile settings = settingsProfileRepository.findOne(1L);
        esqCalculator.setSettingsProfile(settings);
        log.debug("profile  = {}", settings);
        List<ESQSurveyResultGroup> groups = esqCalculator.getGroupedSurveyResults();
        model.addAttribute("groups", groups);
        log.debug("EXIT");
        return "esq_groups";
    }

}
