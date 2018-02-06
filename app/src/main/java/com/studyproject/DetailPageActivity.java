package com.studyproject;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.studyproject.fragment.DetailFragment;

public class DetailPageActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);
        DetailFragment fragment = new DetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_layout,fragment).commit();
    }
}
