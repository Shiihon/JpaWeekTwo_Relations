package org.example.jpaWeekTwo_tir0309.DAO;

import java.util.Set;

/**
 * Purpose: This is an interface for making a DAO (Data Access Object) that can be used to perform CRUD operations on any entity.
 * Author: Thomas Hartmann
 * @param <T>
 */
interface IDAO<T> {


//    void setEntityManagerFactory(EntityManagerFactory emf);

//    EntityManagerFactory getEntityManagerFactory(); // used for getting emf from super class

    T findById(Object id);

    Set<T> getAll();

    void create(T t);

    void update(T t);
    T getById(Long id);

    void delete(T t);

}