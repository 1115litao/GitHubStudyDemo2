package com.demotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demotest.sqlite.SqliteDb;

import java.util.ArrayList;

public class SqliteDemo extends AppCompatActivity implements View.OnClickListener {

    private Button btVersion;
    private Button btInsert;
    private Button btShow;
    private SqliteDb db;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_demo);
        //初始化数据库
        db = SqliteDb.getInstance(this);
        initDataAndView();
    }

    private void initDataAndView() {

        list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add("李濤添加的數據"+i);
        }

        btVersion = findViewById(R.id.bt_version);
        btInsert = findViewById(R.id.bt_start_insert);
        btShow = findViewById(R.id.bt_start_show);
        btVersion.setText("当前版本"+SqliteDb.VERSION);
        btInsert.setOnClickListener(this);
        btShow.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_start_insert:
                db.insertSQlite(list);
                break;

            case R.id.bt_start_show:
              ArrayList<String> showList =  db.querySQlite();
              if(showList!=null&&showList.size()>0){
                  btShow.setText(showList.toString());
              }else{
                  btShow.setText("沒有數據");
              }
                break;
        }
    }
}
