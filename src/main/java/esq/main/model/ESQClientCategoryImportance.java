package esq.main.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nsusoev on 14.04.16.
 */

@Entity
@Table(name = "ESQClientCategoryImportances")
public class ESQClientCategoryImportance implements Serializable {

    @EmbeddedId
    private ESQClientCategoryImportanceId pk = new ESQClientCategoryImportanceId();
    @ManyToOne
    private LinguisticTerm clientCategoryImportance;

    protected ESQClientCategoryImportance() {
    }

    public ESQClientCategoryImportance(LinguisticTerm clientCategoryImportance) {
        this.clientCategoryImportance = clientCategoryImportance;
    }

    public ESQClientCategoryImportanceId getPk() {
        return this.pk;
    }

    public void setPk(ESQClientCategoryImportanceId pk) {
        this.pk = pk;
    }

    @Transient
    public ESQSettingsProfile getESQSettingsProfile() {
        return getPk().getESQSettingsProfile();
    }

    @Transient
    public ClientCategory getClientCategory() {
        return getPk().getClientCategory();
    }

    public LinguisticTerm getClientCategoryImportanceImportance() {
        return this.clientCategoryImportance;
    }

    public void setClientCategoryImportance(LinguisticTerm clientCategoryImportance) {
        this.clientCategoryImportance = clientCategoryImportance;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ESQClientCategoryImportance that = (ESQClientCategoryImportance) o;

        if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }
}
