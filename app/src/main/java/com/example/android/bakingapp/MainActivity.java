package com.example.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.WebService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letsBake();
            }
        });

        letsBake();
    }

    private void letsBake() {
        WebService webService =
                WebService.retrofit.create(WebService.class);

        Call<Recipe[]> call = webService.recipes();
        call.enqueue(new Callback<Recipe[]>() {
            @Override
            public void onResponse(@NonNull Call<Recipe[]> call, @NonNull Response<Recipe[]> response) {
                if (response.isSuccessful()){
                    Log.i(TAG, "onResponse response: " + response);
                    Log.i(TAG, "onResponse call: " + call);

                    for (Recipe recipe : response.body()){
                        Log.i(TAG, "Recipe Items: " + recipe.toString());
                        Log.i(TAG, "Recipe Names " + recipe.getName());
                    }
                }
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Log.i(TAG, "onFailure call: " + call);
                Log.i(TAG, "onFailure throwable: " + t);
            }
        });

        Call<List<Recipe>> callList = webService.recipeList();
        callList.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                Log.i(TAG, "onResponse response: " + response);
                Log.i(TAG, "onResponse call: " + call);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.i(TAG, "onFailure call: " + call);
                Log.i(TAG, "onFailure throwable: " + t);
            }
        });
    }
}
