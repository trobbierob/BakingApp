package com.example.android.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.Recipe;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    public static final String TAG = "**RECIPE ADAPTER LOG**";
    public static final String RECIPE_ITEM_KEY = "recipe_item_key";


    private LayoutInflater mInflater;
    private ArrayList<Recipe> mRecipeData;

    private Context context;
    private boolean mTablet;

    public RecipeListAdapter(Context context, ArrayList<Recipe> recipeData) {
        mInflater = LayoutInflater.from(context);
        this.mRecipeData = recipeData;
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                                 int i) {
        View mItemView = mInflater.inflate(R.layout.main_recipe_item, viewGroup, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder recipeViewHolder,
                                 int position) {
        Recipe currentRecipe = mRecipeData.get(position);
        recipeViewHolder.recipeNameTV.setText(currentRecipe.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipeData.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView recipeNameTV;
        RecipeListAdapter mAdapter;

        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeNameTV = (TextView) itemView.findViewById(R.id.recipe_name_tv);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            Intent detailActivityIntent = new Intent(view.getContext(), RecipeDetailActivity.class);
            detailActivityIntent.putExtra("current_recipe", mRecipeData.get(mPosition));
            view.getContext().startActivity(detailActivityIntent);
        }
    }
}
