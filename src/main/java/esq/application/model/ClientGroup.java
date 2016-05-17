package esq.application.model;

import javax.persistence.*;

/**
 * Группы клиентов
 */

@Entity
@Table(name = "ClientGroups")
public class ClientGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;

    protected ClientGroup() {
    }

    public ClientGroup(String name) {
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
        return String.format("ClientGroup[id=%d, name=%s]", id, name);
    }
}
