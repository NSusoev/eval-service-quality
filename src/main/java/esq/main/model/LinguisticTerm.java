package esq.main.model;

import javax.persistence.*;

/**
 * Created by nsusoev on 05.04.16.
 */

@Entity
@Table(name = "LinguisticTerms")
public class LinguisticTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private byte value;

    protected LinguisticTerm() {
    }

    public LinguisticTerm(String name, byte value) {
        this.name = name;
        this.value = value;
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

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("LinguisticTerm[id=%d, name=%s, value=%d]", id, name, value);
    }

}
