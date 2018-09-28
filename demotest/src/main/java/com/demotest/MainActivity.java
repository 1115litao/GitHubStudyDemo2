package com.demotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demotest.adapter.PopupAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity  {


    private ArrayList<String> list;
    private TextView tv_switch;
    private ListView lv_conten;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent intent  = new Intent(this,SqliteDemo.class);
        startActivity(intent);


        initData();
        initView();
        initListener();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i=0;i<30;i++){
            list.add("李涛测试"+i);
        }
    }


    private void initView() {
        tv_switch = findViewById(R.id.tv_switch);
        lv_conten = findViewById(R.id.lv_content);
        lv_conten.setAdapter(new PopupAdapter(this,list));
    }

    private void initListener() {
        tv_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopup();
            }
        });
    }


    private void initPopup(){
        int srceenW =  this.getWindowManager().getDefaultDisplay().getWidth();
        int height =  lv_conten.getHeight();  //设置高度和ListView高度一致就可以了= =
        View view =  LayoutInflater.from(this).inflate(R.layout.popup_view,null);
        popupWindow = new PopupWindow(view,srceenW,height,true);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.default_ptr_rotate2));
        GridView gv_popup =view.findViewById(R.id.gv_popup_content);
        gv_popup.setAdapter(new PopupAdapter(this,list));
        gv_popup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
            }
        });
        popupWindow.showAsDropDown(tv_switch);
    }
}