package esq.core.model;

import esq.application.model.*;

import java.util.HashMap;
import java.util.Map;

public class SurveyResultGroupsContainer {
    Map<ClientCategory, Map<ClientGroup, Map<Service,
            Map<ServiceQualityCriteria, ESQSurveyResultGroup>>>> surveyResults = new HashMap<>();

    LinguisticTerm integralMarkAll;
    Map<ClientCategory, LinguisticTerm> integralMarksForCategories = new HashMap<>();
    Map<ClientCategory, Map<ClientGroup, LinguisticTerm>> integralMarksForGroups = new HashMap<>();

    public Map<ClientCategory, Map<ClientGroup, Map<Service, Map<ServiceQualityCriteria,
            ESQSurveyResultGroup>>>> getSurveyResults() {
        return surveyResults;
    }

    public void setSurveyResults(Map<ClientCategory, Map<ClientGroup, Map<Service, Map<ServiceQualityCriteria,
            ESQSurveyResultGroup>>>> surveyResults) {
        this.surveyResults = surveyResults;
    }

    public LinguisticTerm getIntegralMarkAll() {
        return integralMarkAll;
    }

    public void setIntegralMarkAll(LinguisticTerm integralMarkAll) {
        this.integralMarkAll = integralMarkAll;
    }

    public Map<ClientCategory, Map<ClientGroup, LinguisticTerm>> getIntegralMarksForGroups() {
        return integralMarksForGroups;
    }

    public void setIntegralMarksForGroups(Map<ClientCategory, Map<ClientGroup, LinguisticTerm>> integralMarksForGroups) {
        this.integralMarksForGroups = integralMarksForGroups;
    }

    public Map<ClientCategory, LinguisticTerm> getIntegralMarksForCategories() {
        return integralMarksForCategories;
    }

    public void setIntegralMarksForCategories(Map<ClientCategory, LinguisticTerm> integralMarksForCategories) {
        this.integralMarksForCategories = integralMarksForCategories;
    }
}
