package com.demotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThreeActivity extends AppCompatActivity {

    private Button three_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        three_bt = findViewById(R.id.three_bt);
        three_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CallBackUtils callBackUtils = CallBackUtils.getInstance();
                callBackUtils.sendMessage("11111111111111111");
                callBackUtils.sendMessage("222222222222222");
                callBackUtils.sendMessage("3333333333333333");
                callBackUtils.sendMessage("44444444444444444444");
                finish();
            }
        });
    }


}
