package com.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    private  String backData;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intent = getIntent();
        Button bt2 = findViewById(R.id.bt2);
        Button back = findViewById(R.id.main2back);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main2Activity.this,Main3Activity.class);
                startActivityForResult(intent2,1);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            backData   =   data.getStringExtra("main3");
            Log.i("aaa","Main2222222onActivityResult");
            Log.i("aaa","Main2222222 onActivityResult=====backData"+backData);

        }
    }

    @Override
    public void onBackPressed() {
        Log.i("aaa","Main2222222onBackPressed");
        Log.i("aaa","Main2222222 backData"+backData);
        intent.putExtra("main2",backData);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }
}
