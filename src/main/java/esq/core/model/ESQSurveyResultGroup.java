package esq.core.model;

import esq.application.model.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Группа результатов анкетирования
 */

public class ESQSurveyResultGroup {

    private ESQSurveyResultGroupMeta esqSurveyResultGroupMeta;
    private Map<LinguisticTerm, QualityMarksVector> qualityMarks;
    private Map<LinguisticTerm, LinguisticTerm> aggregatedQualityMarks = new HashMap<>();
    private Map<LinguisticTerm, Float> marksFrequencies = new HashMap<>();
    private LinguisticTerm integralQualityMark = null;

    public ESQSurveyResultGroup(ESQSurveyResultGroupMeta esqSurveyResultGroupMeta, Map<LinguisticTerm, QualityMarksVector> qualityMarks) {
        this.qualityMarks = qualityMarks;
        this.esqSurveyResultGroupMeta = esqSurveyResultGroupMeta;
    }

    public Map<LinguisticTerm, QualityMarksVector> getQualityMarks() {
        return qualityMarks;
    }

    public void setQualityMarks(Map<LinguisticTerm, QualityMarksVector> qualityMarks) {
        this.qualityMarks = qualityMarks;
    }

    public Map<LinguisticTerm, LinguisticTerm> getAggregatedQualityMarks() {
        return aggregatedQualityMarks;
    }

    public void setAggregatedQualityMarks(Map<LinguisticTerm, LinguisticTerm> aggregatedQualityMarks) {
        this.aggregatedQualityMarks = aggregatedQualityMarks;
    }

    public ESQSurveyResultGroupMeta getESQSurveyResultGroupMeta() {
        return esqSurveyResultGroupMeta;
    }

    public void setESQSurveyResultGroupMeta(ESQSurveyResultGroupMeta esqSurveyResultGroupMeta) {
        this.esqSurveyResultGroupMeta = esqSurveyResultGroupMeta;
    }

    public Map<LinguisticTerm, Float> getMarksFrequencies() {
        return marksFrequencies;
    }

    public void setMarksFrequencies(Map<LinguisticTerm, Float> marksFrequences) {
        this.marksFrequencies = marksFrequences;
    }

    public LinguisticTerm getIntegralQualityMark() {
        return integralQualityMark;
    }

    public void setIntegralQualityMark(LinguisticTerm integralQualityMark) {
        this.integralQualityMark = integralQualityMark;
    }

    @Override
    public String toString() {
        return String.format("ESQSurveyResultGroup[MetaInfo = %s, qualityMarks = %s," +
                " aggregatedQualityMarks = %s, marksFrequencies = %s, integralQualityMark = %s]\n\n",
                esqSurveyResultGroupMeta, qualityMarks, aggregatedQualityMarks, marksFrequencies, integralQualityMark);
    }

    public String getQualityMarksListView(LinguisticTerm group) {
        StringBuilder sb = new StringBuilder();
        List<LinguisticTerm> marks = qualityMarks.get(group).getQualityMarks();
        Iterator<LinguisticTerm> marksIterator = marks.iterator();
        while(marksIterator.hasNext()) {
            sb.append(marksIterator.next().getName());

            if (marksIterator.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
