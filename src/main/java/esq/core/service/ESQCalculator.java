package esq.core.service;

import esq.application.model.*;
import esq.application.repository.*;
import esq.core.model.ESQSurveyResultGroup;
import esq.core.model.ESQSurveyResultGroupMeta;
import esq.core.repository.ESQSurveyResultGroupMetaDAO;
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
    private ESQSurveyResultGroupMetaDAO esqSurveyResultGroupMetaDAO;

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

        List<ESQSurveyResultGroupMeta> groupsMeta = esqSurveyResultGroupMetaDAO.findAllMetaGroups();
        log.debug("groupsMeta = {}", groupsMeta);

        for (ESQSurveyResultGroupMeta groupMeta : groupsMeta) {
            Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks = new HashMap<>();

            for (LinguisticTerm importance : linguisticTermRepository.findAll()) {
                List<LinguisticTerm> qualityMarksForImportanceGroup = linguisticTermRepository.findQualityMarksForGroup(
                        groupMeta.getClientCategory().getId(),
                        groupMeta.getClientGroup().getId(),
                        groupMeta.getService().getId(),
                        groupMeta.getServiceQualityCriteria().getId(),
                        importance.getId());

                if (!qualityMarksForImportanceGroup.isEmpty()) {
                    qualityMarks.put(importance, qualityMarksForImportanceGroup);
                }
            }

            if (!qualityMarks.isEmpty()) {
                ESQSurveyResultGroup group = new ESQSurveyResultGroup(groupMeta, qualityMarks);
                calculaceLinguisticTermsFrequences(group);
                resultGroups.add(group);
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
                try {
                    long aggregatedMark = (long)calculateAggregatedQualityMark(importanceMark, esqSurveyResultGroup.getQualityMarks().get(importanceMark));
                    esqSurveyResultGroup.getAggregatedQualityMarks().put(importanceMark, linguisticTermRepository.findOne(aggregatedMark));
                } catch (IllegalArgumentException e) {
                    log.error(e.toString());
                }
            }
            log.debug("AGGREGATED MARKS = {}", esqSurveyResultGroup.getAggregatedQualityMarks());
        }
        log.debug("EXIT");
    }

    private float calculateAggregatedQualityMark(LinguisticTerm importanceMark, List<LinguisticTerm> qualityMarks) throws IllegalArgumentException {
        log.debug("ENTER");
        if (qualityMarks == null || importanceMark == null) {
            throw new IllegalArgumentException();
        }

        if (qualityMarks.size() < 2) {
            return qualityMarks.get(0).getId();
        }

        generateImportanceWeightsForQualityMarks(importanceMark, qualityMarks);
        log.debug("TERMS WITH NEW WEIGHTS = {}", qualityMarks);

        if (qualityMarks.size() > 2) {
            long result = (long)calculateAggregatedQualityMark(importanceMark, qualityMarks.subList(1, qualityMarks.size() - 1));
            return Math.min(5, result + Math.round(qualityMarks.get(0).getWeight() * (qualityMarks.get(0).getId() - result)));
        }

        log.debug("EXIT");
        return Math.min(5, qualityMarks.get(1).getId() + Math.round(qualityMarks.get(0).getWeight() * (qualityMarks.get(0).getId() - qualityMarks.get(1).getId())));
    }

    private void generateImportanceWeightsForQualityMarks(LinguisticTerm importanceMark, List<LinguisticTerm> qualityMarks)
            throws IllegalArgumentException {
        log.debug("ENTER");
        if (qualityMarks == null || importanceMark == null) {
            throw new IllegalArgumentException();
        }

        switch (importanceMark.getName()) {
            case "очень низкое":
                for (LinguisticTerm qualityMark : qualityMarks) {
                    qualityMark.setWeight((float)(Math.pow(qualityMark.getId(), 0.75)));
                }
                break;

            case "низкое":
                for (LinguisticTerm qualityMark : qualityMarks) {
                    qualityMark.setWeight((float)(Math.pow(qualityMark.getId(), 0.5)));
                }
                break;

            case "среднее":
                int size = qualityMarks.size();

                for (LinguisticTerm qualityMark : qualityMarks) {
                    qualityMark.setWeight(1 / size);
                }
                break;

            case "высокое":
                for (LinguisticTerm qualityMark : qualityMarks) {
                    qualityMark.setWeight((float)(Math.pow(qualityMark.getId(), -0.5)));
                }
                break;

            case "очень высокое":
                for (LinguisticTerm qualityMark : qualityMarks) {
                    qualityMark.setWeight((float)(Math.pow(qualityMark.getId(), -0.75)));
                }
                break;

            default:
                throw new IllegalArgumentException();
        }

        // нормализация данных
        float sumWeight = 0;

        for (LinguisticTerm qualityMark : qualityMarks) {
            sumWeight += qualityMark.getWeight();
        }

        for (LinguisticTerm qualityMark : qualityMarks) {
            qualityMark.setWeight(qualityMark.getWeight() / sumWeight);
        }

        log.debug("EXIT");
    }

    private void calculaceLinguisticTermsFrequences(ESQSurveyResultGroup group) throws IllegalArgumentException {
        log.debug("ENTER");
        if (group == null) {
            throw new IllegalArgumentException();
        }

        for (LinguisticTerm qualityMark : linguisticTermRepository.findAll()) {
            float frequence = esqSurveyResultGroupMetaDAO.getFrequenceOfMarkForSubCriteria(group.getESQSurveyResultGroupMeta().getClientCategory().getId(),
                    group.getESQSurveyResultGroupMeta().getClientGroup().getId(),
                    group.getESQSurveyResultGroupMeta().getService().getId(),
                    group.getESQSurveyResultGroupMeta().getServiceQualityCriteria().getId(),
                    qualityMark.getId());

            if (frequence != 0) {
                group.getMarksFrequences().put(qualityMark, frequence);
            }
        }

        log.debug("GROUP WITH FREQUENCES = {}", group);
        log.debug("EXIT");
    }

}
