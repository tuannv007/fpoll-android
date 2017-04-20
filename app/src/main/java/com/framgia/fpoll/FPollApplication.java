package com.framgia.fpoll;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by framgia on 20/04/2017.
 */

public class FPollApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return sContext;
    }
}
