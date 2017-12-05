package com.uiautomatordemo;

import android.os.RemoteException;
import android.support.test.uiautomator.UiAutomatorInstrumentationTestRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.test.InstrumentationTestCase;

import java.io.IOException;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * @author 李涛
 * @description
 * @Date 2017/11/17.
 */


public class UITest extends InstrumentationTestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testHome() throws RemoteException, IOException, InterruptedException {

        UiDevice.getInstance(getInstrumentation()).wakeUp();
        UiDevice.getInstance(getInstrumentation()).pressHome();

        Runtime.getRuntime().exec("am start com.uiautomatordemo/com.uiautomatordemo.MainActivity");
        Thread.sleep(4000);

        try {
            new UiObject(new UiSelector().resourceId("com.uiautomatordemo:id/bt1")).setText("litao123");
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

    }
}
