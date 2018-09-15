package com.example.android.bakingapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import adapters.IngredientListAdapter;
import adapters.StepListAdapter;
import model.Recipe;
import model.Step;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "**DETAIL ACTIVITY LOG**";

    public Recipe mRecipe;
    public Step mStep;
    private IngredientListAdapter mAdapter;
    private StepListAdapter sAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView sRecyclerView;

    public RecipeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.content_recipe_detail, container, false);



        Bundle bundle = getArguments();
        if (bundle != null){

            mStep = bundle.getParcelable("details_step");
            ((TextView) rootView.findViewById(R.id.step_description)).setText(String.valueOf(mStep.getDescription()));
            Log.i(TAG, "mStep is: " + mStep);
            Log.i(TAG, "mStep is: " + mStep.getDescription());


        } else {
            throw new AssertionError();
        }

        return rootView;
    }

    public static RecipeDetailFragment newInstance(Step step) {
        Bundle args = new Bundle();
        args.putParcelable("details_step", step);
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
