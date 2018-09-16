package com.example.android.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import model.Ingredient;

public class WidgetProvider extends AppWidgetProvider {

    private static final String TAG = "WidgetProvider";

    Ingredient ingredient = new Ingredient();

    String state;

    private static final String ACTION_CUSTOM_BROADCAST =
            "ACTION_CUSTOM_BROADCAST";


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        //intent.getExtras().get("facts");
        Log.i(TAG, "Action: " + intent.getAction());

        Log.i(TAG, "Action: " + intent.getExtras().get("facts"));
    }

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        Log.i(TAG, "onUpdate");

        String ingredientTitle = "Ingredients";

        Ingredient ingredient = new Ingredient();

        Log.i(TAG, "onUpdate" + ingredient.getIngredient());

        for (int widgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(
                    context.getPackageName(), R.layout.widget_layout);
            remoteViews.setTextViewText(R.id.widget_ingredients_tv, ingredientTitle);

            Intent intent = new Intent(context, WidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, remoteViews);

        }

    }

}
