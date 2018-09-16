package com.example.android.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
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


        //List<String> itemNames = new ArrayList<>();
        //List<String> itemNames = {"Android", "iPhone",""};
        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };

        ListView listView = (ListView) findViewById(R.id.widget_listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, values);
        Log.i(TAG, "Not in tablet mode." + values);
        listView.setAdapter(adapter);*/

        Intent intent1 = new Intent(this, WidgetProvider.class);
        //Log.i(TAG, "Get ingredients is: " + recipe.getIngredients());
        intent1.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent1.putExtra("widgetextra", recipe.getIngredients());
        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), WidgetProvider.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(intent1);

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
