package esq.application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nsusoev on 08.04.16.
 */

@Entity
@Table(name = "ESQSettingsProfiles")
public class ESQSettingsProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "pk.clientCategory")
    private Set<ESQClientCategoryImportance> esqClientCategoryImportances = new HashSet<ESQClientCategoryImportance>();
    @OneToMany(mappedBy = "pk.clientGroup")
    private Set<ESQClientGroupImportance> esqClientGroupImportances = new HashSet<ESQClientGroupImportance>();
    @OneToOne
    private ESQRoudingPolicy esqRoudingPolicy;

    protected ESQSettingsProfile() {
    }

    public ESQSettingsProfile(String name) {
        this.name = name;
    }

    public ESQSettingsProfile(String name, ESQRoudingPolicy esqRoudingPolicy) {
        this.name = name;
        this.esqRoudingPolicy = esqRoudingPolicy;
    }

    public ESQSettingsProfile(String name, ESQRoudingPolicy esqRoudingPolicy,
                              Set<ESQClientCategoryImportance> esqClientCategoryImportances) {
        this.name = name;
        this.esqRoudingPolicy = esqRoudingPolicy;
        this.esqClientCategoryImportances = esqClientCategoryImportances;
    }

    public ESQSettingsProfile(String name, ESQRoudingPolicy esqRoudingPolicy,
                              Set<ESQClientCategoryImportance> esqClientCategoryImportances,
                              Set<ESQClientGroupImportance> esqClientGroupImportances) {
        this.name = name;
        this.esqRoudingPolicy = esqRoudingPolicy;
        this.esqClientCategoryImportances = esqClientCategoryImportances;
        this.esqClientGroupImportances = esqClientGroupImportances;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ESQClientCategoryImportance> getEsqClientCategoryImportances() {
        return this.esqClientCategoryImportances;
    }

    public void setEsqClientCategoryImportances(Set<ESQClientCategoryImportance> categoryImportances) {
        this.esqClientCategoryImportances = categoryImportances;
    }

    public Set<ESQClientGroupImportance> getEsqClientGroupImportances() {
        return this.esqClientGroupImportances;
    }

    public void setEsqClientGroupImportances(Set<ESQClientGroupImportance> groupImportances) {
        this.esqClientGroupImportances = groupImportances;
    }

    public ESQRoudingPolicy getEsqRoudingPolicy() {
        return this.esqRoudingPolicy;
    }

    public void setEsqRoudingPolicy(ESQRoudingPolicy esqRoudingPolicy) {
        this.esqRoudingPolicy = esqRoudingPolicy;
    }

    @Override
    public String toString() {
        return String.format("ESQSettingsProfile[id = %d, name = %s, category importances = %s]", id, name, esqClientCategoryImportances);
    }
}
