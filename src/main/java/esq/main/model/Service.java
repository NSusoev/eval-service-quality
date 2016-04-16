package esq.main.model;

import javax.persistence.*;
import javax.validation.OverridesAttribute;
import java.util.List;

/**
 * Created by nsusoev on 05.04.16.
 */

@Entity
@Table(name = "Services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @ManyToMany
    private List<ServiceQualityCriteria> serviceQualityCriterias;
    @ManyToMany
    private List<ClientCategory> clientCategoriesFor;

    protected Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServiceQualityCriteria> getServiceQualityCriterias() {
        return this.serviceQualityCriterias;
    }

    public void setServiceQualityCriterias(List<ServiceQualityCriteria> serviceQualityCriterias) {
        this.serviceQualityCriterias = serviceQualityCriterias;
    }

    public List<ClientCategory> getClientCategoriesFor() {
        return clientCategoriesFor;
    }

    public void setClientCategoriesFor(List<ClientCategory> clientCategoriesFor) {
        this.clientCategoriesFor = clientCategoriesFor;
    }

    @Override
    public String toString() {
        return String.format("Service[id=%d, name=%s, service quality criterias=%s, client categories for=%s]",
                id, name, serviceQualityCriterias, clientCategoriesFor);
    }
}
