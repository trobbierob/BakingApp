package com.example.android.bakingapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.ArrayList;

import model.Step;

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.StepViewHolder> {

    public static final String TAG = "**STEP ADAPTER LOG**";
    private LayoutInflater mInflater;
    private ArrayList<Step> mStepData;
    private Context mContext;
    private PlayerView playerView;

    public StepListAdapter(Context context, ArrayList<Step> stepData) {
        mInflater = LayoutInflater.from(context);
        this.mStepData = stepData;
        this.mContext = context;
    }

    @NonNull
    @Override
    public StepListAdapter.StepViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.detail_step_items, viewGroup, false);
        return new StepViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull StepListAdapter.StepViewHolder stepViewHolder, int position) {
        Step currentStep = mStepData.get(position);
        stepViewHolder.sId.setText(String.valueOf(currentStep.getId()));
        stepViewHolder.sShortDescription.setText(currentStep.getShortDescription());
        stepViewHolder.sDescription.setText(currentStep.getDescription());
        //stepViewHolder.sVideoUrl.setText(currentStep.getVideoURL());
        ///initializePlayer(currentStep.getVideoURL());

    }

    private void initializePlayer(String media_url) {

        long playbackPosition = 0;
        int currentWindow = 0;
        boolean playWhenReady = true;

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(mContext),
                new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);


        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        Uri uri = Uri.parse(media_url);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("Baking-App")).
                createMediaSource(uri);
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    public int getItemCount() {
        return mStepData.size();
    }

    public class StepViewHolder extends RecyclerView.ViewHolder {

        TextView sId, sShortDescription, sDescription, sVideoUrl, sThumbnail;
        StepListAdapter mAdapter;

        //PlayerView playerView;

        public StepViewHolder(@NonNull View itemView, StepListAdapter adapter) {
            super(itemView);
            sId = itemView.findViewById(R.id.step_number);
            sShortDescription = itemView.findViewById(R.id.step_description_title);
            sDescription = itemView.findViewById(R.id.step_description);
            //sVideoUrl = itemView.findViewById(R.id.step_video_url);
            //playerView = itemView.findViewById(R.id.step_video_url);
            sThumbnail = itemView.findViewById(R.id.step_thumbnail_url);
            this.mAdapter = adapter;
        }
    }
}
