package com.easy.tbs;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.lang.ref.SoftReference;

/**
 * @author fuyujie
 * @package: com.easy.tbs
 * @fileNmae App
 * @date 2018/9/11 14:23
 * @describe
 * @org easylinking
 * @email f279259625@gmail.com
 */

public class App extends Application {

    public static SoftReference<Context> bsnContext;

    @Override
    public void onCreate() {
        super.onCreate();

        bsnContext = new SoftReference<>(getApplicationContext());

//        QbSdk.setTbsListener(new TbsListener() {
//            @Override
//            public void onDownloadFinish(int i) {
//                //tbs内核下载完成回调
//                Log.e(">>>>>>>>>>>", "onDownloadFinish" + i);
//            }
//
//            @Override
//            public void onInstallFinish(int i) {
//                //内核安装完成回调，
//                Log.e(">>>>>>>>>>>", "onInstallFinish" + i);
//            }
//
//            @Override
//            public void onDownloadProgress(int i) {
//                //下载进度监听
//                Log.e(">>>>>>>>>>>", "onDownloadProgress" + i);
//            }
//        });
//
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.e(">>>>>>>>>>>", " onViewInitFinished is " + b);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                Log.e(">>>>>>>>>>>", "onCoreInitFinished");
//            }
//        };
//
//        QbSdk.initX5Environment(getApplicationContext(), cb);

    }
}
