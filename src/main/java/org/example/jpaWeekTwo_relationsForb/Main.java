package org.example.jpaWeekTwo_relationsForb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.persistence.HibernateConfig;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Dolphin!");

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        try (EntityManager em = emf.createEntityManager()) {

            Person p1 = new Person("Hanzi");
            PersonDetail pd1 = new PersonDetail("Kongensgade 35", 2700, "København V", 25);

            p1.addPersonDetail(pd1);
            Fee f1 = new Fee(125, LocalDate.of(2024, 06, 22));
            Fee f2 = new Fee(200, LocalDate.of(2024, 10, 5));

            p1.addFee(f1);
            p1.addFee(f2);

            Event e1 = new Event("DM Senior", LocalDate.now());
            Event e2 = new Event("Sjællands mesterskaber", LocalDate.of(2024, 01, 10));
            p1.addEvent(e1);
            p1.addEvent(e2);
            em.persist(e1);
            em.persist(e2); // kan også lave en cascade persist inde i event klassen, istedet for at persiste herinde.

            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
        }

    }
}
