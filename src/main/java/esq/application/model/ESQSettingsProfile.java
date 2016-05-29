package esq.application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Профили настроек расчёта оценки(оценки важностей от экспертов)
 */

@Entity
@Table(name = "ESQSettingsProfiles")
public class ESQSettingsProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // название профиля
    @Column(unique = true, nullable = false)
    private String name;
    // важности категорий
    @OneToMany(mappedBy = "pk.clientCategory")
    private Set<ESQClientCategoryImportance> esqClientCategoryImportances = new HashSet<ESQClientCategoryImportance>();
    // важности групп
    @OneToMany(mappedBy = "pk.clientGroup")
    private Set<ESQClientGroupImportance> esqClientGroupImportances = new HashSet<ESQClientGroupImportance>();
    // политика округления
    @OneToOne
    private ESQRoundingPolicy esqRoundingPolicy;

    protected ESQSettingsProfile() {
    }

    public ESQSettingsProfile(String name) {
        this.name = name;
    }

    public ESQSettingsProfile(String name, ESQRoundingPolicy esqRoundingPolicy) {
        this.name = name;
        this.esqRoundingPolicy = esqRoundingPolicy;
    }

    public ESQSettingsProfile(String name, ESQRoundingPolicy esqRoundingPolicy,
                              Set<ESQClientCategoryImportance> esqClientCategoryImportances) {
        this.name = name;
        this.esqRoundingPolicy = esqRoundingPolicy;
        this.esqClientCategoryImportances = esqClientCategoryImportances;
    }

    public ESQSettingsProfile(String name, ESQRoundingPolicy esqRoundingPolicy,
                              Set<ESQClientCategoryImportance> esqClientCategoryImportances,
                              Set<ESQClientGroupImportance> esqClientGroupImportances) {
        this.name = name;
        this.esqRoundingPolicy = esqRoundingPolicy;
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

    public ESQRoundingPolicy getEsqRoundingPolicy() {
        return this.esqRoundingPolicy;
    }

    public void setEsqRoundingPolicy(ESQRoundingPolicy esqRoundingPolicy) {
        this.esqRoundingPolicy = esqRoundingPolicy;
    }

    @Override
    public String toString() {
        return String.format("ESQSettingsProfile[id = %d, name = %s, category importances = %s]", id, name, esqClientCategoryImportances);
    }
}
