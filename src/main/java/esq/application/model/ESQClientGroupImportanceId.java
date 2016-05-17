package esq.application.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nsusoev on 15.04.16.
 */

@Embeddable
public class ESQClientGroupImportanceId implements Serializable {

    @ManyToOne
    private ESQSettingsProfile esqSettingsProfile;
    @ManyToOne
    private ClientGroup clientGroup;
    @ManyToOne
    private ClientCategory clientCategory;

    public ESQSettingsProfile getESQSettingsProfile() {
        return this.esqSettingsProfile;
    }

    public void setESQSettingsProfile(ESQSettingsProfile settingsProfile) {
        this.esqSettingsProfile = settingsProfile;
    }

    public ClientGroup getClientGroup() { return this.clientGroup;
    }

    public void setClientGroup(ClientGroup clientGroup) {
        this.clientGroup = clientGroup;
    }

    public ClientCategory getClientCategory() { return this.clientCategory;
    }

    public void setClientCategory(ClientCategory clientCategory) {
        this.clientCategory = clientCategory;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ESQClientGroupImportanceId that = (ESQClientGroupImportanceId) o;

        if (esqSettingsProfile != null ? !esqSettingsProfile.equals(that.esqSettingsProfile) : that.esqSettingsProfile != null) {
            return false;
        }

        if (clientGroup != null ? !clientGroup.equals(that.clientGroup) : that.clientGroup != null) {
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
        result = 31 * result + (clientGroup != null ? clientGroup.hashCode() : 0);
        result = 31 * result + (clientCategory != null ? clientCategory.hashCode() : 0);
        return result;
    }
}
