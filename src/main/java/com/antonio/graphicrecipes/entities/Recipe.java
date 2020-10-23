/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonio.graphicrecipes.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Antonio
 */
@Entity
@Table(name="recipes")
public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="recipe_id")
    private String name;
    private String description;
    
    @Column(name="short_description")
    private String shortDescription;
    private Integer likes;
    
    @ManyToMany
    @JoinTable(name = "users_recipes", joinColumns = @JoinColumn(name = "recipe_id", nullable = false), 
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<User> users = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "category_fk")
    private Category category;
    
    
    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe() {
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category categories) {
        this.category = categories;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        return "Recipe{" + "name=" + name + ", description=" + description + ", likes=" + likes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Recipe other = (Recipe) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }   
}
