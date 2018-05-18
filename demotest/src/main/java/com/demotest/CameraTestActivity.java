package com.demotest;

import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.LinearLayout;

public class CameraTestActivity extends FragmentActivity {

    private GridView gv_photp_show;
    private LinearLayout ll_fragment_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        initView();

    }

    private void initView() {
        gv_photp_show = findViewById(R.id.gv_photp_show);
        ll_fragment_show = findViewById(R.id.ll_fragment_show);
        LocalPhotosFragment fragment = new LocalPhotosFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.ll_fragment_show,fragment).commit();
    }
}
