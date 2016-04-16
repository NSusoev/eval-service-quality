package esq.main.model;

import javax.persistence.*;
import javax.validation.OverridesAttribute;

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
    private LinguisticTerm expectationMark;
    @ManyToOne
    private LinguisticTerm afterUseMark;
    @ManyToOne
    private LinguisticTerm importanceMark;
    @ManyToOne
    private ServiceQualitySurvey serviceQualitySurvey;

    protected ServiceQualitySurveyResult() {
    }

    public ServiceQualitySurveyResult(LinguisticTerm expectationMark,
                                      LinguisticTerm afterUseMark,
                                      LinguisticTerm importanceMark,
                                      ServiceQualitySurvey serviceQualitySurvey) {
        this.expectationMark = expectationMark;
        this.afterUseMark = afterUseMark;
        this.importanceMark = importanceMark;
        this.serviceQualitySurvey = serviceQualitySurvey;
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

    @Override
    public String toString() {
        return String.format("ServiceQualitySurveyResult[id=%d, expectation mark=%s, after use mark=%s, importance mark=%s, survey=%s]",
                id, expectationMark, afterUseMark, importanceMark, serviceQualitySurvey);
    }
}
