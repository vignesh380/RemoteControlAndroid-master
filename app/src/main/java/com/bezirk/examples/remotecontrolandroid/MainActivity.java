package com.bezirk.examples.remotecontrolandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bezirk.examples.AppConfig;
import com.bezirk.examples.events.AliveEvent;
import com.bezirk.examples.events.SimulateKeyEvent;
import com.bezirk.hardwareevents.beacon.Beacon;
import com.bezirk.hardwareevents.beacon.BeaconsDetectedEvent;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.android.BezirkMiddleware;
import com.bezirk.middleware.android.IntentSender;
import com.bezirk.middleware.core.proxy.Config;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private static final int LEFT_ARROW = 37;
    private static final int RIGHT_ARROW = 39;
    private static final int UP_ARROW = 38;
    private static final int DOWN_ARROW = 40;
    private static final int SPACE_BAR = 32;
    private static final String beaconMessage = "Shaswath is the man ";
    static Timer timer;
    private Bezirk bezirk;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bezirk = AppApplication.bezirk;

        Intent i = new Intent(this,DashBoard.class);
        startActivity(i);
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
