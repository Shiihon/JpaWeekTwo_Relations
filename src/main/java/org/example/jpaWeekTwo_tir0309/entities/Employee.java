package org.example.jpaWeekTwo_tir0309.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.PERSIST) //når en ny employee oprettes, og emp allerede har en adresse vil den automatisk blive persisteret.
    private Adress adress;

//Owning side, annotation skal ske i den modsatte.
}
