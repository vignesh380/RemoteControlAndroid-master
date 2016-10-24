package com.bezirk.examples.remotecontrolandroid;

import android.app.Application;
import android.util.Log;

import com.bezirk.examples.AppConfig;
import com.bezirk.examples.events.AliveEvent;
import com.bezirk.examples.events.HouseStateEvent;
import com.bezirk.examples.events.SimulateKeyEvent;
import com.bezirk.hardwareevents.beacon.Beacon;
import com.bezirk.hardwareevents.beacon.BeaconsDetectedEvent;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vicky on 10/22/2016.
 */

public class AppApplication extends Application {
    static Bezirk bezirk;
    static HouseStateEvent houseStateEvent;
    Boolean isConnectedFirstTime = true;
    int count = 0;
    int missed = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        bezirk= AppConfig.instantiate(getApplicationContext());
        setUpBeacon();
        EventSet houseStateEventType = new EventSet(HouseStateEvent.class);
        houseStateEventType.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
                if (event instanceof HouseStateEvent) {
                    houseStateEvent  = (HouseStateEvent) event;
                    Log.e("Application", "House state event " + houseStateEvent.ReceiverId());
                }
            }
        });
    }

    private void setUpBeacon() {
        EventSet eventSet = new EventSet(BeaconsDetectedEvent.class);
        final TimerTask aliveMessage = new TimerTask() {
            @Override
            public void run() {

                if(count==0)
                    missed++;
                else
                    missed = 0;
                if(missed == 0){
                    count = 0;
                    Log.e("beacon", "hit");
                    AliveEvent a = new AliveEvent("vignesh","CH");
                    bezirk.sendEvent(a);
                    Log.e("aliveevent", a.toString());
                }
                else{
                    Log.e("Beacon","out of loop");
                }
            }
        };

        eventSet.setEventReceiver(new EventSet.EventReceiver() {
            @Override
            public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
                if (event instanceof BeaconsDetectedEvent) {
                    BeaconsDetectedEvent beaconsDetectedEvt = (BeaconsDetectedEvent) event;
//                    Log.e("BEACON", "Received beacons detect event");
                    for (Beacon beacon : beaconsDetectedEvt.getBeacons()) {
//                        count = count + 1;
                        if(isConnectedFirstTime){
                            isConnectedFirstTime = false;
                            Timer timer = new Timer();
                            timer.schedule(aliveMessage, 0, 1000*10);
                        }
                        else{
                            count++;
                        }
                    }
                }
            }
        });

        bezirk.subscribe(eventSet);
        Log.e("BEACON", "Listening for Beacon events");

    }

}
