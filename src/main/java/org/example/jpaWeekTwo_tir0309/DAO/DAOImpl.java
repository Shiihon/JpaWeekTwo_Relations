package org.example.jpaWeekTwo_tir0309.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.example.jpaWeekTwo_tir0309.entities.Adress;
import org.example.jpaWeekTwo_tir0309.entities.Employee;
import org.example.persistence.HibernateConfig;

import java.util.Set;

public class DAOImpl implements IDAO<Employee> {
    private final EntityManagerFactory emf;

    public DAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Employee findById(Object id) {
        return null;
    }

    @Override
    public Set<Employee> getAll() {
        return Set.of();
    }

    @Override
    public void create(Employee emp) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Adress adress = emp.getAdress();

            if (adress != null) { // ser om adressen allerede er i DB.
                String street = adress.getStreet();
                TypedQuery<Adress> query = em.createQuery("SELECT a FROM Adress a WHERE a.street = :street", Adress.class);
                query.setParameter("street", street);
                try {
                    Adress found = query.getSingleResult();
                    emp.setAdress(found);
                } catch (NoResultException e) {
                    em.persist(adress);
                }
            }
            em.persist(emp);
            em.getTransaction().commit();
        }

    }

    @Override
    public void update(Employee employee) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            if (employee.getId() != null) {
                try {
                    Employee foundEmp = getById(employee.getId());

                    if (employee.getName() != null) {
                        foundEmp.setName(employee.getName());
                    }
                    if (employee.getAdress() != null) {
                        foundEmp.setAdress(employee.getAdress());
                    }
                } catch (NoResultException e) {
                    System.out.println("Employee with id " + employee.getId() + " not found");
                }
            }
            em.getTransaction().commit();
        }
    }

    @Override
    public Employee getById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Employee.class, id);
        } catch (NoResultException e) {
            System.out.println("No employee with id " + id + " found");
            return null;
        }
    }

    @Override
    public void delete(Employee employee) {

    }

    public static void main(String[] args) {
        DAOImpl dao = new DAOImpl(HibernateConfig.getEntityManagerFactory());
       /* Employee e1 = Employee.builder()
                .name("Ida")
                .build();
        Adress adress = Adress.builder()
                .street("Kongensgade 3")
                .build();
        adress.addEmployee(e1);
        dao.create(e1);*/

        Employee foundEmp = dao.getById(4L);
        foundEmp.setName("Jørgen");
        dao.update(foundEmp);
    }
}
