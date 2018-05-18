package com.studyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.studyproject.adapter.TestAdapter;

import java.util.ArrayList;

public class ListAdapterTestActivity extends AppCompatActivity {

    private ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter_test);
        initView();
    }

    private void initView() {

        for (int i=0;i<10;i++){
            list.add("李涛 "+i);
        }

        ListView listview1 = (ListView) findViewById(R.id.listview1);
        TestAdapter adapter = new TestAdapter(this,list);
        listview1.setAdapter(adapter);


        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListAdapterTestActivity.this,ListAdapterTestActivity2.class);
                startActivity(intent);
            }
        });
    }
}
