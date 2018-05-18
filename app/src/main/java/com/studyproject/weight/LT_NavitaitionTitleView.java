package com.studyproject.weight;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * @author 李涛
 * @description
 * @Date 2018/3/14.
 */


public class LT_NavitaitionTitleView extends LinearLayout {

    private HorizontalScrollView hs_view;
    private LinearLayout ll_veiw;
    private Context context;
    private ArrayList<String> titleList;
    private  ViewPager viewPager;
    private int defaultlTextColor = Color.RED;
    private int selectTextColor = Color.parseColor("#43A44b");
    private int defaultlBg;
    private int selectBg;
    private int defaultIndex = 0;
    private int lastSelectIndex = defaultIndex;//上次选中的item
    private int marginLeft = 10;
    private int marginRight = 10;
    private ArrayList<TextView> textViews = new ArrayList<>();
    private Ontabonclick ontabonlick;  //tab监听
    private OnViewPagerOclick onPagerOclick; //viewpager滑动监听

    public LT_NavitaitionTitleView(Context context) {
        super(context,null);
        setOrientation(HORIZONTAL);
    }

    public LT_NavitaitionTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        initView(context);
    }

    public LT_NavitaitionTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
    }


   private void initView(Context mContext){

        //初始化HorHorizontalScrollView
        hs_view = new HorizontalScrollView(mContext);
        hs_view.setHorizontalScrollBarEnabled(false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hs_view.setLayoutParams(layoutParams);
        //初始化LinearLayout 显示title
        ll_veiw = new LinearLayout(mContext);
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ll_veiw.setOrientation(LinearLayout.HORIZONTAL);
        ll_veiw.setGravity(Gravity.CENTER_VERTICAL);
        hs_view.addView(ll_veiw,layoutParams1);
        addView(hs_view);
    }

    /***
     *
     * @param context
     * @param titleList title数据展示
     * @param viewPager  相关联的ViewPager
     * @param defaultlTextColor 默认字体的颜色
     * @param selectTextColor  选中字体的颜色
     * @param defaultlBg 默认背景的颜色
     * @param selectBg  选中背景的颜色
     * @param defaultIndex  初始化位置
     */
    public void setTitleAdapter(Context context, ArrayList<String> titleList, ViewPager viewPager,int defaultlBg,int defaultlTextColor,int selectBg, int selectTextColor, int defaultIndex){
        this.context = context;
        this.titleList = titleList;
        this.viewPager = viewPager;
        this.defaultIndex =defaultIndex;
        this.defaultlTextColor = defaultlTextColor;
        this.selectTextColor = selectTextColor;
        this.defaultlBg  = defaultlBg;
        this.selectBg = selectBg;

        setTitles(titleList,defaultIndex);
      //  setViewPager(viewPager);
    }

    /**
     * 设置ViewPager监听
     * @param viewPager
     */
    private void setViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position!=lastSelectIndex){
                    textViews.get(position).setTextColor(selectTextColor);
                    textViews.get(position).setBackgroundResource(selectBg);

                    textViews.get(lastSelectIndex).setTextColor(defaultlTextColor);
                    textViews.get(lastSelectIndex).setBackgroundResource(defaultlBg);
                    lastSelectIndex = position;
                    //检测tab的在屏幕的范围
                    setCurrent(position);

                    if(onPagerOclick!=null){
                        onPagerOclick.pagePosition(position);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }





    /**
     * 初始化Title内容
     * @param titleList
     * @param defaultIndexs
     */
    private   void  setTitles(ArrayList<String> titleList, final int  defaultIndexs){
                 this.defaultIndex = defaultIndexs;
                   textViews.clear();
            for (int i=0;i<titleList.size();i++){
                TextView tab = new TextView(context);
                final   int   index = i;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = dip2px(context,marginLeft);
                params.rightMargin = dip2px(context,marginRight);
                tab.setTextSize(17);
                tab.setLayoutParams(params);
                tab.setGravity(Gravity.CENTER);
                tab.setText(titleList.get(i));
                //设置默认选中
                if(defaultIndex == -1){
                    Log.i("hahah","这里初始化");
                    tab.setBackgroundResource(defaultlBg);
                    tab.setTextColor(defaultlTextColor);
                }else{
                    //设置默认选中
                    if(i == defaultIndex){
                        tab.setBackgroundResource(selectBg);
                        tab.setTextColor(selectTextColor);
                    }else {
                        tab.setBackgroundResource(defaultlBg);
                        tab.setTextColor(defaultlTextColor);
                    }
                }
                //设置选中的ViewPager页面
                tab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    if(index != defaultIndex){
                        textViews.get(index).setBackgroundResource(selectBg);
                        textViews.get(index).setTextColor(selectTextColor);
                        if(defaultIndex != -1){
                            textViews.get(defaultIndex).setBackgroundResource(defaultlBg);
                            textViews.get(defaultIndex).setTextColor(defaultlTextColor);
                        }
                        defaultIndex = index;
                    }

                        setCurrent(index);
                        //tab监听
                        if(ontabonlick!=null){
                            ontabonlick.tabView(v);
                        }
                    }
                });
                ll_veiw.addView(tab);
                textViews.add(tab);
            }
    }

    /**
     * 检测当前tab在屏幕的范围
     * @param index
     */
    public void setCurrent(int index){

        Log.i("index","=========="+index);

        int[] TabScreen = new int[2];
        int[] ll_ViewScreen = new int[2];
        textViews.get(index).getLocationOnScreen(TabScreen);
        getLocationOnScreen(ll_ViewScreen);
        Log.i("ll_ViewScreen",""+ll_ViewScreen[0]);
        int  width = textViews.get(index).getWidth();
        //控制左边
        if(TabScreen[0]<ll_ViewScreen[0]){
            hs_view.smoothScrollBy( (TabScreen[0]-ll_ViewScreen[0])-20,0);
        }
        //控制右边
        int parentViewWidth =   getWidth();
        Log.i("ccc","TabScreen[0]=="+TabScreen[0]);
        Log.i("ccc","width=="+ width);
        Log.i("ccc","parentViewWidth=="+(parentViewWidth+ll_ViewScreen[0]));
        if((TabScreen[0]+width)>(parentViewWidth+ll_ViewScreen[0])){
            hs_view.smoothScrollBy((TabScreen[0]+width)-(parentViewWidth+ll_ViewScreen[0])+20,0);
        }
    }

    /**
     * tab监听
     * @param ontabonlick
     */
    public   void setTabOnlickListener(Ontabonclick ontabonlick){
        this.ontabonlick = ontabonlick;
    }
   public interface Ontabonclick{
        void tabView(View  view);
    }

    /**
     * ViewPager滑动的监听
     * @param onPagerOclick
     */
    public  void setPagerOnlickListener( OnViewPagerOclick onPagerOclick){
        this.onPagerOclick = onPagerOclick;
    }

   public interface OnViewPagerOclick{
        void pagePosition(int position);
    }

    /**
     * 更新tab数据内容
     * @param updateList
     * @param flag
     */
    public  void updateTitle(ArrayList<String> updateList,int flag){

        if(0==flag){
            this.titleList = updateList;
            ll_veiw.removeAllViews();
            setTitles(titleList,0);
            //    setViewPager(viewPager);
            hs_view.smoothScrollTo(0,0);//初始化标题栏位置
            // viewPager.setCurrentItem(defaultIndex);//初始化ViewPager位置
        }else  if(1==flag){
            this.titleList = updateList;
            ll_veiw.removeAllViews();
            setTitles(titleList,-1);
        }

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


    /**
     * dip转化成px
     */
    public   int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * px转化成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
