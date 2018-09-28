package com.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.IntDef;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends BaseActivity {

    @NonNull
    private String str = "";

    public static final int TYPE1 = 1;
    public static  final int TYPE2= 2;

    public static final String TYPE3 = "3";
    public static  final String TYPE4= "4";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFitsSystemWindows(true);
        initView();
    }


    private void initView() {
        annotationTest(TYPE1);
        textView = findViewById(R.id.show_text);
        textView.setText("");



        ImageView banner_view = findViewById(R.id.banner_view);
        banner_view.setImageDrawable(getResources().getDrawable(R.mipmap.webp_image));
        banner_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
    }

    private void annotationTest(@intdef int i) {
       String st =  getStringResult("asd");

    }

    @IntDef({TYPE1,TYPE2})
    @Retention(RetentionPolicy.SOURCE)
    public @interface intdef {

    }
    @StringDef({TYPE3,TYPE4})
    @Retention(RetentionPolicy.SOURCE)
    public @interface strdef {

    }

    @CheckResult
    public String  getStringResult(@NonNull String parameter){
        return parameter.replace(" ","_");
    }

}
