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

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private List<PojoLearnerLeader> mLearnerLeaders = new ArrayList<>();
    private List<PojoLearnerSkillLeader> mSkillLeaders = new ArrayList<>();
    private View mRoot;
    private RecyclerView mRvFragmentLearnerLeader;
    private RecyclerView mRvLearnerSkill;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
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

        switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
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

//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return mRoot;
    }

    public void loadLearnerSkill() {
        mSkillLeaders.clear();
        final ProgressDialog progressDialog = Utils.getProgressDialog(getContext(), "Please wait");

        ApiAdapter.getClient().getLearnerSkillIqLeader(new Callback<List<PojoLearnerSkillLeader>>() {
            @Override
            public void success(List<PojoLearnerSkillLeader> pojoLearnerSkillLeaders, Response response) {
                progressDialog.dismiss();
                mSkillLeaders.addAll(pojoLearnerSkillLeaders);
                setLearnerSkillDataInRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_skill, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void setLearnerSkillDataInRecyclerView() {
        // Get RecyclerView Reference and Set LayoutManager
        mRvLearnerSkill = mRoot.findViewById(R.id.rv_fragment_learner_skill);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRvLearnerSkill.setLayoutManager(layoutManager);

        // Load Data in RecyclerView
        final LearnerSkillAdapter adapterSkill = new LearnerSkillAdapter(getContext(), mSkillLeaders);
        mRvLearnerSkill.setAdapter(adapterSkill);
    }

    public void loadLeanerLeaders() {
        mLearnerLeaders.clear();
        final ProgressDialog progressDialog = Utils.getProgressDialog(getContext(), "Please wait");

        ApiAdapter.getClient().getLearnerLeader(new Callback<List<PojoLearnerLeader>>() {
            @Override
            public void success(List<PojoLearnerLeader> pojoLearnerLeaders, Response response) {
                progressDialog.dismiss();
                mLearnerLeaders.addAll(pojoLearnerLeaders);
                setLearnerLeadersDataInRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_leaders, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

//    private ProgressDialog getProgressDialog() {
//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please wait");
//        progressDialog.show();
//        return progressDialog;
//    }

    private void setLearnerLeadersDataInRecyclerView() {
        // Get RecyclerView Reference and Set LayoutManager
        mRvFragmentLearnerLeader = mRoot.findViewById(R.id.rv_fragment_learner_leader);
        final LinearLayoutManager learnerLayoutManager = new LinearLayoutManager(mRoot.getContext());
        mRvFragmentLearnerLeader.setLayoutManager(learnerLayoutManager);

        // Load Data in RecyclerView
        final LearnerLeaderAdapter adapterLearners = new LearnerLeaderAdapter(getContext(), mLearnerLeaders);
        mRvFragmentLearnerLeader.setAdapter(adapterLearners);
    }
}