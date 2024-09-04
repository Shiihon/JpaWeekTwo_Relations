package org.example.jpaWeekTwo_relationsForb;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class PersonDetail {
    @Id //ingen autogeneration
    private Integer id;
    private String address;
    private int zip;
    private String city;
    private int age;

// relationer 1:1
    @OneToOne
    @MapsId // markerer at den skal tage id fra person og indsætte det i persondetails, så de er identiske.
    private Person person;

    @Builder
    public PersonDetail(String address, int zip, String city, int age) {
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.age = age;
    }
}
