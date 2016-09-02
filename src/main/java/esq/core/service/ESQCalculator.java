package esq.core.service;

import esq.application.model.*;
import esq.application.repository.*;
import esq.core.model.ESQSurveyResultGroup;
import esq.core.model.ESQSurveyResultGroupMeta;
import esq.core.model.QualityMarksVector;
import esq.core.model.SurveyResultGroupsContainer;
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
                calculateLinguisticTermsFrequencies(group);
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
                    long aggregatedMark = calculateAggregatedQualityMark(importanceMark,
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

    private long calculateAggregatedQualityMark(LinguisticTerm importanceMark, List<LinguisticTerm> qualityMarks, List<Float> weights) throws IllegalArgumentException {
        log.debug("ENTER");
        if (qualityMarks == null || importanceMark == null) {
            throw new IllegalArgumentException();
        }

        int qualityMarksListSize = qualityMarks.size();
        log.debug("MARKS LIST SIZE = {}", qualityMarksListSize);

        if (qualityMarksListSize < 2) {
            return qualityMarks.get(0).getId();
        }

        normalizeWeightsForMarks(weights);

        if (qualityMarksListSize > 2) {
            long result = calculateAggregatedQualityMark(importanceMark, qualityMarks.subList(1, qualityMarksListSize), weights.subList(1, weights.size()));
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

        float power = getCalcPowerByImportance(importanceMark);

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

    private float getCalcPowerByImportance(LinguisticTerm importanceMark) {
        float power = 0;

        if (importanceMark == null) {
            throw new IllegalArgumentException();
        }

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

        return power;
    }

    private void normalizeWeightsForMarks(List<Float> weights) throws IllegalArgumentException {
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

    private void normalizeWeightsForMarks(Map<LinguisticTerm, Float> aggregatedMarksWithWeights) throws IllegalArgumentException {
        log.debug("ENTER");
        if (aggregatedMarksWithWeights == null) {
            throw new IllegalArgumentException();
        }

        float sumWeight = 0;

        for (Float weight : aggregatedMarksWithWeights.values()) {
            sumWeight += weight;
        }
        log.debug("AGGR MARKS SUM WEIGHT = {}", sumWeight);

        for (LinguisticTerm importance : aggregatedMarksWithWeights.keySet()) {
            aggregatedMarksWithWeights.put(importance, aggregatedMarksWithWeights.get(importance) / sumWeight);
        }

        log.debug("AGGREGATED MARKS WITH NORMALIZED WEIGHTS = {}", aggregatedMarksWithWeights);
        log.debug("EXIT");
    }

    private long calculateIntegralQualityMark(Map<LinguisticTerm, LinguisticTerm> qualityMarks) {
        log.debug("ENTER");
        if (qualityMarks == null) {
            throw new IllegalArgumentException();
        }

        Collection<LinguisticTerm> qualityMarksList = qualityMarks.values();

        if (qualityMarksList.size() < 2) {
            return qualityMarksList.iterator().next().getId();
        }

        log.debug("QUALITY MARKS = {}", qualityMarks);
        float result = 0;
        Map<LinguisticTerm, Float> marksWithWeights = new HashMap<>();
        for (LinguisticTerm importanceMark : qualityMarks.keySet()) {
            log.debug("IMPORTANCE = {}", importanceMark);
            LinguisticTerm qualityMark = qualityMarks.get(importanceMark);
            float power = getCalcPowerByImportance(qualityMark);
            marksWithWeights.put(importanceMark, (float)(Math.pow(qualityMark.getId(), power)));
        }

        normalizeWeightsForMarks(marksWithWeights);
        log.debug("MARKS WITH NORMALIZED WEIGHTS = {}", marksWithWeights);
        for (LinguisticTerm importanceMark : marksWithWeights.keySet()) {
            result += qualityMarks.get(importanceMark).getId() * marksWithWeights.get(importanceMark);
        }
        log.debug("INTEGRAL QUALITY MARK BEFORE ROUNDING = {}", result);
        if (result < 1) {
            result = 1;
        } else {
            result = Math.round(result);
        }
        log.debug("EXIT");
        return (long)result;
    }

    public void calculateIntegralQualityMarks(List<ESQSurveyResultGroup> resultGroups) {
        log.debug("ENTER");

        for (ESQSurveyResultGroup group : resultGroups) {
            LinguisticTerm integralMark = linguisticTermRepository
                    .findOne(calculateIntegralQualityMark(group.getAggregatedQualityMarks()));
            group.setIntegralQualityMark(integralMark);
        }
        log.debug("GROUPS WITH INTERGRAL MARKS = {}", resultGroups);
        log.debug("EXIT");
    }

    private void calculateLinguisticTermsFrequencies(ESQSurveyResultGroup group) throws IllegalArgumentException {
        log.debug("ENTER");
        if (group == null) {
            throw new IllegalArgumentException();
        }

        for (LinguisticTerm qualityMark : linguisticTermRepository.findAll()) {
            float frequency = esqSurveyResultGroupMetaDAO.getFrequencyOfMarkForSubCriteria(group.getESQSurveyResultGroupMeta().getClientCategory().getId(),
                    group.getESQSurveyResultGroupMeta().getClientGroup().getId(),
                    group.getESQSurveyResultGroupMeta().getService().getId(),
                    group.getESQSurveyResultGroupMeta().getServiceQualityCriteria().getId(),
                    qualityMark.getId());

            if (frequency != 0) {
                group.getMarksFrequencies().put(qualityMark, frequency);
            }
        }

        log.debug("GROUP WITH FREQUENCES = {}", group);
        log.debug("EXIT");
    }

    public SurveyResultGroupsContainer createContainer(List<ESQSurveyResultGroup> resultGroups) {
        SurveyResultGroupsContainer container = new SurveyResultGroupsContainer();
        Map<ServiceQualityCriteria, ESQSurveyResultGroup> criterias = new HashMap<>();
        Map<esq.application.model.Service, Map<ServiceQualityCriteria, ESQSurveyResultGroup>> services = new HashMap<>();
        Map<ClientGroup, Map<esq.application.model.Service, Map<ServiceQualityCriteria, ESQSurveyResultGroup>>> groups = new HashMap<>();

        for (ESQSurveyResultGroup group : resultGroups) {
            // TODO: сделать проверку на null, чтобы добавлялось, а не перезаписывалось (может и не надо ?)
            // Подумать, как считать интегральную оценку для сервиса
            criterias.put(group.getESQSurveyResultGroupMeta().getServiceQualityCriteria(), group);
            services.put(group.getESQSurveyResultGroupMeta().getService(), criterias);
            groups.put(group.getESQSurveyResultGroupMeta().getClientGroup(), services);
            container.getSurveyResults().put(group.getESQSurveyResultGroupMeta().getClientCategory(), groups);
        }

        return container;
    }

}
