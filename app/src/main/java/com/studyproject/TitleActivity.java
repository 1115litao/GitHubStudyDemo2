package com.studyproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studyproject.weight.LT_NavitaitionTitleView;

import java.util.ArrayList;
import java.util.List;

public class TitleActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private ArrayList<ImageView> imagelist;
    private ArrayList<String> titleList;
    private HorizontalScrollView hs_view;
    private LinearLayout ll_view;

    private ArrayList<TextView> textList = new ArrayList<>();
    private  int lastIndex = 0;
    private ArrayList<String> datalist;
    private TitleShowModle db;
    private LT_NavitaitionTitleView slidetitleview;
    private ImageView addImage;
    private TitleViewPager adapter;
    private TextView titleName1;
    private TextView titleName2;

    private int flag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,TagGroupActivity.class);
        startActivity(intent);
        setContentView(R.layout.activity_title);
        db = TitleShowModle.getInstance(this,"sqlitetable");
        initData();
        initTest();

    }

    private void initTest() {


        titleName1 = (TextView) findViewById(R.id.title_name1);
        titleName2 = (TextView) findViewById(R.id.title_name2);
        addImage = (ImageView) findViewById(R.id.add_image);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new TitleViewPager(titleList);
        viewpager.setAdapter(adapter);
        slidetitleview = (LT_NavitaitionTitleView) findViewById(R.id.slidetitleview);
        datalist = titleList;
         slidetitleview.setTitleAdapter(this,datalist,viewpager,R.drawable.defaultbg,Color.RED,R.drawable.select,Color.parseColor("#43A44b"),-1);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   titleList.add(0,"新添加的");
                slidetitleview.updateTitle(titleList,0);*/
              //  adapter.updateList(titleList);
                initDialog();
            }
        });

        titleName1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag != 1){
                    slidetitleview.updateTitle(titleList,1);
                    titleName1.setBackgroundResource(R.drawable.select);
                    titleName1.setTextColor(Color.parseColor("#43A44b"));

                    titleName2.setBackgroundResource(R.drawable.defaultbg);
                    titleName2.setTextColor(Color.RED);
                    flag = 1;
                }

            }
        });
        titleName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag != 2){
                    flag = 2;
                    slidetitleview.updateTitle(titleList,1);
                    titleName2.setBackgroundResource(R.drawable.select);
                    titleName2.setTextColor(Color.parseColor("#43A44b"));

                    titleName1.setBackgroundResource(R.drawable.defaultbg);
                    titleName1.setTextColor(Color.RED);
                }

            }
        });
        slidetitleview.setTabOnlickListener(new LT_NavitaitionTitleView.Ontabonclick() {
            @Override
            public void tabView(View view) {
                if(flag != 0){
                    flag = 0;
                    titleName1.setBackgroundResource(R.drawable.defaultbg);
                    titleName1.setTextColor(Color.RED);
                    titleName2.setBackgroundResource(R.drawable.defaultbg);
                    titleName2.setTextColor(Color.RED);
                }
            }
        });

       // addTest();
    }

    public void initDialog(){
        Dialog dialog = new Dialog(this,R.style.MyDialog);
        dialog.setContentView(R.layout.lt_pub_title_dialog);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);  //此处可以设置dialog显示的位置
        dialog.show();
    }








    public  void addTest(){
        datalist = db.selectData();
        for (int i=0;i<datalist.size();i++){

            final int index = i;
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = dip2px(this,10);
            params.rightMargin = dip2px(this,10);
            textView.setTextSize(17);
            textView.setText(datalist.get(i));
            ll_view.addView(textView,params);
            textList.add(textView);

            if(i==0){
                textView.setTextColor(Color.parseColor("#43A44b"));
            }else{
                textView.setTextColor(Color.RED);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(index!=lastIndex){
                        viewpager.setCurrentItem(index);
                        setCurrent(index);
                    }
                }
            });
        }


        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                textList.get(position).setTextColor(Color.parseColor("#43A44b"));
                textList.get(lastIndex).setTextColor(Color.RED);
                if(position!=lastIndex){
                    lastIndex = position;
                }
                setCurrent(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    public void setCurrent(int index){
        int[] screen = new int[2];
        textList.get(index).getLocationOnScreen(screen);
        int  width = textList.get(index).getWidth();
        //控制左边
        if(screen[0]<0){
            hs_view.smoothScrollBy( -(Math.abs(screen[0])+20),0);
        }
        //控制右边
        int sereenWidth =  getScreenWidth(TitleActivity.this);
        if((screen[0]+width)>sereenWidth){
            hs_view.smoothScrollBy(screen[0]+width-sereenWidth+20,0);
        }
    }



    /**
     * px转化成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取屏幕宽度
     *
     * @param
     * @return
     */
    private static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    private void initData() {

            titleList = new ArrayList<>();
        for(int i=0;i<300;i++){
            titleList.add("李涛"+i);
        }


          //  db.insertData(titleList);

            imagelist = new ArrayList<>();

            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(Color.parseColor("#67EA78"));
            imagelist.add(imageView);
    }

    /**
     * dip转化成px
     */
    public   int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    /**
     * sp转化成px
     */
    public   int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    public class TitleViewPager extends PagerAdapter{
        ArrayList<String> imagelist;
        private ImageView imageView;

        public    TitleViewPager( ArrayList<String> titleList){
            this.imagelist = titleList;
        }

        public  void updateList(ArrayList<String> titleList){
            this.imagelist = titleList;
            notifyDataSetChanged();
        }
        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            imageView = new ImageView(TitleActivity.this);
            imageView.setBackgroundColor(Color.parseColor("#67EA78"));
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }


}
