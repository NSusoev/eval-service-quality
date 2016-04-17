package esq.main.model;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

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
    private Set<ServiceQualitySurveyResult> surveyResults = new HashSet<ServiceQualitySurveyResult>();
    @ManyToOne
    private ClientCategory clientCategory;
    @ManyToOne
    private ClientGroup clientGroup;

    protected ServiceQualitySurvey() {

    }

    public ServiceQualitySurvey(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public ServiceQualitySurvey(Date surveyDate, Set<ServiceQualitySurveyResult> surveyResults) {
        this.surveyResults = surveyResults;
        this.surveyDate = surveyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id ) {
        this.id = id;
    }

    public Set<ServiceQualitySurveyResult> getSurveyResults() {
        return this.surveyResults;
    }

    public void setSurveyResults(Set<ServiceQualitySurveyResult> surveyResults) {
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
