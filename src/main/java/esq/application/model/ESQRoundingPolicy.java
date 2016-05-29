package esq.application.model;

import javax.persistence.*;

/**
 * Политика округления при расчёте оценки
 */

@Entity
@Table(name = "ESQRoudingPolicies")
public class ESQRoundingPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // имя политики округления
    @Column(unique = true, nullable = false)
    private String name;

    protected ESQRoundingPolicy() {
    }

    public ESQRoundingPolicy(String name) {
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
