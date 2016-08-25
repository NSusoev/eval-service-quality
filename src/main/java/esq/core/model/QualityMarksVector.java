package esq.core.model;

import esq.application.model.LinguisticTerm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Вектор оценок качества с соответствующими им весами
 */

public class QualityMarksVector {
    private List<LinguisticTerm> qualityMarks;
    private List<Float> weights;

    public QualityMarksVector(List<LinguisticTerm> qualityMarks) {
        this.qualityMarks = qualityMarks;
        weights = new ArrayList<>(Collections.nCopies(qualityMarks.size(), 0f));
    }

    public List<LinguisticTerm> getQualityMarks() {
        return qualityMarks;
    }

    public void setQualityMarks(List<LinguisticTerm> qualityMarks) {
        this.qualityMarks = qualityMarks;
    }

    public List<Float> getWeights() {
        return weights;
    }

    public void setWeights(List<Float> weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        return "QualityMarksVector[" +
                "qualityMarks=" + qualityMarks +
                ", weights=" + weights +
                ']';
    }
}
