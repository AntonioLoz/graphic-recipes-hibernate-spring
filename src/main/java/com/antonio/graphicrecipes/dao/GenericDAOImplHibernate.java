/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.graphicrecipes.dao;

import com.antonio.utils.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author Antonio
 * @param <T> Entity
 * @param <E> Id of entities
 */
public class GenericDAOImplHibernate<T, E extends Serializable> implements GenericDAO<T, E> {
    
    private final SessionFactory sessionFactory;
    
    // Un tipo generico es Foo<T>
    // Un tipo parametrizado es, por ejemplo, Foo<Integer>
    private final Class<T> entityType;
    
    public GenericDAOImplHibernate() {
        sessionFactory = HibernateUtil.getSessionFactory();
        
        /**
         * getGenericSuperclass retorna una instancia de ParameteizedType. 
         * Esta clase tiene un metodo, getActualTypeArguments, el cual
         * retorna un array que contiene todos los tipos genericos contenidos 
         * en esta clase
         */
        this.entityType = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T get(E id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T entity = session.get(entityType, id);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public List<T> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("SELECT e FROM " + entityType.getName() + " e");
        List<T> entities = query.list();
        return entities;
    }

    @Override
    public void save(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
