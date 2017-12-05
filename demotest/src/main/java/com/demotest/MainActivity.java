package com.demotest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demotest.crash.CrashHandler;
import com.demotest.crash.XL_CrashHandler;

public class MainActivity extends AppCompatActivity {

    private Button bt1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {



        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

    /*    XL_CrashHandler xl_crashHandler = XL_CrashHandler.getInstance();
        xl_crashHandler.init(getApplicationContext());*/

        LT_CustomDivisionEditext lt_idcard_input = findViewById(R.id.lt_idcard_input);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            String name = data.getStringExtra("main2");
            Log.i("aaa","Main11111111onActivityResult");
            Log.i("aaa","Main11111111onActivityResult=====DAta"+name);
//            bt1.setText(name);
        }
    }
}
