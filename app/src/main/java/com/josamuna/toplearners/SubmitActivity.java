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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.josamuna.toplearners.pojo.ApiAdapter;
import com.josamuna.toplearners.pojo.PojoProjectSubmit;

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
    private PojoProjectSubmit mPojoProjectSubmit = new PojoProjectSubmit();

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

        mTextFirstName.setText("Josue");
        mTextLastName.setText("Isamuna Nkembo");
        mTextEmail.setText("josamuna2009@gmail.com");
        mTextProjectGithub.setText("https://github.com/josamuna/TopLearners");

        Button btnSubmit = findViewById(R.id.btn_submit_project);

        btnSubmit.setOnClickListener(view1 -> {
//            showSubmissionStatus(R.layout.success_layout);
//            showSubmissionStatus(R.layout.fail_layout);
//            showCustomDialogMessage(mContext);
            if(validateField()) {
                showCustomDialogQuestionSubmit();
            }
        });
    }

    private void executeSubmissionProject(String firstName, String lastName, String emailAddress, String gitHubProject) {
        final ProgressDialog progressDialog = Utils.getProgressDialog(mContext, "Please wait");

        (ApiAdapter.getPostClient().sendInformations(firstName, lastName,
                emailAddress, gitHubProject)).enqueue(new Callback<PojoProjectSubmit>() {
            @Override
            public void onResponse(Call<PojoProjectSubmit> call, Response<PojoProjectSubmit> response) {
                if(response.isSuccessful()) {
                    mPojoProjectSubmit = response.body();
                    Toast.makeText(mContext, String.format("FirstName = %s\nLastName = %s\nEmail Address = %s\nGitHub Project link = %s",
                            mPojoProjectSubmit.getFirstName(), mPojoProjectSubmit.getLastName(), mPojoProjectSubmit.getEmailAddress(),
                            mPojoProjectSubmit.getLinkGitHub()), Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    showCustomDialogMessageSubmit(R.layout.success_layout);
                }
            }

            @Override
            public void onFailure(Call<PojoProjectSubmit> call, Throwable t) {
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

//    private void showDialogMessage(String message, final Context context) {
//        AlertDialog.Builder builder;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
//        } else {
//            builder = new AlertDialog.Builder(context);
//        }
//        builder.setTitle(message)
//                .setMessage(null)
//                .setPositiveButton(android.R.string.yes, (dialog, which) -> Toast.makeText(context, "Yes accepted", Toast.LENGTH_LONG).show())
////                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        // do nothing
////                    }
////                })
//                .setIcon(android.R.drawable.ic_dialog_alert)
//                .show();
//    }

//    private void showCustomDialogMessage(final Context context) {
//        AlertDialog.Builder builder;
//        builder = new AlertDialog.Builder(context);
//
//        LayoutInflater factory = LayoutInflater.from(context);
//        final View view = factory.inflate(R.layout.question_layout, null);
//        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
//        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
//            // Submit project action
//            Toast.makeText(mContext, "Yes submit project", Toast.LENGTH_LONG).show();
//        });
//
//        builder.setIcon(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background)).setView(view);
//        builder.show();
//    }

    private void showCustomDialogQuestionSubmit() {
        mViewGroup = findViewById(android.R.id.content);
        View view = LayoutInflater.from(this).inflate(R.layout.question_layout, mViewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();

        Button buttonDialogQuestionSubmit = view.findViewById(R.id.button_dialog_question_submit);
        buttonDialogQuestionSubmit.setOnClickListener(view2 -> {
            alertDialog.cancel();
            executeSubmissionProject(mTextFirstName.getText().toString(), mTextLastName.getText().toString(),
                    mTextEmail.getText().toString(), mTextProjectGithub.getText().toString());
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

//    private void showSubmissionStatus(int layout) {
//        View view = LayoutInflater.from(this).inflate(layout, mViewGroup, false);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

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