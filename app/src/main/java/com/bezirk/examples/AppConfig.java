package com.bezirk.examples;

import android.content.Context;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.android.BezirkMiddleware;
import com.bezirk.middleware.core.proxy.Config;

/**
 * Created by vicky on 10/22/2016.
 */

public class AppConfig {
    private static Bezirk bezirk;

    AppConfig(){

    }

    public static Bezirk instantiate(Context context) {
        //Initialize the middleware and register your Zirk to get Bezirk instance.
        Config config = new Config.ConfigBuilder().setGroupName("SVYYOCO").create();

        BezirkMiddleware.initialize(context, config);

        bezirk = BezirkMiddleware.registerZirk("vignesh-phone");
        return bezirk;

    }
}
