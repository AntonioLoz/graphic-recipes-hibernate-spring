/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.graphicrecipes.dao;

import com.antonio.graphicrecipes.entities.User;

/**
 *
 * @author Antonio
 */
public class UserDAOImplHibernate extends GenericDAOImplHibernate<User, Integer> implements UserDAO{

    @Override
    public User findByLogin(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
