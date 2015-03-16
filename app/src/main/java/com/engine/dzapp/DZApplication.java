package com.engine.dzapp;

import android.app.Application;

/**
 * Created by cat1412266 on 15/3/15.
 */
public class DZApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DaoManager.getInstance().init(this);
    }
}
