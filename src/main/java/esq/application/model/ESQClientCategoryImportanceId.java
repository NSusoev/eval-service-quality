package esq.application.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Промежуточная сущность между таблицей профилей настроек расчёта
 * и таблицей важностей категорий клиентов
 */

@Embeddable
public class ESQClientCategoryImportanceId implements Serializable {

    @ManyToOne
    private ESQSettingsProfile esqSettingsProfile;
    @ManyToOne
    private ClientCategory clientCategory;

    public ESQSettingsProfile getESQSettingsProfile() {
        return this.esqSettingsProfile;
    }

    public void setESQSettingsProfile(ESQSettingsProfile settingsProfile) {
        this.esqSettingsProfile = settingsProfile;
    }

    public ClientCategory getClientCategory() {
        return this.clientCategory;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ESQClientCategoryImportanceId that = (ESQClientCategoryImportanceId) o;

        if (esqSettingsProfile != null ? !esqSettingsProfile.equals(that.esqSettingsProfile) : that.esqSettingsProfile != null) {
            return false;
        }

        if (clientCategory != null ? !clientCategory.equals(that.clientCategory) : that.clientCategory != null) {
            return false;
        }

        return true;
    }

    public int hashCode() {
        int result;
        result = (esqSettingsProfile != null ? esqSettingsProfile.hashCode() : 0);
        result = 31 * result + (clientCategory != null ? clientCategory.hashCode() : 0);
        return result;
    }


}
