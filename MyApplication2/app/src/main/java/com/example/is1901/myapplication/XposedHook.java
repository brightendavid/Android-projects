package com.example.is1901.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XposedHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //判断当前加载的包名
//        XposedBridge.log("Hello World! I'm from " + loadPackageParam.processName + "|" + loadPackageParam.packageName);
        if(loadPackageParam.packageName.equals("com.youku.phone")) {
            //Do something
            XposedBridge.log("package hacked");
            Class cla = loadPackageParam.classLoader.loadClass("com.youku.upsplayer.module.VideoInfo");
            XposedHelpers.findAndHookMethod(cla, "getAd",new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("get it!!!!!!");
                    param.setResult(null);
                    XposedBridge.log("success!!!!!!");
                }
            });

        }
    }
}