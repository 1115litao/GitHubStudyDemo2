package com.rxjavatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.rxjavatest.home.HomeActivity;
import com.rxjavatest.netutils.RetrofitUtils;
import com.rxjavatest.rxjava.RXObservere;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



/**
 * http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world
 *
 * http://gank.io/api/search/query/listview/category/Android/count/10/page/1
 *
 */
public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }


}
