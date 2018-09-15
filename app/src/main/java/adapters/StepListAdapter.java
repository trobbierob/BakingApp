package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.RecipeDetailActivity;
import com.example.android.bakingapp.RecipeDetailFragment;
import com.example.android.bakingapp.RecipeStepActivity;

import java.util.ArrayList;

import model.Step;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    public static final String TAG = "**STEP ADAPTER LOG**";
    private LayoutInflater mInflater;
    private ArrayList<Step> mStepData;
    private Context mContext;
    private boolean mTablet;

    public StepListAdapter(Context context, ArrayList<Step> stepData) {
        mInflater = LayoutInflater.from(context);
        this.mStepData = stepData;
        this.mContext = context;
    }

    public StepListAdapter(Context context, ArrayList<Step> stepData, boolean tablet) {
        mInflater = LayoutInflater.from(context);
        this.mStepData = stepData;
        this.mContext = context;
        this.mTablet = tablet;
    }

    @NonNull
    @Override
    public StepListAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.step_items, viewGroup, false);
        return new StepViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapter.StepViewHolder stepViewHolder, int position) {
        Step currentStep = mStepData.get(position);
        stepViewHolder.stepId.setText(String.valueOf(String.valueOf(currentStep.getId()+1)));
        stepViewHolder.stepDescription.setText(currentStep.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepData.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        StepListAdapter mAdapter;
        TextView stepId, stepDescription;

        public StepViewHolder(@NonNull View itemView, StepListAdapter adapter) {
            super(itemView);
            stepId = itemView.findViewById(R.id.step_id);
            stepDescription = itemView.findViewById(R.id.step_description);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            /*Intent stepDetailActivityIntent = new Intent(view.getContext(), StepDetailActivity.class);
            stepDetailActivityIntent.putExtra("current_step_data", mStepData.get(mPosition));
            view.getContext().startActivity(stepDetailActivityIntent);*/


            if (mTablet){
                RecipeDetailFragment fragment =
                        RecipeDetailFragment.newInstance(mStepData.get(mPosition));
                ((RecipeStepActivity) mContext).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipe_detail_container, fragment)
                        .addToBackStack(null)
                        .commit();

            } else {
                Intent detailActivityIntent = new Intent(view.getContext(), RecipeDetailActivity.class);
                detailActivityIntent.putExtra("current_step_data", mStepData.get(mPosition));
                view.getContext().startActivity(detailActivityIntent);
            }

            mAdapter.notifyDataSetChanged();
        }
    }
}
