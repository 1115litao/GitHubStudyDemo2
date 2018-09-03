package com.kotilndemo.ui;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.kotilndemo.R;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import http.OkHttpUtil;
import http.callback.StringCallback;
import okhttp3.Call;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.tabs_layout)
     TabLayout tabLayout;
    @BindView(R.id.recy_content)
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        initData();
//        Intent intent = new Intent(this,KotlinTestActivity.class);
//        startActivity(intent);
    }

    private void initData() {
        HashMap<String,String>  params = new HashMap<>();
        OkHttpUtil.Companion.post().url("http://gank.io/api/data/Android/10/1").params(params).build().execute(new StringCallback() {
            @Override
            public void onError(@Nullable Call call, @Nullable Exception e, @Nullable Integer id) {

            }

            @Override
            public void onResponse(@Nullable String response, @Nullable Integer id) {
                    Log.i("aaa",""+response);

            }
        });
    }

    private void initView() {
        tabLayout.addTab(tabLayout.newTab().setText("标签一"));
        tabLayout.addTab(tabLayout.newTab().setText("标签二"));
        tabLayout.addTab(tabLayout.newTab().setText("标签三"));
        tabLayout.addTab(tabLayout.newTab().setText("标签四"));
        tabLayout.addTab(tabLayout.newTab().setText("标签五"));

    }
}
