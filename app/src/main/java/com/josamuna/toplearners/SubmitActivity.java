package com.josamuna.toplearners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.josamuna.toplearners.pojo.ApiAdapter;
import com.josamuna.toplearners.pojo.PojoLearnerLeader;
import com.josamuna.toplearners.pojo.PojoLearnerSkillLeader;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SubmitActivity extends AppCompatActivity {

    private Context mContext;
    private PojoLearnerLeader mPojoLearnerLeader;
    private PojoLearnerSkillLeader mPojoLearnerSkillLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mContext = this;
//        // To delete begin
//
//        AlertDialog.Builder alertadd = new AlertDialog.Builder(mContext);
//        LayoutInflater factory = LayoutInflater.from(mContext);
//        final View view = factory.inflate(R.layout.question_layout, null);
//        alertadd.setView(view);
////        alertadd.setNeutralButton("Here!", new DialogInterface.OnClickListener() {
////            public void onClick(DialogInterface dlg, int sumthin) {
////            }
////        });
//        alertadd.show();
//
//        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
//        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
//            Toast.makeText(mContext, "Yes submit project", Toast.LENGTH_SHORT).show();
//        });
//
//
//
//        /// To delete end
        // Get toolbar reference
        Toolbar toolbar = findViewById(R.id.toolbar_submit_project);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
//        Objects.requireNonNull(getSupportActionBar())
//                .setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_check_circle_24));

        final EditText textFirstName = findViewById(R.id.text_first_name);
        final EditText textLastName = findViewById(R.id.text_last_name);
        final EditText textEmail = findViewById(R.id.text_email);
        final EditText textProjectGithub = findViewById(R.id.text_project_github);
        Button btnSubmit = findViewById(R.id.btn_submit_project);

        btnSubmit.setOnClickListener(view1 -> {
            showCustomDialogMessage();
//            showCustomDialogMessage(mContext);
//            executeSubmissionProject();
        });
    }

    private void showDialogMessage(String message, final Context context) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(message)
                .setMessage(null)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> Toast.makeText(context, "Yes accepted", Toast.LENGTH_SHORT).show())
//                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        // do nothing
//                    }
//                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void showCustomDialogMessage(final Context context) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);

        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.question_layout, null);
        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
            // Submit project action
            Toast.makeText(mContext, "Yes submit project", Toast.LENGTH_SHORT).show();
        });

        builder.setIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background)).setView(view);
        builder.show();
    }

    private void showCustomDialogMessage() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.question_layout, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
            // Submit project action
            Toast.makeText(mContext, "Yes submit project", Toast.LENGTH_SHORT).show();
        });

        ImageButton buttonImageClose = view.findViewById(R.id.image_button_close);
        buttonImageClose.setOnClickListener(view1 -> {
            alertDialog.cancel();
        });

        alertDialog.show();
    }

    private void executeSubmissionProject() {
        // Load TopLearners
        ApiAdapter.getClient().getLearnerLeader(new Callback<List<PojoLearnerLeader>>() {
            @Override
            public void success(List<PojoLearnerLeader> pojoLearnerLeaders, Response response) {
                for(PojoLearnerLeader lst : pojoLearnerLeaders){
                    System.out.println(String.format(Locale.ENGLISH,"Name = %s\nHours = %d\nCountry = %s\nBadgeUrl = ",
                            lst.getName(), lst.getHours(), lst.getCountry(), lst.getBadgeUrl()));
                }
                Log.d("DATA_LEARNER", "Learners Loaded Successfully");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_LONG).show();
            }
        });
        // Load TopSkillIQ
        ApiAdapter.getClient().getLearnerSkillIqLeader(new Callback<List<PojoLearnerSkillLeader>>() {
            @Override
            public void success(List<PojoLearnerSkillLeader> pojoLearnerSkillLeaders, Response response) {
                for(PojoLearnerSkillLeader lst : pojoLearnerSkillLeaders) {
                    System.out.println(String.format(Locale.ENGLISH,"Name = %s\nScore = %d\nCountry = %s\nBadgeUrl = ",
                            lst.getName(), lst.getScore(), lst.getCountry(), lst.getBadgeUrl()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(mContext, "Failed to load data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home :
                Intent intent = new Intent(SubmitActivity.this, SplashActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}