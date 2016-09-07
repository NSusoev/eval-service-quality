package esq.core.model;

import esq.application.model.ClientCategory;
import esq.application.model.ClientGroup;
import esq.application.model.Service;
import esq.application.model.ServiceQualityCriteria;

/**
 * Информация о группe результатов анкетирования
 */

public class ESQSurveyResultGroupMeta {

    private ClientCategory clientCategory;
    private ClientGroup clientGroup;
    private Service service;

    public ESQSurveyResultGroupMeta(ClientCategory clientCategory,
                                    ClientGroup clientGroup,
                                    Service service) {
        this.clientCategory = clientCategory;
        this.clientGroup = clientGroup;
        this.service = service;
    }

    public ClientCategory getClientCategory() {
        return clientCategory;
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

    @Override
    public String toString() {
        return String.format("ESQSurveyResultGroupMeta[ClientCategory = %s, ClientGroup = %s," +
                " Service = %s]\n\n",
                clientCategory,
                clientGroup,
                service);
    }
}
