package com.josamuna.toplearners;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.josamuna.toplearners.pojo.ApiAdapter;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitActivity extends AppCompatActivity {

    private Context mContext;
    private ViewGroup mViewGroup;
    private EditText mTextFirstName;
    private EditText mTextLastName;
    private EditText mTextEmail;
    private EditText mTextProjectGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        mContext = this;
        // Get toolbar reference
        Toolbar toolbar = findViewById(R.id.toolbar_submit_project);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
//        Objects.requireNonNull(getSupportActionBar())
//                .setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_round_check_circle_24));

        mTextFirstName = findViewById(R.id.text_first_name);
        mTextLastName = findViewById(R.id.text_last_name);
        mTextEmail = findViewById(R.id.text_email);
        mTextProjectGithub = findViewById(R.id.text_project_github);

        Button btnSubmit = findViewById(R.id.btn_submit_project);

        btnSubmit.setOnClickListener(view1 -> {
            if(validateField()) {
                showCustomDialogQuestionSubmit();
            }
        });
    }

    private void executeSubmissionProject(String firstName, String lastName, String emailAddress, String gitHubProject) {
        final ProgressDialog progressDialog = Utils.getProgressDialog(mContext, "Please wait");

        (ApiAdapter.getPostClient().sendInformations(firstName, lastName,
                emailAddress, gitHubProject)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()) {
                    progressDialog.dismiss();
                    showCustomDialogMessageSubmit(R.layout.success_layout);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                progressDialog.dismiss();
                showCustomDialogMessageSubmit(R.layout.fail_layout);
            }
        });
    }

    private boolean validateField() {
        String firstName = mTextFirstName.getText().toString().trim();
        String lastName = mTextLastName.getText().toString().trim();
        String emailAddress = mTextEmail.getText().toString().trim();
        String gitHubProject = mTextProjectGithub.getText().toString();

        if(firstName.isEmpty()) {
            mTextFirstName.setError("First Name must no be empty.");
            mTextFirstName.requestFocus();
            return false;
        } else if (lastName.isEmpty()) {
            mTextLastName.setError("Last Name must no be empty.");
            mTextLastName.requestFocus();
            return false;
        } else if(gitHubProject.isEmpty()) {
            mTextProjectGithub.setError("Project name must no be empty.");
            mTextProjectGithub.requestFocus();
            return false;
        }

        if(isValidateEmail(emailAddress)) {
            mTextEmail.setError("Email Address is not valid.");
            mTextEmail.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidateEmail(String emailAddress) {
        return !Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    private void showCustomDialogQuestionSubmit() {
        mViewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.question_layout, mViewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
            alertDialog.cancel();

            String firstName = mTextFirstName.getText().toString();
            String lastName = mTextLastName.getText().toString();
            String emailAddress = mTextEmail.getText().toString();
            String gitHubProjectLink = mTextProjectGithub.getText().toString();

            executeSubmissionProject(firstName, lastName, emailAddress, gitHubProjectLink);
        });

        ImageButton buttonImageClose = view.findViewById(R.id.image_button_close);
        buttonImageClose.setOnClickListener(view1 -> alertDialog.cancel());

        alertDialog.show();
    }

    private void showCustomDialogMessageSubmit(int layout) {
        mViewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(layout, mViewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}