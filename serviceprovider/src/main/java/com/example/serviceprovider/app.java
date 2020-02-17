package com.example.serviceprovider;

import android.app.Application;
import android.util.Log;

import androidx.databinding.DataBindingUtil;

import databinding.AppDataBindingComponent;

public class app extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
        Log.w("call","calling AppDataBindingComponent");
    }
}
