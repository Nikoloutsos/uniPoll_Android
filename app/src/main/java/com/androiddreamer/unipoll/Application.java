package com.androiddreamer.unipoll;

import android.content.Context;

public class Application extends android.app.Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
    }
}
