/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.graphicrecipes.dao;

import com.antonio.graphicrecipes.entities.Recipe;
import java.util.List;

/**
 *
 * @author Antonio
 */
public class RecipeDAOImplHibernate extends GenericDAOImplHibernate<Recipe, Integer> implements RecipeDAO{

    @Override
    public Recipe findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> findByCategory(String category) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
