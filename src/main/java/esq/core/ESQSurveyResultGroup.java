package esq.core;

import esq.application.model.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Группа результатов анкетирования
 */

public class ESQSurveyResultGroup {

    private ClientCategory clientCategory;
    private ClientGroup clientGroup;
    private Service service;
    private ServiceQualityCriteria serviceQualityCriteria;
    private Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks;

    public ESQSurveyResultGroup(ClientCategory clientCategory,
                                ClientGroup clientGroup,
                                Service service,
                                ServiceQualityCriteria serviceQualityCriteria,
                                Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks) {
        this.clientCategory = clientCategory;
        this.clientGroup = clientGroup;
        this.service = service;
        this.serviceQualityCriteria = serviceQualityCriteria;
        this.qualityMarks = qualityMarks;

    }

    public ClientCategory getClientCategory() {
        return this.clientCategory;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public ClientGroup getClientGroup() {
        return clientGroup;
    }

    public void setClientGroup(ClientGroup clientGroup) {
        this.clientGroup = clientGroup;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceQualityCriteria getServiceQualityCriteria() {
        return serviceQualityCriteria;
    }

    public void setServiceQualityCriteria(ServiceQualityCriteria serviceQualityCriteria) {
        this.serviceQualityCriteria = serviceQualityCriteria;
    }

    public Map<LinguisticTerm, List<LinguisticTerm>> getQualityMarks() {
        return qualityMarks;
    }

    public void setQualityMarks(Map<LinguisticTerm, List<LinguisticTerm>> qualityMarks) {
        this.qualityMarks = qualityMarks;
    }

    @Override
    public String toString() {
        return String.format("ESQSurveyResultGroup[clientCategory = %s, clientGroup = %s, service = %s, serviceQualityCriteria = %s, qualityMarks = %s]\n\n",
                clientCategory.getName(), clientGroup.getName(), service.getName(), serviceQualityCriteria.getName(), qualityMarks);
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
