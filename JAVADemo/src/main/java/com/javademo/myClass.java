package com.javademo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class myClass {

    private static List<String> list1;
    private static List<String> list2;
    private static List<String> list3;
    private static List<List<String>> allList;

    public static void main(String[] args) {

        initData();
        //确定胆
        List<List<String>> lists = new ArrayList();
        lists.add(list1);
        lists.add(list3);
        judgeDan(lists);
    }



    private static void judgeDan(List<List<String>> lists){

        for (List<String> testlist :lists){
            judgeDan2(testlist);
        }
    }

    private static void judgeDan2(List<String> list){
            for (int i=0;i<list.size();i++) {
                judgeDan3(list.get(i),list2);
            }
    }

    private static void judgeDan3(String i,List<String>list){
            for (int k=0;k<list.size();k++){
                System.out.println(i+"-----"+list.get(k));
            }
    }

    private static void initData() {
        list1 = new ArrayList<>();
        for (int i=0;i<=3;i++){
            list1.add("一"+i);
        }
        list2 = new ArrayList<>();
        for (int i=4;i<=6;i++){
            list2.add("二"+i);
        }
        list3 = new ArrayList<>();
        for (int i=7;i<=9;i++){
            list3.add("三"+i);
        }

        allList = new ArrayList<>();
        allList.add(list1);
        allList.add(list2);
        allList.add(list3);
    }

}
