package com.josamuna.toplearners;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.josamuna.toplearners.ui.main.PlaceholderFragment;
import com.josamuna.toplearners.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Button buttonSubmitProjectFragment = findViewById(R.id.button_submit_project_fragment);
        buttonSubmitProjectFragment.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
            startActivity(intent);
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            PlaceholderFragment placeholderFragment = new PlaceholderFragment();
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
//                        placeholderFragment.loadLeanerLeaders();
//                        Toast.makeText(getApplicationContext(), "TAB 1", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
//                        placeholderFragment.loadLearnerSkill();
//                        Toast.makeText(getApplicationContext(), "TAB 2", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}