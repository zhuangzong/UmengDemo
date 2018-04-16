package com.zz.umengdemo;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;


/**
 * Created by kang on 2018/4/16.
 */

public class MyApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        UMConfigure.init(this, "5ad410e78f4a9d324b00002c", "Umeng", UMConfigure.DEVICE_TYPE_PHONE,
                "a10b4fa09f761e21b21cf2b15f318b71");
        initUpush();
    }
    private void initUpush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.i("success", deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i("failure", "deviceToken onFailure " + s + " " + s1);
            }
        });

        mPushAgent.setPushIntentServiceClass(MyService.class);

    }
    public static Context getContext() {
        return context;
    }
}
