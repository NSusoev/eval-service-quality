package esq.application.model;

import javax.persistence.*;
import java.lang.Math;

/**
 * Created by nsusoev on 08.04.16.
 */

@Entity
@Table(name = "ServiceQualitySurveyResults")
public class ServiceQualitySurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private ServiceQualitySurvey serviceQualitySurvey;
    @ManyToOne
    private ServiceQualityCriteria serviceQualityCriteria;
    @ManyToOne
    private LinguisticTerm expectationMark;
    @ManyToOne
    private LinguisticTerm afterUseMark;
    @ManyToOne
    private LinguisticTerm importanceMark;

    protected ServiceQualitySurveyResult() {
    }

    public ServiceQualitySurveyResult(ServiceQualitySurvey serviceQualitySurvey,
                                      ServiceQualityCriteria serviceQualityCriteria,
                                      LinguisticTerm expectationMark,
                                      LinguisticTerm afterUseMark,
                                      LinguisticTerm importanceMark) {
        this.serviceQualitySurvey = serviceQualitySurvey;
        this.serviceQualityCriteria = serviceQualityCriteria;
        this.expectationMark = expectationMark;
        this.afterUseMark = afterUseMark;
        this.importanceMark = importanceMark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id ) {
        this.id = id;
    }

    public LinguisticTerm getExpectationMark() {
        return expectationMark;
    }

    public void setExpectationMark(LinguisticTerm expectationMark) {
        this.expectationMark = expectationMark;
    }

    public LinguisticTerm getAfterUseMark() {
        return afterUseMark;
    }

    public void setAfterUseMark(LinguisticTerm afterUseMark) {
        this.afterUseMark = afterUseMark;
    }

    public LinguisticTerm getImportanceMark() {
        return importanceMark;
    }

    public void setImportanceMark(LinguisticTerm importanceMark) {
        this.importanceMark = importanceMark;
    }

    public void setServiceQualitySurvey(ServiceQualitySurvey serviceQualitySurvey) {
        this.serviceQualitySurvey = serviceQualitySurvey;
    }

    public ServiceQualitySurvey getServiceQualitySurvey() {
        return this.serviceQualitySurvey;
    }

    public ServiceQualityCriteria getServiceQualityCriteria() {
        return this.serviceQualityCriteria;
    }

    public void setServiceQualityCriteria(ServiceQualityCriteria serviceQualityCriteria) {
        this.serviceQualityCriteria = serviceQualityCriteria;
    }

    @Override
    public String toString() {
        return String.format("ServiceQualitySurveyResult[id=%d, service quality criteria=%s,expectation mark=%s, after use mark=%s, importance mark=%s, survey=%s]",
                id, serviceQualityCriteria, expectationMark, afterUseMark, importanceMark, serviceQualitySurvey);
    }

    public int getQualityMark() {
        return Math.max(Math.min(2 * this.afterUseMark.getValue() - this.expectationMark.getValue(), 5), 1);
    }
}
