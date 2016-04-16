package esq.main.model;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.util.List;
import java.util.Date;

/**
 * Created by nsusoev on 08.04.16.
 */

@Entity
@Table(name = "ServiceQualitySurveys")
public class ServiceQualitySurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private Date surveyDate;
    @OneToMany(mappedBy = "serviceQualitySurvey")
    private List<ServiceQualitySurveyResult> surveyResults;
    @ManyToOne
    private ClientCategory clientCategory;
    @ManyToOne
    private ClientGroup clientGroup;

    protected ServiceQualitySurvey() {

    }

    public ServiceQualitySurvey(Date surveyDate, List<ServiceQualitySurveyResult> surveyResults) {
        this.surveyResults = surveyResults;
        this.surveyDate = surveyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id ) {
        this.id = id;
    }

    public List<ServiceQualitySurveyResult> getSurveyResults() {
        return this.surveyResults;
    }

    public void setSurveyResults(List<ServiceQualitySurveyResult> surveyResults) {
        this.surveyResults = surveyResults;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public ClientCategory getClientCategory() {
        return this.clientCategory;
    }

    public void setClientGroup(ClientGroup clientGroup) {
        this.clientGroup = clientGroup;
    }

    public ClientGroup getClientGroup() {
        return this.clientGroup;
    }

    @Override
    public String toString() {
        return String.format("ServiceQualitySurvey[id=%d, results=%s, date=%s, client category=%s, client group=%s]",
                id, surveyResults, surveyDate, clientCategory, clientGroup);
    }
}
