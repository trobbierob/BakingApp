package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.WebService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "**MAIN ACTIVITY LOG**";
    private boolean mTablet;

    RecipeListAdapter mAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recipe_list_rv);

        runOven();
    }

    public void tabletDetector(){

    }

    public void addRecipes(View view) {
        //runOven();
    }

    private void runOven() {
        WebService webService =
                WebService.retrofit.create(WebService.class);

        Call<Recipe[]> call = webService.recipes();
        call.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call,
                                   @NonNull Response<Recipe[]> response) {
                if (response.isSuccessful()){
                    for (Recipe recipe : response.body()){
                        StateManager.getInstance().setRecipeObjects(recipe);
                    }
                    mAdapter = new RecipeListAdapter(MainActivity.this, StateManager.getInstance().getRecipeObjects());
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Log.i(TAG, getString(R.string.onFailure_throwable) + t);
            }
        });
    }
}
