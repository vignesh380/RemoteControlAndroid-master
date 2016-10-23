package com.bezirk.examples.remotecontrolandroid;

import android.app.Application;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bezirk.examples.events.HouseStateEvent;
import com.bezirk.examples.events.Person;
import com.bezirk.examples.events.PersonStatus;
import com.bezirk.examples.events.SimulateKeyEvent;
import com.bezirk.examples.events.Task;
import com.bezirk.hardwareevents.beacon.Beacon;
import com.bezirk.hardwareevents.beacon.BeaconsDetectedEvent;
import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.addressing.ZirkEndPoint;
import com.bezirk.middleware.messages.Event;
import com.bezirk.middleware.messages.EventSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vicky on 10/22/2016.
 */

public class DashBoard extends AppCompatActivity{


    private RecyclerView vertical_recycler_view,horizontal_recycler_view;
    private ArrayList<Person> horizontalList;
    private ArrayList<Task> verticalList;
    private HorizontalAdapter horizontalAdapter;
    private  VerticalAdapter verticalAdapter;
    static Bezirk bezirk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dasboard_activity);

        vertical_recycler_view= (RecyclerView) findViewById(R.id.vertical_recycler_view);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);

        horizontalList=new ArrayList<Person>();
        if(AppApplication.houseStateEvent != null && AppApplication.houseStateEvent.houseState != null){
            horizontalList.addAll(AppApplication.houseStateEvent.houseState.People);
        }

        horizontalList.add(new Person("1","shaswath", PersonStatus.asleep));

        verticalList=new ArrayList<>();
        if(AppApplication.houseStateEvent != null && AppApplication.houseStateEvent.houseState != null){
            verticalList.addAll(AppApplication.houseStateEvent.houseState.Tasks);
        }
        verticalList.add(new Task(false, null, "taskName", "Description"));
        horizontalAdapter=new HorizontalAdapter(horizontalList);
        verticalAdapter=new VerticalAdapter(verticalList);


        LinearLayoutManager verticalLayoutmanager
                = new LinearLayoutManager(DashBoard.this, LinearLayoutManager.VERTICAL, false);
        vertical_recycler_view.setLayoutManager(verticalLayoutmanager);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        vertical_recycler_view.setLayoutManager(mLayoutManager);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(DashBoard.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        vertical_recycler_view.setAdapter(verticalAdapter);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
    }


//    private void setUpBeacon() {
//
//        EventSet eventSet = new EventSet(BeaconsDetectedEvent.class);
//        final TimerTask aliveMessage = new TimerTask() {
//            @Override
//            public void run() {
////                if(count==0)
////                    missed++;
////                if(missed == 0){
////                    count = 0;
////                    bezirk.sendEvent(new SimulateKeyEvent(37));
////                }
//                EventSet localEventSet = new EventSet(BeaconsDetectedEvent.class);
//                localEventSet.setEventReceiver(new EventSet.EventReceiver() {
//                    @Override
//                    public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
//                        if (event instanceof BeaconsDetectedEvent) {
//                            bezirk.sendEvent(new SimulateKeyEvent(37));
//                        }
//                    }
//                });
//                // localEventSet.setEventReceiver(null);
//            }
//        };
//        eventSet.setEventReceiver(new EventSet.EventReceiver() {
//            @Override
//            public void receiveEvent(Event event, ZirkEndPoint zirkEndPoint) {
//                if (event instanceof BeaconsDetectedEvent) {
//                    BeaconsDetectedEvent beaconsDetectedEvt = (BeaconsDetectedEvent) event;
////                    Log.e("BEACON", "Received beacons detect event");
//                    for (Beacon beacon : beaconsDetectedEvt.getBeacons()) {
////                        count = count + 1;
////
//                        Timer timer = new Timer();
//                        timer.schedule(aliveMessage, 0, 1000*60);
//                    }
//                }
//            }
//        });
//
//        bezirk.subscribe(eventSet);
//        Log.e("BEACON", "Listening for Beacon events");
//
//    }




    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {


        private List<Person> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;
            public ImageView imageView;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.horizontal_image_view);
                txtView = (TextView) view.findViewById(R.id.txtView);

            }
        }


        public HorizontalAdapter(List<Person> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontaltabcontent, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.txtView.setText(horizontalList.get(position).name);
            switch (horizontalList.get(position).status){
                case asleep:
                    holder.imageView.setImageResource(R.mipmap.sleep);
                    break;
                case in:
                    holder.imageView.setImageResource(R.mipmap.athome);
                    break;
                case out:
                    holder.imageView.setImageResource(R.mipmap.outofhouse);
                    break;
            }
            holder.imageView.setImageResource(R.mipmap.sleep);

            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DashBoard.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }



    public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

        private List<Task> verticalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.txtView);

            }
        }


        public VerticalAdapter(ArrayList<Task> verticalList) {
            this.verticalList = verticalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.verticaltabcontent, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.txtView.setText(verticalList.get(position).name + verticalList.get(position).description);
            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(DashBoard.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return verticalList.size();
        }
    }
}
/*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }
}
*/



