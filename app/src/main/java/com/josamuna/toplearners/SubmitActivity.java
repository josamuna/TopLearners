package com.josamuna.toplearners;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

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

        final EditText textFirstName = findViewById(R.id.text_first_name);
        final EditText textLastName = findViewById(R.id.text_last_name);
        final EditText textEmail = findViewById(R.id.text_email);
        final EditText textProjectGithub = findViewById(R.id.text_project_github);
        Button btnSubmit = findViewById(R.id.btn_submit_project);

        btnSubmit.setOnClickListener(view -> {
//            showDialogMessage("Are you sure ?", mContext);
            executeSubmissionProject();
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

    private void executeSubmissionProject() {
        // Load TopLearners
        ApiAdapter.getClient().getLearnerLeader(new Callback<List<PojoLearnerLeader>>() {
            @Override
            public void success(List<PojoLearnerLeader> pojoLearnerLeaders, Response response) {
//                for(PojoLearnerLeader lst : pojoLearnerLeaders){
//                    System.out.println(String.format(Locale.ENGLISH,"Name = %s\nHours = %d\nCountry = %s\nBadgeUrl = ",
//                            lst.getName(), lst.getHours(), lst.getCountry(), lst.getBadgeUrl()));
//                }
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
}