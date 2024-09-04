package org.example.jpaWeekTwo_relationsForb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    // relationer 1:1
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)//alt bliver automatisk eksikveret.
    private PersonDetail personDetail;

    public Person(String name) {
        this.name = name;
    }

    //  relationer 1:M
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Fee> fees = new HashSet<>();

    //relationer M:M
    @ManyToMany
    private Set<Event> events = new HashSet<>();

    //Uni-directional
    public void addEvent(Event event) {
        this.events.add(event);
    }

    //bi-directional update - peger den modsatte vej.

    //man fortæller at den skal sættes på oppe ved persondetail attributten. Så værdien er blevet sat.
    public void addPersonDetail(PersonDetail personDetail){
        this.personDetail = personDetail;
        if(personDetail != null){
            personDetail.setPerson(this);
        }
    }

    public void addFee(Fee fee){
        this.fees.add(fee);
        if(fee != null){
            fee.setPerson(this);
        }
    }

}
