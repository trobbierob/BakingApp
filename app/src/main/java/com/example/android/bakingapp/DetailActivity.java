package com.example.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import model.Recipe;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra(RecipeListAdapter.RECIPE_ITEM_KEY);

        Log.i(TAG, "Recipe is: " + recipe);

        DetailFragment detailFragment = DetailFragment.newInstance(recipe);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_detail_fragment, detailFragment)
                .commit();

    }
}
