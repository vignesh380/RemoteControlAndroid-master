package com.bezirk.examples.remotecontrolandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bezirk.examples.events.SimulateKeyEvent;
import com.bezirk.hardwareevents.beacon.Beacon;
import com.bezirk.hardwareevents.beacon.BeaconsDetectedEvent;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.android.BezirkMiddleware;
import com.bezirk.middleware.core.proxy.Config;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private Bezirk bezirk;
    private static final int LEFT_ARROW = 37;
    private static final int RIGHT_ARROW = 39;
    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;
    private static final int SPACE_BAR = 32;
    private static final String beaconMessage = "Shaswath is the man ";
    static Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiate();
        setUpBeacon();



    }

    private void setUpBeacon() {

        EventSet eventSet = new EventSet(BeaconsDetectedEvent.class);
        final TimerTask aliveMessage = new TimerTask() {
            @Override
            public void run() {
//                if(count==0)
//                    missed++;
//                if(missed == 0){
//                    count = 0;
//                    bezirk.sendEvent(new SimulateKeyEvent(37));
//                }
                EventSet localEventSet = new EventSet(BeaconsDetectedEvent.class);
                localEventSet.setEventReceiver(new EventSet.EventReceiver() {
                    @Override
                    public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
                        if (event instanceof BeaconsDetectedEvent) {
                            bezirk.sendEvent(new SimulateKeyEvent(37));
                        }
                    }
                });
               // localEventSet.setEventReceiver(null);
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
//
                        Timer timer = new Timer();
                        timer.schedule(aliveMessage, 0, 1000*60);
                    }
                }
            }
        });

        bezirk.subscribe(eventSet);
        Log.e("BEACON", "Listening for Beacon events");

    }

    private void instantiate() {
        //Initialize the middleware and register your Zirk to get Bezirk instance.
        Config config = new Config.ConfigBuilder().setGroupName("SVY-YOCO").create();
        BezirkMiddleware.initialize(this, config);
        bezirk = BezirkMiddleware.registerZirk("vignesh-phone");

    }

    public void playPauseBtnPressed(View v) {
        bezirk.sendEvent(new SimulateKeyEvent(SPACE_BAR));
        System.out.println("Sent simulateKeyEvent for space bar");
    }

    public void backBtnPressed(View v) {
        bezirk.sendEvent(new SimulateKeyEvent(LEFT_ARROW));
        System.out.println("Sent simulateKeyEvent for left arrow");
    }

    public void forwardBtnPressed(View v) {
        bezirk.sendEvent(new SimulateKeyEvent(RIGHT_ARROW));
        System.out.println("Sent simulateKeyEvent for right arrow");
    }

    public void volumeDownBtnPressed(View v) {
        bezirk.sendEvent(new SimulateKeyEvent(DOWN_ARROW));
        System.out.println("Sent simulateKeyEvent for up arrow");
    }

    public void volumeUpBtnPressed(View v) {
        bezirk.sendEvent(new SimulateKeyEvent(UP_ARROW));
        System.out.println("Sent simulateKeyEvent for down arrow");
    }
}
