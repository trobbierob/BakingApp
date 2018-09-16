package com.example.android.bakingapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import adapters.RecipeListAdapter;
import model.Recipe;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.WebService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "**MAIN ACTIVITY LOG**";
    private boolean mTablet = false;

    private RecipeListAdapter mAdapter;
    private RecyclerView mRecyclerView;

    WidgetProvider wp;

    private static final String ACTION_CUSTOM_BROADCAST =
            "ACTION_CUSTOM_BROADCAST";

    private WidgetProvider mReceiver = new WidgetProvider();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recipe_list_rv);

        /**
         * If there are no recipes listed, get them
         * If there are recipes persist the data during an orientation change
         **/
        if (StateManager.getInstance().getRecipeObjects().isEmpty()) {
            runOven();
        } else {
            savedInstanceState.putParcelableArrayList("recipes",
                    StateManager.getInstance().getRecipeObjects());
        }


        wp = new WidgetProvider();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(wp, filter);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));


    }

    @Override
    protected void onDestroy() {
        //unregisterReceiver(wp);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelableArrayList("recipes");
        mAdapter = new RecipeListAdapter(MainActivity.this,
                savedInstanceState.<Recipe>getParcelableArrayList("recipes"));
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This is the runOven method which makes a call to the WebService interface
     * to retrieve JSON data of the recipe lists.
     */
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

                    sendCustomBroadcast();

                    mAdapter = new RecipeListAdapter(MainActivity.this,
                            StateManager.getInstance().getRecipeObjects(), mTablet);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<Recipe[]> call, Throwable t) {
                Log.i(TAG, getString(R.string.onFailure_throwable) + t);
            }
        });
    }

    private void sendCustomBroadcast() {

        Intent customBroadcast = new Intent(getApplicationContext(), WidgetProvider.class);
        customBroadcast.putExtra("facts","b");
        sendBroadcast(customBroadcast);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcast);

    }
}
