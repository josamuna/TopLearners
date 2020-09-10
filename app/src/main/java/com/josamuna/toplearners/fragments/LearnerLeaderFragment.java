package com.josamuna.toplearners.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.josamuna.toplearners.R;
import com.josamuna.toplearners.pojo.ApiAdapter;
import com.josamuna.toplearners.pojo.PojoLearnerLeader;
import com.josamuna.toplearners.recycleradapters.LearnerLeaderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearnerLeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnerLeaderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LearnerLeaderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LearnerLeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearnerLeaderFragment newInstance(String param1, String param2) {
        LearnerLeaderFragment fragment = new LearnerLeaderFragment();
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
        return inflater.inflate(R.layout.fragment_learner_leader, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        // Get RecyclerView Reference
//        final RecyclerView rvFragmentLearnerLeader = view.findViewById(R.id.rv_fragment_learner_leader);
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        rvFragmentLearnerLeader.setLayoutManager(layoutManager);
//
//        // Load Data into RecyclerView
//        List<PojoLearnerLeader> leaders;
////        List<PojoLearnerLeader> leaders = new ArrayList<>();
////        PojoLearnerLeader l1 = new PojoLearnerLeader();
////        l1.setName("Isamuns Nkembo");
////        l1.setHours(235);
////        l1.setCountry("DRC");
////        l1.setBadgeUrl("Badge Test1");
////        PojoLearnerLeader l2 = new PojoLearnerLeader();
////        l2.setName("Feza Hozana");
////        l2.setHours(320);
////        l2.setCountry("Angola");
////        l2.setBadgeUrl("Badge Test2");
////        leaders.add(l1);
////        leaders.add(l2);
//        leaders = loadLeanerLeaders();
//        final LearnerLeaderAdapter adapter = new LearnerLeaderAdapter(getContext(), leaders);
//        rvFragmentLearnerLeader.setAdapter(adapter);
    }

    private List<PojoLearnerLeader> loadLeanerLeaders() {
        List<PojoLearnerLeader> leaders = new ArrayList<>();

        ApiAdapter.getClient().getLearnerLeader(new Callback<List<PojoLearnerLeader>>() {
            @Override
            public void success(List<PojoLearnerLeader> pojoLearnerLeaders, Response response) {
                leaders.addAll(pojoLearnerLeaders);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(), R.string.toast_fail_load_learner_leaders, Toast.LENGTH_LONG).show();
            }
        });

        return leaders;
    }
}