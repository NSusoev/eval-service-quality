package esq.application.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Категории клиентов
 */

@Entity
@Table(name = "ClientCategories")
public class ClientCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany
    private Set<ClientGroup> clientGroups = new HashSet<ClientGroup>();

    protected ClientCategory() {
    }

    public ClientCategory(String name) {
        this.name = name;
    }

    public ClientCategory(long id, String name) {
        this.id = id;
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

    public Set<ClientGroup> getClientGroups() {
        return this.clientGroups;
    }

    public void setClientGroups(Set<ClientGroup> clientGroups) {
        this.clientGroups = clientGroups;
    }

    @Override
    public String toString() {
        return String.format("ClientCategory[id=%d, name=%s, client groups=%s]", id, name, clientGroups);
    }
}
