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
import com.example.android.bakingapp.StepDetailActivity;

import java.util.ArrayList;

import model.Step;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    public static final String TAG = "**STEP ADAPTER LOG**";
    private LayoutInflater mInflater;
    private ArrayList<Step> mStepData;
    private Context mContext;
    //Step currentStep;
    String currentDescription, currentVideoUrl, currentThumbnail;

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

    public interface OnItemClickListener {
        void onItemClick(Step item);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapter.StepViewHolder stepViewHolder, int position) {
        Step currentStep = mStepData.get(position);
        stepViewHolder.stepId.setText(String.valueOf(currentStep.getId()));
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
            int mPositon = getLayoutPosition();

            Intent stepDetailActivityIntent = new Intent(view.getContext(), StepDetailActivity.class);
            stepDetailActivityIntent.putExtra("current_description", mStepData.get(mPositon).getDescription());
            stepDetailActivityIntent.putExtra("current_thumbnail", mStepData.get(mPositon).getThumbnailURL());
            stepDetailActivityIntent.putExtra("current_video_url", mStepData.get(mPositon).getVideoURL());
            view.getContext().startActivity(stepDetailActivityIntent);

            mAdapter.notifyDataSetChanged();
        }
    }
}
