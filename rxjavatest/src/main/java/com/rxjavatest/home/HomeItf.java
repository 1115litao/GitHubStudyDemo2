package com.rxjavatest.home;
import com.rxjavatest.basaitf.BasePresenter;
import com.rxjavatest.basaitf.BaseView;
import com.rxjavatest.bean.MessageDataBean;

/**
 * @author 李涛
 * @description
 * @Date 2018/1/10.
 */


public interface HomeItf  {

    interface  homeView extends BaseView<homePersenter> {
        void  hettpNetData(MessageDataBean data);
    }


    interface homePersenter extends BasePresenter{
        void  getNetData();
    }

}
