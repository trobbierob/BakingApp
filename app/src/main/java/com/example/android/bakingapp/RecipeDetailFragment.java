package com.example.android.bakingapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Recipe;
import model.Step;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "**DETAIL ACTIVITY LOG**";

    public Recipe mRecipe;
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
        mRecyclerView = rootView.findViewById(R.id.detail_ingredients_rv);
        sRecyclerView = rootView.findViewById(R.id.detail_steps_rv);



        Bundle bundle = getArguments();
        if (bundle != null){
            mRecipe = bundle.getParcelable("details_recipe");
            ((TextView) rootView.findViewById(R.id.detail_recipe_name)).setText(mRecipe.getName());
            ((TextView) rootView.findViewById(R.id.detail_recipe_id)).setText("Recipe #" + String.valueOf(mRecipe.getId()) + ":");

            LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mAdapter = new IngredientListAdapter(rootView.getContext(), mRecipe.getIngredients());
            mRecyclerView.setAdapter(mAdapter);
            mRecyclerView.setLayoutManager(layoutManager);


            LinearLayoutManager sLayoutManager = new LinearLayoutManager(rootView.getContext());
            sLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            sAdapter = new StepListAdapter(rootView.getContext(), mRecipe.getSteps());
            sRecyclerView.setAdapter(sAdapter);
            sRecyclerView.setLayoutManager(sLayoutManager);

            Step step = new Step();
            step.getVideoURL();
            Log.i(TAG, "Step url is: " + step.getVideoURL());
            Log.i(TAG, "Steps are: " + mRecipe.getSteps());
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
