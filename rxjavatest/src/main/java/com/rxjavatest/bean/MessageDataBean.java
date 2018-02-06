package com.rxjavatest.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @description
 * @Date 2018/1/5.
 */


public class MessageDataBean implements Serializable {

    private List<HomeResults> results = new ArrayList<>();

    public List<HomeResults> getResults() {
        return results;
    }

    public void setResults(List<HomeResults> results) {
        this.results = results;
    }


    @Override
    public String toString() {
        return "MessageDataBean{" +
                "results=" + results +
                '}';
    }
}
