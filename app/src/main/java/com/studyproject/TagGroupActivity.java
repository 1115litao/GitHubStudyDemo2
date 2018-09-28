package com.studyproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class TagGroupActivity extends AppCompatActivity {

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_group);
        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add("李涛我阿什顿啊是"+i);
        }
    }

    private void initView() {

        TagGroup tagGroup = (TagGroup) findViewById(R.id.tagGroup);
        tagGroup.setTags(list);
        tagGroup.setNormalOnClickListener(new TagGroup.normalOnClick() {
            @Override
            public void onlickContent(TagGroup.TagView tagView) {
                Toast.makeText(TagGroupActivity.this, ""+tagView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
