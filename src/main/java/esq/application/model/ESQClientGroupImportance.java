package esq.application.model;

import javax.persistence.*;

/**
 * Важности групп клиентов при расчёте оценки
 */

@Entity
@Table(name = "ESQClientGroupImportances")
public class ESQClientGroupImportance {

    @EmbeddedId
    private ESQClientGroupImportanceId pk = new ESQClientGroupImportanceId();
    @ManyToOne
    private LinguisticTerm clientGroupImportance;

    protected ESQClientGroupImportance() {
    }

    public ESQClientGroupImportance(LinguisticTerm clientGroupImportance) {
        this.clientGroupImportance = clientGroupImportance;
    }

    public ESQClientGroupImportanceId getPk() {
        return this.pk;
    }

    public void setPk(ESQClientGroupImportanceId pk) {
        this.pk = pk;
    }

    @Transient
    public ESQSettingsProfile getESQSettingsProfile() {
        return getPk().getESQSettingsProfile();
    }

    @Transient
    public ClientGroup getClientGroup() {
        return getPk().getClientGroup();
    }

    @Transient
    public ClientCategory getClientCategory() {
        return getPk().getClientCategory();
    }

    public LinguisticTerm getClientGroupImportance() {
        return this.clientGroupImportance;
    }

    public void setClientGroupImportance(LinguisticTerm clientGroupImportance) {
        this.clientGroupImportance = clientGroupImportance;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ESQClientGroupImportance that = (ESQClientGroupImportance) o;

        if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
