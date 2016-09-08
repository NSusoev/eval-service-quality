package esq.core.model;

import esq.application.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyResultGroupsContainer {
    Map<ClientCategory, Map<ClientGroup, Map<Service, List<ESQSurveyResultGroup>>>> surveyResults = new HashMap<>();

    LinguisticTerm integralMarkAll;
    Map<ClientCategory, LinguisticTerm> integralMarksForCategories = new HashMap<>();
    Map<ClientCategory, Map<ClientGroup, LinguisticTerm>> integralMarksForGroups = new HashMap<>();

    public Map<ClientCategory, Map<ClientGroup, Map<Service, List<ESQSurveyResultGroup>>>> getSurveyResults() {
        return surveyResults;
    }

    public void setSurveyResults(Map<ClientCategory, Map<ClientGroup, Map<Service, List<ESQSurveyResultGroup>>>> surveyResults) {
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

    @Override
    public String toString() {
        return String.format("SurveyResultGroupsContainer[surveyResults = %s, integralMarkAll = %s," +
                " integralMarksForCategories = %s, integralMarksForGroups = %s]",
                surveyResults, integralMarkAll, integralMarksForCategories, integralMarksForGroups);
    }
}
