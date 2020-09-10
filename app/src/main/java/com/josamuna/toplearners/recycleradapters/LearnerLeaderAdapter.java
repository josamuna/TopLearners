package com.josamuna.toplearners.recycleradapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.josamuna.toplearners.R;
import com.josamuna.toplearners.pojo.PojoLearnerLeader;

import java.util.List;

public class LearnerLeaderAdapter extends RecyclerView.Adapter<LearnerLeaderAdapter.ViewHolder> {
    private final List<PojoLearnerLeader> mPojoLearnerLeaders;
    private final LayoutInflater mLayoutInflater;

    public LearnerLeaderAdapter(Context context, List<PojoLearnerLeader> pojoLearnerLeaders) {
        mPojoLearnerLeaders = pojoLearnerLeaders;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.rv_learner_leader_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PojoLearnerLeader pojoLearnerLeader = mPojoLearnerLeaders.get(position);
        holder.mTextLearnerName.setText(pojoLearnerLeader.getName());
        holder.mTextLearnerDetails.setText(String.format("%d learning hours, %s.",
                pojoLearnerLeader.getHours(), pojoLearnerLeader.getCountry()));
    }

    @Override
    public int getItemCount() {
        return mPojoLearnerLeaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextLearnerName;
        public final TextView mTextLearnerDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextLearnerName = itemView.findViewById(R.id.text_learner_name);
            mTextLearnerDetails = itemView.findViewById(R.id.text_learner_details);
        }
    }

}
