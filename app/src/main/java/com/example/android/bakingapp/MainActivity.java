package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.WebService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAINACTIVITY LOG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addRecipes(View view) {

        Bundle bundle = new Bundle();
        bundle.putString(RecipeFragment.RECIPE_KEY, "Passed Message");

        RecipeFragment recipeFragment = new RecipeFragment();
        recipeFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.recipe_fragment_container, recipeFragment)
                .commit();
    }

    private void letsBake() {
        WebService webService =
                WebService.retrofit.create(WebService.class);

        Call<Recipe[]> call = webService.recipes();
        call.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call, @NonNull Response<Recipe[]> response) {
                if (response.isSuccessful()){
                    for (Recipe recipe : response.body()){
                        Log.i(TAG, "Recipe Items: " + recipe.toString());
                        Log.i(TAG, "Recipe Names " + recipe.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Log.i(TAG, "onFailure throwable message: " + t);
            }
        });
    }


}
