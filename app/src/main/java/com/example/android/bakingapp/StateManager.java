package com.example.android.bakingapp;

import java.util.ArrayList;

import model.Recipe;

class StateManager {
    private static final StateManager ourInstance = new StateManager();

    private ArrayList<Recipe> recipeObjects = new ArrayList<>();

    static StateManager getInstance() {
        return ourInstance;
    }

    private StateManager() {
    }

    public ArrayList<Recipe> getRecipeObjects() {
        return recipeObjects;
    }

    public void setRecipeObjects(Recipe recipe) {
        recipeObjects.add(recipe);
    }
}
