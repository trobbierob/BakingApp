package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import adapters.IngredientListAdapter;
import adapters.StepListAdapter;
import model.Recipe;

public class RecipeStepActivity extends AppCompatActivity {

    private static final String TAG = "**RESTEP ACTIVITY LOG**";
    private boolean mTablet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.recipe_detail_container) != null){
            mTablet = true;
        } else {
            Log.i(TAG, "Not in tablet mode.");
        }

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("current_recipe");

        TextView recipeName = (TextView) findViewById(R.id.recipe_name_tv);
        RecyclerView ingredientsRv = (RecyclerView) findViewById(R.id.detail_ingredients_rv);
        RecyclerView stepsRv = (RecyclerView) findViewById(R.id.detail_steps_rv);

        recipeName.setText(recipe.getName());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        IngredientListAdapter mAdapter = new IngredientListAdapter(this, recipe.getIngredients());
        ingredientsRv.setAdapter(mAdapter);
        ingredientsRv.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        StepListAdapter sAdapter = new StepListAdapter(this, recipe.getSteps(), mTablet);
        stepsRv.setAdapter(sAdapter);
        stepsRv.setLayoutManager(layoutManager2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
