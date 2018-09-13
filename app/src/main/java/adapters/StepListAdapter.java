package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.R;

import java.util.ArrayList;

import model.Step;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    public static final String TAG = "**STEP ADAPTER LOG**";
    private LayoutInflater mInflater;
    private ArrayList<Step> mStepData;
    private Context mContext;

    public StepListAdapter(Context context, ArrayList<Step> stepData) {
        mInflater = LayoutInflater.from(context);
        this.mStepData = stepData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public StepListAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //View mItemView = mInflater.inflate(R.layout.detail_step_items, viewGroup, false);
        View mItemView = mInflater.inflate(R.layout.step_items, viewGroup, false);
        return new StepViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapter.StepViewHolder stepViewHolder, int position) {
        Step currentStep = mStepData.get(position);
        stepViewHolder.stepId.setText(String.valueOf(currentStep.getId()+1));
        stepViewHolder.stepDescription.setText(currentStep.getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mStepData.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        StepListAdapter mAdapter;
        TextView stepId, stepDescription;

        public StepViewHolder(@NonNull View itemView, StepListAdapter adapter) {
            super(itemView);
            stepId = itemView.findViewById(R.id.step_id);
            stepDescription = itemView.findViewById(R.id.step_description);
            this.mAdapter = adapter;
        }
    }
}
