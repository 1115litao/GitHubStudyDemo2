package com.studyproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.studyproject.adapter.TestAdapter;

import java.util.ArrayList;

public class ListAdapterTestActivity2 extends AppCompatActivity {
    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter_test2);
        initView();
    }

    private void initView() {
        for (int i=0;i<10;i++){
            list.add("李涛 "+i);
        }

        ListView listview2 = (ListView) findViewById(R.id.listview2);
        TestAdapter adapter = new TestAdapter(this,list);
        listview2.setAdapter(adapter);
        listview2.setVisibility(View.VISIBLE);
    }
}
