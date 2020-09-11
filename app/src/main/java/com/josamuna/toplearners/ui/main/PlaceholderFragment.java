package com.josamuna.toplearners.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.josamuna.toplearners.R;
import com.josamuna.toplearners.Utils;
import com.josamuna.toplearners.pojo.ApiAdapter;
import com.josamuna.toplearners.pojo.PojoLearnerLeader;
import com.josamuna.toplearners.pojo.PojoLearnerSkillLeader;
import com.josamuna.toplearners.recycleradapters.LearnerLeaderAdapter;
import com.josamuna.toplearners.recycleradapters.LearnerSkillAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private List<PojoLearnerLeader> mLearnerLeaders = new ArrayList<>();
    private List<PojoLearnerSkillLeader> mSkillLeaders = new ArrayList<>();
    private View mRoot;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        switch (Objects.requireNonNull(getArguments()).getInt(ARG_SECTION_NUMBER)) {
            case 1: {
                mRoot = inflater.inflate(R.layout.fragment_learner_leader, container, false);
                this.loadLeanerLeaders();

                break;
            }
            case 2: {
                mRoot = inflater.inflate(R.layout.fragment_learner_skill, container, false);
                this.loadLearnerSkill();
                break;
            }
        }
        return mRoot;
    }

    public void loadLearnerSkill() {
        mSkillLeaders.clear();
        final ProgressDialog progressDialog = Utils.getProgressDialog(getContext(), "Please wait");

        Call<List<PojoLearnerSkillLeader>> listCall = ApiAdapter.getClient().getLearnerSkillIqLeader();
        listCall.enqueue(new Callback<List<PojoLearnerSkillLeader>>() {
            @Override
            public void onResponse(Call<List<PojoLearnerSkillLeader>> call, Response<List<PojoLearnerSkillLeader>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    mSkillLeaders.addAll(response.body());
                    setLearnerSkillDataInRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<PojoLearnerSkillLeader>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_skill, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void setLearnerSkillDataInRecyclerView() {
        // Get RecyclerView Reference and Set LayoutManager
        RecyclerView rvLearnerSkill = mRoot.findViewById(R.id.rv_fragment_learner_skill);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvLearnerSkill.setLayoutManager(layoutManager);

        // Load Data in RecyclerView
        final LearnerSkillAdapter adapterSkill = new LearnerSkillAdapter(getContext(), mSkillLeaders);
        rvLearnerSkill.setAdapter(adapterSkill);
    }

    public void loadLeanerLeaders() {
        mLearnerLeaders.clear();
        final ProgressDialog progressDialog = Utils.getProgressDialog(getContext(), "Please wait");

        Call<List<PojoLearnerLeader>> listCall = ApiAdapter.getClient().getLearnerLeader();
        listCall.enqueue(new Callback<List<PojoLearnerLeader>>() {
            @Override
            public void onResponse(Call<List<PojoLearnerLeader>> call, Response<List<PojoLearnerLeader>> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    mLearnerLeaders.addAll(response.body());
                    setLearnerLeadersDataInRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<PojoLearnerLeader>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_leaders, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void setLearnerLeadersDataInRecyclerView() {
        // Get RecyclerView Reference and Set LayoutManager
        RecyclerView rvFragmentLearnerLeader = mRoot.findViewById(R.id.rv_fragment_learner_leader);
        final LinearLayoutManager learnerLayoutManager = new LinearLayoutManager(mRoot.getContext());
        rvFragmentLearnerLeader.setLayoutManager(learnerLayoutManager);

        // Load Data in RecyclerView
        final LearnerLeaderAdapter adapterLearners = new LearnerLeaderAdapter(getContext(), mLearnerLeaders);
        rvFragmentLearnerLeader.setAdapter(adapterLearners);
    }
}