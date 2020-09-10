package com.josamuna.toplearners.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.josamuna.toplearners.R;
import com.josamuna.toplearners.pojo.ApiAdapter;
import com.josamuna.toplearners.pojo.PojoLearnerLeader;
import com.josamuna.toplearners.pojo.PojoLearnerSkillLeader;
import com.josamuna.toplearners.recycleradapters.LearnerSkillAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnerSkillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnerSkillFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnerSkillFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnerSkillFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnerSkillFragment newInstance(String param1, String param2) {
        LearnerSkillFragment fragment = new LearnerSkillFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

//        // Get RecyclerView Reference
//        final RecyclerView rvLearnerSkill = view.findViewById(R.id.rv_fragment_learner_skill);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rvLearnerSkill.setLayoutManager(layoutManager);
//
//        // Load Data into RecyclerView
////        List<PojoLearnerSkillLeader> leaders;
//        List<PojoLearnerSkillLeader> leaders = new ArrayList<>();
//        PojoLearnerSkillLeader s1 = new PojoLearnerSkillLeader();
//        s1.setName("Isamuns Nkembo");
//        s1.setScore(300);
//        s1.setCountry("DRC");
//        s1.setBadgeUrl("Badge Score1");
//        PojoLearnerSkillLeader s2 = new PojoLearnerSkillLeader();
//        s2.setName("Feza Hozana");
//        s2.setScore(300);
//        s2.setCountry("Angola");
//        s2.setBadgeUrl("Badge Score2");
//        leaders.add(s1);
//        leaders.add(s2);
//
//        leaders = loadLeanerSkill();
//
//        final LearnerSkillAdapter adapter = new LearnerSkillAdapter(getContext(), leaders);
//        rvLearnerSkill.setAdapter(adapter);
    }

    private List<PojoLearnerSkillLeader> loadLeanerSkill() {
        List<PojoLearnerSkillLeader> leaders = new ArrayList<>();

        ApiAdapter.getClient().getLearnerSkillIqLeader(new Callback<List<PojoLearnerSkillLeader>>() {
            @Override
            public void success(List<PojoLearnerSkillLeader> pojoLearnerSkillLeaders, Response response) {
                leaders.addAll(pojoLearnerSkillLeaders);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_skill, Toast.LENGTH_LONG).show();
            }
        });
        return leaders;
    }
}