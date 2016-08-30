package esq.core.service;

import esq.application.model.*;
import esq.application.repository.*;
import esq.core.model.ESQSurveyResultGroup;
import esq.core.model.ESQSurveyResultGroupMeta;
import esq.core.model.QualityMarksVector;
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
            Map<LinguisticTerm, QualityMarksVector> qualityMarks = new HashMap<>();

            for (LinguisticTerm importance : linguisticTermRepository.findAll()) {
                List<LinguisticTerm> qualityMarksForImportanceGroup = linguisticTermRepository.findQualityMarksForGroup(
                        groupMeta.getClientCategory().getId(),
                        groupMeta.getClientGroup().getId(),
                        groupMeta.getService().getId(),
                        groupMeta.getServiceQualityCriteria().getId(),
                        importance.getId());

                if (!qualityMarksForImportanceGroup.isEmpty()) {
                    qualityMarks.put(importance, new QualityMarksVector(qualityMarksForImportanceGroup));
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

                Collections.sort(esqSurveyResultGroup.getQualityMarks().get(importanceMark).getQualityMarks());
                log.debug("COLLECTION AFTER SORTING = {}", esqSurveyResultGroup.getQualityMarks().get(importanceMark));
                try {
                    QualityMarksVector qualityMarks = esqSurveyResultGroup.getQualityMarks().get(importanceMark);
                    generateImportanceWeightsForQualityMarks(importanceMark, qualityMarks);
                    long aggregatedMark = (long)calculateAggregatedQualityMark(importanceMark,
                            qualityMarks.getQualityMarks(), qualityMarks.getWeights());

                    esqSurveyResultGroup.getAggregatedQualityMarks().put(importanceMark, linguisticTermRepository.findOne(aggregatedMark));
                } catch (IllegalArgumentException e) {
                    log.error(e.toString());
                }
            }
            log.debug("AGGREGATED MARKS = {}", esqSurveyResultGroup.getAggregatedQualityMarks());
        }
        log.debug("EXIT");
    }

    private float calculateAggregatedQualityMark(LinguisticTerm importanceMark, List<LinguisticTerm> qualityMarks, List<Float> weights) throws IllegalArgumentException {
        log.debug("ENTER");
        if (qualityMarks == null || importanceMark == null) {
            throw new IllegalArgumentException();
        }

        int qualityMarksListSize = qualityMarks.size();
        log.debug("MARKS LIST SIZE = {}", qualityMarksListSize);

        if (qualityMarksListSize < 2) {
            return qualityMarks.get(0).getId();
        }

        normalizeMarks(weights);

        if (qualityMarksListSize > 2) {
            long result = (long)calculateAggregatedQualityMark(importanceMark, qualityMarks.subList(1, qualityMarksListSize), weights.subList(1, weights.size()));
            return Math.min(5, result + Math.round(weights.get(0) * (qualityMarks.get(0).getId() - result)));
        }

        log.debug("EXIT");
        return Math.min(5, qualityMarks.get(1).getId() + Math.round(weights.get(0) * (qualityMarks.get(0).getId() - qualityMarks.get(1).getId())));
    }

    private void generateImportanceWeightsForQualityMarks(LinguisticTerm importanceMark, QualityMarksVector qualityMarks)
            throws IllegalArgumentException {
        log.debug("ENTER");
        if (qualityMarks == null || importanceMark == null) {
            throw new IllegalArgumentException();
        }

        float power = 0;

        switch (importanceMark.getName()) {
            case "очень низкое":
                power = 1;
                break;

            case "низкое":
                power = -1.2f;
                break;

            case "среднее":
                power = -1.3f;
                break;

            case "высокое":
                power = -2;
                break;

            case "очень высокое":
                power = -3;
                break;

            default:
                throw new IllegalArgumentException();
        }

        if (power == 1) {
            float size = qualityMarks.getWeights().size();

            for (int i = 0; i < qualityMarks.getWeights().size(); i++) {
                qualityMarks.getWeights().set(i, 1 / size);
            }

        } else {
            for (int i = 0; i < qualityMarks.getWeights().size(); i++) {
                qualityMarks.getWeights().set(i, (float)(Math.pow(qualityMarks.getQualityMarks().get(i).getId(), power)));
            }
        }

        log.debug("IMPORTANCE MARKS WITH GENERATED WEIGHTS[ {} ] = {}", importanceMark, qualityMarks);
        log.debug("EXIT");
    }

    private void normalizeMarks(List<Float> weights) throws IllegalArgumentException {
        log.debug("ENTER");
        if (weights == null) {
            throw new IllegalArgumentException();
        }

        float sumWeight = 0;

        for (Float weight : weights) {
            sumWeight += weight;
        }
        log.debug("SUM WEIGHT = {}", sumWeight);

        for (int i = 0; i < weights.size(); i++) {
            log.debug("WEIGHT = {}", weights.get(i));
            weights.set(i, weights.get(i) / sumWeight);
        }

        log.debug("NORMALIZED WEIGHTS = {}", weights);
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
