/**
 * ****************************************************************************
 * Copyright (C) 2016 Open Universiteit Nederland
 * <p/>
 * This library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 * <p/>
 * Contributors: Ioannis D. Zaimidis
 * ****************************************************************************
 */
package com.example.ioannisd.ounbeacon_v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.example.ioannisd.ounbeacon_v1.Rooms.Summer;
import com.example.ioannisd.ounbeacon_v1.Rooms.Winter;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

import java.util.Arrays;
import java.util.List;


public class Main extends Activity implements SensorEventListener{

    private CardScrollView mCardScroller;
    private View mView;
    private BeaconManager beaconManager;
    private SensorManager sensorManager;
    private Sensor stepSensor;
    private Region Teli;
    Beacon[] Beacons;
    String mac_address;
    Distance[] BeaconDist;
    private enum Distance {UNKNOWN, IMMEDIATE, NEAR, FAR}
    private static final String TAG="Mac Address";
    private static final String ESTIMOTE_PROXIMITY_UUID= "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    Double[] threshold = {0.4,1.5,3.1};
    int testvar;

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Beacons = new Beacon[1];
        BeaconDist = new Distance[1];
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, stepSensor,SensorManager.SENSOR_DELAY_NORMAL);
        Teli= new Region("regionID", ESTIMOTE_PROXIMITY_UUID, null, null);
        beaconManager =new BeaconManager(getApplicationContext());
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                final int k=beacons.size();
                Beacons = Arrays.copyOf(Beacons, Beacons.length * k);
                BeaconDist = Arrays.copyOf(BeaconDist, BeaconDist.length * k);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testvar=0;
                        int j=0;
                        for (Beacon beacon:beacons) {
                            Beacons[j] = beacon;
                            mac_address = beacon.getMacAddress();
                            Log.d(TAG,"This beacons address is "+mac_address);
                            if (Beacons[j] != null) {
                                double BeaconDistance = Utils.computeAccuracy(Beacons[j]);
                                Log.d(TAG, "BeaconDistance: " + BeaconDistance + " " + BeaconDist[j]);
                                if (BeaconDistance > threshold[2]) {
                                    BeaconDist[j] = Distance.UNKNOWN;
                                } else if (BeaconDistance < threshold[2] && BeaconDistance > threshold[1]) {
                                    BeaconDist[j] = Distance.FAR;
                                } else if (BeaconDistance < threshold[1] && BeaconDistance > threshold[0]) {
                                    BeaconDist[j] = Distance.NEAR;
                                } else if (BeaconDistance < threshold[0]) {
                                    BeaconDist[j] = Distance.IMMEDIATE;
                                }}//if

                            if (Beacons[j] != null) {
                                //double BeaconDistance = Utils.computeAccuracy(Beacons[j]);
                               // Log.d(TAG, "BeaconDistance: " + BeaconDistance + " " + BeaconDist[j]);
                                if (mac_address.equals("DB:52:3B:B1:20:EB")&&((BeaconDist[j]==Distance.NEAR) || (BeaconDist[j]==Distance.IMMEDIATE))) {
                                    //testvar=1;
                                    //setContentView(buildView());
                                    startActivity(new Intent(Main.this, Winter.class));
                                } else if (mac_address.equals("C6:5B:D7:05:A5:C7")&&((BeaconDist[j]==Distance.NEAR) || (BeaconDist[j]==Distance.IMMEDIATE))) {
                                    //testvar=2;
                                    //setContentView(buildView());
                                    startActivity(new Intent(Main.this, Summer.class));
                                } else if (mac_address.equals("D7:7F:33:05:74:2C")&&((BeaconDist[j]==Distance.NEAR) || (BeaconDist[j]==Distance.IMMEDIATE))) {
                                    testvar=3;
                                    setContentView(buildView());
                                }//if
                            } else {
                                setContentView(errorView());
                            }//else, I think this else needs to go or just change
                                j++;
                        }//for
                        //setContentView(buildView());
                    }//run
                }/**thread*/);
            }//onBeaconDiscovered
        }/** listener */)/**listener*/;

        mView = buildView();
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardScrollAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public Object getItem(int position) {
                return mView;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return mView;
            }

            @Override
            public int getPosition(Object item) {
                if (mView.equals(item)) {
                    return 0;
                }
                return AdapterView.INVALID_POSITION;
            }
        });
        /** Handle the TAP event.
        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Plays disallowed sound to indicate that TAP actions are not supported.
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(Sounds.DISALLOWED);
            }
        });*/
        setContentView(mCardScroller);
    }//onCreate

    @Override
    protected void onResume() {
        super.onResume();
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    beaconManager.startRanging(Teli);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        try {
            beaconManager.stopRanging(Teli);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        super.onPause();
    }


    private View buildView() {
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        if (testvar==0){
        card.setText(R.string.hello_mes);}
        else if (testvar==1){
            card.setText("Winter");
        }else if (testvar==2){
            card.setText("Zomer");
        }
        else if (testvar==3){
            card.setText("Herfst");
        }
        return card.getView();
    }

    private View errorView(){
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText(R.string.nothing);
        return card.getView();

    }

    private View winterView(){
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText(R.string.winter);
        return card.getView();

    }
    private View zomerView(){
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText(R.string.zomer);
        return card.getView();

    }
    private View herfstView(){
        CardBuilder card = new CardBuilder(this, CardBuilder.Layout.TEXT);
        card.setText(R.string.herfst);
        return card.getView();

    }
}
