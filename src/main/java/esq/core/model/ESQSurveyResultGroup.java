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
    private Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks;
    private Map<LinguisticTerm, LinguisticTerm> aggregatedQualityMarks = new HashMap<>();
    private Map<LinguisticTerm, Float> marksFrequences = new HashMap<>();

    public ESQSurveyResultGroup(ESQSurveyResultGroupMeta esqSurveyResultGroupMeta, Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks) {
        this.qualityMarks = qualityMarks;
        this.esqSurveyResultGroupMeta = esqSurveyResultGroupMeta;
    }

    public Map<LinguisticTerm, List<LinguisticTerm>> getQualityMarks() {
        return qualityMarks;
    }

    public void setQualityMarks(Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks) {
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

    public Map<LinguisticTerm, Float> getMarksFrequences() {
        return marksFrequences;
    }

    public void setMarksFrequences(Map<LinguisticTerm, Float> marksFrequences) {
        this.marksFrequences = marksFrequences;
    }

    @Override
    public String toString() {
        return String.format("ESQSurveyResultGroup[MetaInfo = %s, qualityMarks = %s," +
                " aggregatedQualityMarks = %s, marksFrequences = %s]\n\n",
                esqSurveyResultGroupMeta, qualityMarks, aggregatedQualityMarks, marksFrequences);
    }

    public String getQualityMarksListView(LinguisticTerm group) {
        StringBuilder sb = new StringBuilder();
        List<LinguisticTerm> marks = qualityMarks.get(group);
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
