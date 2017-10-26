package com.rxjavatest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rxjavatest.netutils.RetrofitUtils;
import com.rxjavatest.rxjava.RXObservere;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
 */
public class MainActivity extends Activity {

    private TextView showdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
    }

    private void initData() {

        showdata = (TextView) findViewById(R.id.showdata);

        Map<String,String> requestParams = new HashMap();
        requestParams.put("f","auto");
        requestParams.put("t","auto");
        requestParams.put("w","hello%20world");
        RetrofitUtils.RequestBackString().getUrlData(requestParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RXObservere<String>(MainActivity.this,true) {
                    @Override
                    public void onNext(String o) {
                        super.onNext(o);
                        Log.i("Http",""+o);
                        showdata.setText(o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }
}
