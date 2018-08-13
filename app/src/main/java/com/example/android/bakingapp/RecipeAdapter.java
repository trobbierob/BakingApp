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

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder>{

    public static final String TAG = "RECIPEADAPTER LOG";
    //private Recipe[] mRecipes;
    private ArrayList<String> mRecipes;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecipeAdapter(Context context, ArrayList<String> recipeNames){
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mRecipes = recipeNames;
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = mInflater.inflate(R.layout.rv_item, parent, false);
        //ViewHolder viewHolder = new ViewHolder(itemView, this);
        return null;


    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        //final Recipe recipe = mRecipes[position];
        //final Recipe recipe = mRecipes.get(position);
        //final Recipe recipe = new Recipe();

        Log.i(TAG, "Recipes here?" + mRecipes);

        String recipe = mRecipes.get(position);
        holder.recipeName.setText(recipe);

        //holder.recipeName.setText();

        //holder.mView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //Toast.makeText(mContext, recipe.getName() + " selected.", Toast.LENGTH_SHORT).show();
                //Toast.makeText(mContext, mRecipes + " selected.", Toast.LENGTH_SHORT).show();
            //}
        //});
    }

    @Override
    public int getItemCount() {
        if (mRecipes == null) {
            return 0;
        }
        //return mRecipes.length;
        return mRecipes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView recipeName;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.recipe_name_tv);
        }
    }
}
