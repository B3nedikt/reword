package com.b3nedikt.restring.example;

import android.app.Application;

import dev.b3nedikt.restring.Restring;
import dev.b3nedikt.reword.RewordInterceptor;
import dev.b3nedikt.viewpump.ViewPump;

public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Restring.init(this);

        ViewPump.init(ViewPump.builder()
                .addInterceptor(RewordInterceptor.INSTANCE)
                .build()
        );
    }
}