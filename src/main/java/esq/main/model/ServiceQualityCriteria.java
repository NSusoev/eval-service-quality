package esq.main.model;

import javax.persistence.*;
import javax.validation.OverridesAttribute;

/**
 * Created by nsusoev on 08.04.16.
 */

@Entity
@Table(name = "ServiceQualityCriterias")
public class ServiceQualityCriteria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;

    protected ServiceQualityCriteria() {
    }

    public ServiceQualityCriteria(String name) {
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

    @Override
    public String toString() {
        return String.format("ServiceQualityCriteria[id=%d, name=%s]", id, name);
    }

}
