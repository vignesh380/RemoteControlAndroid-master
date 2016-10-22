package com.bezirk.examples.remotecontrolandroid;

import android.app.Application;
import android.util.Log;

import com.bezirk.examples.AppConfig;
import com.bezirk.examples.events.HouseStateEvent;
import com.bezirk.examples.events.SimulateKeyEvent;
import com.bezirk.hardwareevents.beacon.BeaconsDetectedEvent;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

/**
 * Created by vicky on 10/22/2016.
 */

public class AppApplication extends Application {
    static Bezirk bezirk;
    static HouseStateEvent houseStateEvent;

    @Override
    public void onCreate() {
        super.onCreate();
        bezirk= AppConfig.instantiate(getApplicationContext());
        EventSet localEventSet = new EventSet(HouseStateEvent.class);
        localEventSet.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
                if (event instanceof HouseStateEvent) {
                    houseStateEvent  = (HouseStateEvent) event;
                    Log.e("Application", "HOuse state event " + houseStateEvent.ReceiverId());
                }
            }
        });
    }
}
