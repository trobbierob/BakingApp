package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.WebService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "**MAIN ACTIVITY LOG**";
    private static final ArrayList<Recipe> recipeObjects = new ArrayList<>();
    private boolean mTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        letsBake();

        RecyclerView recyclerView = findViewById(R.id.recipe_list_rv);
        Log.i(TAG, "Recipe Objects: " + recipeObjects);
        recyclerView.setAdapter(new RecipeListAdapter(this, recipeObjects));
    }

    public void tabletDetector(){

    }

    public void addRecipes(View view) {

    }

    private void letsBake() {
        WebService webService =
                WebService.retrofit.create(WebService.class);

        Call<Recipe[]> call = webService.recipes();
        call.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call,
                                   @NonNull Response<Recipe[]> response) {
                if (response.isSuccessful()){
                    for (Recipe recipe : response.body()){
                        Log.i(TAG, "Recipe in WebService: " + recipe);
                        recipeObjects.add(recipe);
                    }
                    Log.i(TAG, "Recipe Objects in letsBake: " + recipeObjects);
                }
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Log.i(TAG, getString(R.string.onFailure_throwable) + t);
            }
        });
    }
}
