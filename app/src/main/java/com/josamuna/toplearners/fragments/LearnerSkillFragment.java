package com.josamuna.toplearners.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.josamuna.toplearners.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class LearnerSkillFragment extends Fragment {

    public LearnerSkillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_learner_skill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

//    private List<PojoLearnerSkillLeader> loadLeanerSkill() {
//        List<PojoLearnerSkillLeader> leaders = new ArrayList<>();
//
//        Call<List<PojoLearnerSkillLeader>> listCall = ApiAdapter.getClient().getLearnerSkillIqLeader();
//        listCall.enqueue(new Callback<List<PojoLearnerSkillLeader>>() {
//            @Override
//            public void onResponse(Call<List<PojoLearnerSkillLeader>> call, Response<List<PojoLearnerSkillLeader>> response) {
//                if(response.isSuccessful()) {
//                    leaders.addAll(response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<PojoLearnerSkillLeader>> call, Throwable t) {
//                Toast.makeText(getContext(), R.string.toast_fail_load_learner_skill, Toast.LENGTH_LONG).show();
//            }
//        });
//        return leaders;
//    }
}