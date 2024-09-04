package org.example.jpaWeekTwo_tir0309.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    @OneToMany(mappedBy = "adress")
    private Set<Employee> employees = new HashSet<>();

    @Builder
    public Adress(String street) {
        this.street = street;
    }
    public void addEmployee(Employee employee) {
        this.employees.add(employee);
        employee.setAdress(this);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
        employee.setAdress(null);
    }
}
