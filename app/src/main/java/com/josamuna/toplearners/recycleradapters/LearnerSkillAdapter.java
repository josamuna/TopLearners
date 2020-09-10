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
import com.josamuna.toplearners.pojo.PojoLearnerSkillLeader;

import java.util.List;

public class LearnerSkillAdapter extends RecyclerView.Adapter<LearnerSkillAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<PojoLearnerSkillLeader> mSkillLeaders;

    public LearnerSkillAdapter(Context context, List<PojoLearnerSkillLeader> skillLeaders) {
        mLayoutInflater = LayoutInflater.from(context);
        mSkillLeaders = skillLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.rv_learner_skill_leader, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PojoLearnerSkillLeader skillLeader = mSkillLeaders.get(position);
        holder.mTextLearnerIqName.setText(skillLeader.getName());
        holder.mTextIqDetails.setText(String.format("%d skill IQ Score, %s.",
                skillLeader.getScore(), skillLeader.getCountry()));
    }

    @Override
    public int getItemCount() {
        return mSkillLeaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextLearnerIqName;
        public final TextView mTextIqDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextLearnerIqName = itemView.findViewById(R.id.text_learner_iq_name);
            mTextIqDetails = itemView.findViewById(R.id.text_learner_iq_details);
        }
    }
}
