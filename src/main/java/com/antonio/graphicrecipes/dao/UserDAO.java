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
public interface UserDAO extends GenericDAO<User, Integer>{
    User findByLogin(String login);
}
