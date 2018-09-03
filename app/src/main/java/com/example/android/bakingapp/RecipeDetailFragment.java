package com.example.android.bakingapp;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Recipe;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "**DETAIL ACTIVITY LOG**";

    public Recipe mRecipe;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        /*if (bundle != null){
            mRecipe = bundle.getParcelable("details_recipe");
            Log.i(TAG, "Recipe in onCreate is..." + mRecipe);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.content_recipe_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle != null){
            mRecipe = bundle.getParcelable("details_recipe");
            Log.i(TAG, "Recipe in onCreateView bundle is..." + mRecipe);
        }

        if (mRecipe != null){
            Log.i(TAG, "Recipe in Fragment in IF Statement..." + mRecipe);
            ((TextView) rootView.findViewById(R.id.detail_recipe_name)).setText(mRecipe.getName());
            ((TextView) rootView.findViewById(R.id.detail_recipe_id)).setText("Recipe #" + String.valueOf(mRecipe.getId()) + ":");
        } else {
            Log.i(TAG, "Recipe in Fragment is... nada" + mRecipe);
        }

        return rootView;
    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("details_recipe", recipe);
        fragment.setArguments(args);
        return fragment;
    }

}
