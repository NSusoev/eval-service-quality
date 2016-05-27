package esq.core;

import esq.application.model.*;
import esq.application.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Класс расчёта интегральной оценки качества
 */


@Service
public class ESQCalculator {

    private ESQSettingsProfile settingsProfile;
    private static final Logger log = LoggerFactory.getLogger(ESQCalculator.class);
    @Autowired
    private LinguisticTermRepository linguisticTermRepository;
    @Autowired
    private ClientCategoryRepository clientCategoryRepository;
    @Autowired
    private ClientGroupRepository clientGroupRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ServiceQualityCriteriaRepository serviceQualityCriteriaRepository;

    public ESQCalculator() {

    }

    public ESQCalculator(ESQSettingsProfile settingsProfile) {
        this.settingsProfile = settingsProfile;
    }

    public void setSettingsProfile(ESQSettingsProfile settingsProfile) {
        this.settingsProfile = settingsProfile;
    }

    public ESQSettingsProfile getSettingsProfile() {
        return settingsProfile;
    }

    public List<ESQSurveyResultGroup> getGroupedSurveyResults() {
        log.debug("ENTER");
        List<ESQSurveyResultGroup> resultGroups = new ArrayList<>();

        // TODO: подумать над рефакторингом в следующих релизах, тк будет медленно работать
        for (ClientCategory category : clientCategoryRepository.findAll()) {
            for (ClientGroup group : category.getClientGroups()) {
                for (esq.application.model.Service service: category.getServicesForThisCategory()) {
                    for (ServiceQualityCriteria criteria : service.getServiceQualityCriterias()) {
                        Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks = new HashMap<>();

                        for (LinguisticTerm importance : linguisticTermRepository.findAll()) {
                            List<LinguisticTerm> qualityMarksForImportanceGroup = linguisticTermRepository.findQualityMarksForGroup(
                                    category.getId(),
                                    group.getId(),
                                    service.getId(),
                                    criteria.getId(),
                                    importance.getId());
                            if (!qualityMarksForImportanceGroup.isEmpty()) {
                                qualityMarks.put(importance, qualityMarksForImportanceGroup);
                            }
                        }

                        if (!qualityMarks.isEmpty()) {
                            resultGroups.add(new ESQSurveyResultGroup(category, group, service, criteria, qualityMarks));
                        }
                    }
                }
            }
        }

        log.debug("created groups = {}", resultGroups);
        log.debug("EXIT");
        return resultGroups;
    }
}
