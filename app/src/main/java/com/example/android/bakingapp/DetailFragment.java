package com.example.android.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import model.Recipe;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private static final String TAG = "**DETAIL FRAGMENT LOG**";
    public static final String DETAILS_KEY = "details_key";

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Recipe message) {
        Bundle args = new Bundle();
        args.putParcelable(DetailFragment.DETAILS_KEY, message);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_detail, container,
                false);

        TextView textView = view.findViewById(R.id.detail_recipe_name);
        Bundle bundle = getArguments();

        if (bundle != null){
            Recipe message = bundle.getParcelable(DETAILS_KEY);

            textView.setText(message.getName());
        }
        return view;
    }

}
