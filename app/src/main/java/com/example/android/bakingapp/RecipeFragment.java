package com.example.android.bakingapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import model.Recipe;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    private static final String TAG = "**RECIPE FRAGMENT LOG**";
    public static final String RECIPE_KEY = "recipe_key";

    private Context mContext;

    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance(ArrayList<Recipe> message) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(RecipeFragment.RECIPE_KEY, message);
        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recipe, container,
                false);

        Bundle bundle = getArguments();
        if (bundle != null){
            ArrayList<Recipe> message = bundle.getParcelableArrayList(RECIPE_KEY);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recipe_fragment_rv);
            RecipeListAdapter recipeAdapter = new RecipeListAdapter(getContext(), message);
            recyclerView.setAdapter(recipeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }
}
