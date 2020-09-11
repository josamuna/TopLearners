package com.josamuna.toplearners;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.josamuna.toplearners.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        Button buttonSubmitProjectFragment = findViewById(R.id.button_submit_project_fragment);
        buttonSubmitProjectFragment.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
            startActivity(intent);
        });

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            PlaceholderFragment placeholderFragment = new PlaceholderFragment();
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
                        mViewPager.setCurrentItem(tab.getPosition());
//                        placeholderFragment.loadLeanerLeaders();
//                        Toast.makeText(getApplicationContext(), "TAB 1", Toast.LENGTH_LONG).show();
//                        break;
//                    case 1:
//                        placeholderFragment.loadLearnerSkill();
//                        Toast.makeText(getApplicationContext(), "TAB 2", Toast.LENGTH_LONG).show();
//                }
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