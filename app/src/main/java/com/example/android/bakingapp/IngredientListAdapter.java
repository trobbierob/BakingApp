package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Ingredient;

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.IngredientViewHolder> {

    public static final String TAG = "**INGRED ADAPTER LOG**";
    private LayoutInflater mInflater;
    private ArrayList<Ingredient> mIngredientData;

    public IngredientListAdapter(Context context, ArrayList<Ingredient> ingredientData) {
        mInflater = LayoutInflater.from(context);
        this.mIngredientData = ingredientData;
    }

    @NonNull
    @Override
    public IngredientListAdapter.IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.detail_recipe_ingredients_items, viewGroup, false);
        return new IngredientViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListAdapter.IngredientViewHolder ingredientViewHolder, int position) {
        Ingredient currentIngredient = mIngredientData.get(position);
        ingredientViewHolder.iQuantity.setText(String.valueOf(currentIngredient.getQuantity()));
        ingredientViewHolder.iMeasure.setText(currentIngredient.getMeasure());
        ingredientViewHolder.iNgredient.setText(currentIngredient.getIngredient());
    }

    @Override
    public int getItemCount() {
        return mIngredientData.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        TextView iQuantity, iMeasure, iNgredient;
        IngredientListAdapter mAdapter;

        public IngredientViewHolder(@NonNull View itemView, IngredientListAdapter adapter) {
            super(itemView);
            iQuantity = itemView.findViewById(R.id.quantity);
            iMeasure = itemView.findViewById(R.id.measure);
            iNgredient = itemView.findViewById(R.id.ingredient);
            this.mAdapter = adapter;
        }
    }
}
