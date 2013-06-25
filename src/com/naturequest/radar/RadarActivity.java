/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.naturequest.radar;

import com.naturequest.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

/**
 * Simple Activity wrapper that hosts a {@link RadarView}
 *
 */
public class RadarActivity extends Activity {

    private static final int LOCATION_UPDATE_INTERVAL_MILLIS = 1000;

    private static final int MENU_STANDARD = Menu.FIRST + 1;

    private static final int MENU_METRIC = Menu.FIRST + 2;

    private static final String RADAR = "radar";
    
    private static final String PREF_METRIC = "metric";

    private SensorManager mSensorManager;

    private RadarView mRadar;

    private LocationManager mLocationManager;

    private SharedPreferences mPrefs;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
        
        
        //If Radar do this
        setContentView(R.layout.radar);
        mRadar = (RadarView) findViewById(R.id.radar);
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
     
        // Metric or standard units?
        mPrefs = getSharedPreferences(RADAR, MODE_PRIVATE);
        boolean useMetric = mPrefs.getBoolean(PREF_METRIC, false);
        mRadar.setUseMetric(useMetric);
        
        // Read the target from our intent
        Intent i = getIntent();
        int latE6 = (int)(i.getFloatExtra("latitude", 0) * GeoUtils.MILLION);
        int lonE6 = (int)(i.getFloatExtra("longitude", 0) * GeoUtils.MILLION);
        mRadar.setTarget(latE6, lonE6);
        mRadar.setDistanceView((TextView) findViewById(R.id.distance));
        setUseMetric(true);
        
        
        
      //If Not Radar do this
    }
    
    @Override
    protected void onResume()
    {
        super.onResume();
        
        
        
        
        
      //If Radar do this
        mSensorManager.registerListener(mRadar, SensorManager.SENSOR_ORIENTATION,
                SensorManager.SENSOR_DELAY_GAME);
 
        // Start animating the radar screen
        mRadar.startSweep();
        
        // Register for location updates
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                LOCATION_UPDATE_INTERVAL_MILLIS, 1, mRadar);
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                LOCATION_UPDATE_INTERVAL_MILLIS, 1, mRadar);
    }

    @Override
    protected void onPause()
    {
    	
    	
    	
    	
    	//If Radar do this
        mSensorManager.unregisterListener(mRadar);
        mLocationManager.removeUpdates(mRadar);
        
        // Stop animating the radar screen
        mRadar.stopSweep();
        super.onStop();
    }

   

    private void setUseMetric(boolean useMetric) {
        SharedPreferences.Editor e = mPrefs.edit();
        e.putBoolean(PREF_METRIC, useMetric);
        e.commit();
        mRadar.setUseMetric(useMetric);
    }
}