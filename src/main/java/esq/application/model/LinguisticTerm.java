package esq.application.model;

import javax.persistence.*;

/**
 * Лингвистические термы, по котором оцениваются услуги
 */

@Entity
@Table(name = "LinguisticTerms")
public class LinguisticTerm implements Comparable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // название термы
    @Column(unique = true, nullable = false)
    private String name;

    protected LinguisticTerm() {
    }

    public LinguisticTerm(String name) {
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

    public int compareTo(Object obj) {
        LinguisticTerm term = (LinguisticTerm) obj;

        if (term.id > this.id) {
            return 1;
        } else if (term.id < this.id) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return String.format("LinguisticTerm[id = %d, name = %s]", id, name);
    }

}
