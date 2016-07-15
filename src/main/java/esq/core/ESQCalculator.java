package esq.core;

import esq.application.model.*;
import esq.application.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public void calculateAggregatedQualityMarks(List<ESQSurveyResultGroup> esqSurveyResultGroups) {
        log.debug("ENTER");

        for (ESQSurveyResultGroup esqSurveyResultGroup : esqSurveyResultGroups) {
            for (LinguisticTerm importanceMark : esqSurveyResultGroup.getQualityMarks().keySet()) {

                Collections.sort(esqSurveyResultGroup.getQualityMarks().get(importanceMark));
                log.debug("COLLECTION AFTER SORTING = {}", esqSurveyResultGroup.getQualityMarks().get(importanceMark));
                long aggregatedMark = (long)calculateAggregatedQualityMark(esqSurveyResultGroup.getQualityMarks().get(importanceMark));
                esqSurveyResultGroup.getAggregatedQualityMarks().put(importanceMark, linguisticTermRepository.findOne(aggregatedMark));
            }
            log.debug("AGGREGATED MARKS = {}", esqSurveyResultGroup.getAggregatedQualityMarks());
        }
        log.debug("EXIT");
    }

    private float calculateAggregatedQualityMark(List<LinguisticTerm> qualityMarks) {
        log.debug("ENTER");

        if (qualityMarks.size() < 2) {
            return qualityMarks.get(0).getId();
        }

        generateImportanceWeightsForQualityMarks(qualityMarks);
        log.debug("TERMS WITH NEW WEIGHTS = {}", qualityMarks);

        if (qualityMarks.size() > 2) {
            long result = (long)calculateAggregatedQualityMark(qualityMarks.subList(1, qualityMarks.size() - 1));
            return Math.min(5, result + Math.round(qualityMarks.get(0).getWeight() * (qualityMarks.get(0).getId() - result)));
        }

        log.debug("EXIT");
        return Math.min(5, qualityMarks.get(1).getId() + Math.round(qualityMarks.get(0).getWeight() * (qualityMarks.get(0).getId() - qualityMarks.get(1).getId())));
    }

    private void generateImportanceWeightsForQualityMarks(List<LinguisticTerm> qualityMarks) {
        // заглушка
        float weight = 1 / qualityMarks.size();

        for (LinguisticTerm qualityMark : qualityMarks) {
            qualityMark.setWeight(weight);
        }
    }

}
