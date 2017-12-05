package com.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private Intent intent;
    private Button bt4;
    private Button bt3;
    private TextView show_error_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        Intent intent = getIntent();
        String errorData = intent.getStringExtra("errordata");
        show_error_view = findViewById(R.id.show_error_view);
        show_error_view.setText(errorData);

        Log.i("aaa","ssssssssssssssssssssssssssssss");

     /*   intent = getIntent();
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bt3.setVisibility(View.GONE);

            }
        });*/
    }
}
