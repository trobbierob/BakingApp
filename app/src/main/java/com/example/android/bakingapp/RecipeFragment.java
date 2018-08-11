package com.example.android.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeFragment extends Fragment {

    public static final String RECIPE_KEY = "recipe_key";


    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        Bundle bundle = getArguments();
        if (bundle != null){
            String message = bundle.getString(RECIPE_KEY);
            TextView textView = (TextView) view.findViewById(R.id.temp_frag_tv);
            textView.setText(message);
        }

        return view;
    }

}
