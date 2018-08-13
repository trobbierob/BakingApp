package com.example.android.bakingapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    public static final String TAG = "RECIPEFRAGMENT LOG";
    public static final String RECIPE_KEY = "recipe_key";

    private Context mContext;


    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance(ArrayList<String> message) {

        Log.i(TAG, "Message is: " + message);

        Bundle args = new Bundle();
        args.putStringArrayList(RecipeFragment.RECIPE_KEY, message);

        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle bundle = getArguments();
        if (bundle != null){
            ArrayList<String> message = bundle.getStringArrayList(RECIPE_KEY);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recipe_fragment_rv);
            RecipeListAdapter recipeAdapter = new RecipeListAdapter(getContext(), message);
            recyclerView.setAdapter(recipeAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return view;
    }
}
