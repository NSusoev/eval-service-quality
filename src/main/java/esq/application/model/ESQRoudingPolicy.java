package esq.application.model;

import javax.persistence.*;

/**
 * Created by nsusoev on 14.04.16.
 */

@Entity
@Table(name = "ESQRoudingPolicies")
public class ESQRoudingPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;

    protected ESQRoudingPolicy() {
    }

    public ESQRoudingPolicy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("EvalServiceQualityRoundingPolicy[id = %d, name = %s]", id, name);
    }
}
