package esq.application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private Set<ServiceQualityCriteria> serviceQualityCriterias = new HashSet<ServiceQualityCriteria>();
    @ManyToMany
    private Set<ClientCategory> clientCategoriesFor = new HashSet<ClientCategory>();

    protected Service() {
    }

    public Service(String name) {
        this.name = name;
    }

    public Service(String name, Set<ServiceQualityCriteria> serviceQualityCriterias) {
        this.name = name;
        this.serviceQualityCriterias = serviceQualityCriterias;
    }

    public Service(String name,
                   Set<ServiceQualityCriteria> serviceQualityCriterias,
                   Set<ClientCategory> clientCategoriesFor) {
        this.name = name;
        this.serviceQualityCriterias = serviceQualityCriterias;
        this.clientCategoriesFor = clientCategoriesFor;
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

    public Set<ServiceQualityCriteria> getServiceQualityCriterias() {
        return this.serviceQualityCriterias;
    }

    public void setServiceQualityCriterias(Set<ServiceQualityCriteria> serviceQualityCriterias) {
        this.serviceQualityCriterias = serviceQualityCriterias;
    }

    public Set<ClientCategory> getClientCategoriesFor() {
        return clientCategoriesFor;
    }

    public void setClientCategoriesFor(Set<ClientCategory> clientCategoriesFor) {
        this.clientCategoriesFor = clientCategoriesFor;
    }

    @Override
    public String toString() {
        return String.format("Service[id=%d, name=%s, service quality criterias=%s, client categories for=%s]",
                id, name, serviceQualityCriterias, clientCategoriesFor);
    }
}
