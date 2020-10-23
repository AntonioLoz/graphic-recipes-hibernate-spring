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
public interface RecipeDAO extends GenericDAO<Recipe, Integer>{
    Recipe findByName(String name);
    List<Recipe> findByCategory(String category);
}
