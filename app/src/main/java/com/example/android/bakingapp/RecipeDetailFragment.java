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


        mRecipe = savedInstanceState.getParcelable("details_recipe");
        Log.i(TAG, "Recipe in Fragment is..." + mRecipe);

        /*if (savedInstanceState.getArguments().containsKey("details_recipe")) {
            mRecipe = getArguments().getParcelable("details_recipe");
            Log.i(TAG, "Recipe in Fragment is..." + mRecipe);
        }*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        //TODO: Show detail info
        if (mRecipe != null){
            ((TextView) rootView.findViewById(R.id.fragment_recipe_detail)).setText(mRecipe.getName());
            Log.i(TAG, "Recipe in Fragment is..." + mRecipe);
        } else {
            Log.i(TAG, "Recipe in Fragment is... nada" + mRecipe);
        }

        return rootView;
    }

    public static RecipeDetailFragment newInstance(Recipe recipe) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();

        Log.i(TAG, "Recipe in Fragment is..." + recipe);

        Bundle args = new Bundle();
        args.putParcelable("details_recipe", recipe);
        fragment.setArguments(args);
        return fragment;
    }

}
