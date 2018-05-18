package com.demotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DataNeatenActivity extends AppCompatActivity {

    private ArrayList<String> list1;
    private ArrayList<String> list2;
    private ArrayList<String> list3;
    private TextView show_text;
    private ArrayList<ArrayList<String>> allList;
    private ArrayList<ArrayList<String>> selectAllList = new ArrayList<>();
    private ArrayList<ArrayList<String>> noSelectAllList = new ArrayList<>();
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    StringBuilder builder = new StringBuilder();
    int isadd = 0;
    private StringBuilder appends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_neaten);

        show_text = findViewById(R.id.show_text);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);

        initData();
        initListener();

    }

    private void initListener() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllList.add(list1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllList.add(list2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAllList.add(list3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFlag(selectAllList);
                selectFlag2(noSelectAllList);
                bt4.setText(builder.toString());
            }
        });
    }

    /***
     *   筛选出未选中的集合  （不是胆的集合）
     * @param selectList
     */
    private void selectFlag(ArrayList<ArrayList<String>> selectList) {
            for (ArrayList<String> isInList:allList){
                    if (!selectList.contains(isInList)){
                        Log.i("aaa","筛选出来的"+isInList.toString());
                        noSelectAllList.add(isInList);
                    }
            }
    }


    /***
     * 传入不是胆的集合
     * @param noInList
     */
    private void selectFlag2(ArrayList<ArrayList<String>>  noInList){

        if(selectAllList.size()>=2){
            mergeList2(0);

        }else{

            for (ArrayList<String> danList : selectAllList){
                if(danList.size()>=noInList.get(0).size()){
                    Log.i("aaa","danList.size()>=noInList.size()"+danList.toString()+"         "+noInList.get(0).toString());
                    mergeList1(danList,noInList.get(0));
                }else{
                    Log.i("aaa","danList.size()<noInList.size()"+danList.toString()+"         "+noInList.get(0).toString());
                    mergeList1(noInList.get(0),danList);
                }
            }
        }
    }




        private  void mergeList1(ArrayList<String>  danList1,ArrayList<String>  noDanList){

            for (int i=0;i<danList1.size();i++){
                for (int k=0;k<noDanList.size();k++){
                    builder.append("       "+danList1.get(i)+noDanList.get(k));
                    Log.i("aaa","最后一次"+danList1.get(i)+noDanList.get(k));
                    if(k==noDanList.size()){
                        isadd = 1;
                        break;
                    }
                }
            }
        }

            private void mergeList2(int num){
                appends = new StringBuilder();
                if(num<selectAllList.size()){
                    if(num<=selectAllList.size()){
                        for (int k = 0;k<selectAllList.get(num).size();k++){
                            appends.append(selectAllList.get(num).get(k));
                            test1((++num));
                        }
                    }
                }
            }

            private void test1(int num){
                Log.i("eeeeeeeee","selectAllList.size()"+selectAllList.size());
                Log.i("eeeeeeeee","num==="+num);
                //这里先循环所有的胆
                    if(num <(selectAllList.size())){
                        Log.i("eeeeeeeee","呵呵呵");
                        for (int i = 0;i<selectAllList.get(num).size();i++){
                            appends.append(selectAllList.get(num).get(i));
                            test1((++num));
                        }
                    }else{
                        Log.i("eeeeeeeee","else");
                    //   test3(num);
                    }

                Log.i("eeeeeeeee",""+appends.toString());
                appends.replace(0,appends.length(),"");
            }

            private  void test3(int num){
                for (int k = 0;k<noSelectAllList.get(0).size();k++){
                            appends.append(noSelectAllList.get(0).get(k));
                            mergeList2(num);
                        }
                Log.i("eeeeeeeee",""+appends.toString());
                appends.replace(0,appends.length(),"");

            }

    private void initData() {

        list1 = new ArrayList<>();
        for (int i=0;i<2;i++){
            list1.add("一"+i);
        }
        list2 = new ArrayList<>();
        for (int i=3;i<6;i++){
            list2.add("二"+i);
        }
        list3 = new ArrayList<>();
        for (int i=7;i<9;i++){
            list3.add("三"+i);
        }

        bt1.setText(list1.toString());
        bt2.setText(list2.toString());
        bt3.setText(list3.toString());

        allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);
        allList.add(list3);

    }
}
