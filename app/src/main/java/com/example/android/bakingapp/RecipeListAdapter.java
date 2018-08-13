package com.example.android.bakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    public static final String TAG = "**RECIPE ADAPTER LOG**";
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<String> mRecipeNames;


    public RecipeListAdapter(Context context, ArrayList<String> mRecipeNames) {
        mInflater = LayoutInflater.from(context);
        Log.i(TAG, mRecipeNames + " are recipe names");
        //this.mContext = context;
        this.mRecipeNames = mRecipeNames;
    }

    @NonNull
    @Override
    public RecipeListAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.rv_item, viewGroup, false);
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        //View mItemView = inflater.inflate(R.layout.rv_item, viewGroup, false);
        return new RecipeViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.RecipeViewHolder recipeViewHolder, int position) {
        String mCurrent = mRecipeNames.get(position);
        Log.i(TAG, mRecipeNames + " are recipe names");
        recipeViewHolder.recipeNameTV.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, mRecipeNames.size() + " is size");
        return mRecipeNames.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder{

        public final TextView recipeNameTV;
        final RecipeListAdapter mAdapter;

        public RecipeViewHolder(@NonNull View itemView, RecipeListAdapter adapter) {
            super(itemView);
            recipeNameTV = (TextView) itemView.findViewById(R.id.recipe_name_tv);
            this.mAdapter = adapter;
        }
    }
}
