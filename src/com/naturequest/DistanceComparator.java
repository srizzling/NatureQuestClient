package com.naturequest;

import java.util.Comparator;

import android.content.Context;

import com.loopj.android.http.RequestParams;
import com.naturequest.question.Question;
import com.naturequest.radar.GPSTracker;
import com.naturequest.radar.GeoUtils;

public class DistanceComparator implements Comparator<Question>{
	GPSTracker gps;
	Context view;

	@Override
	public int compare(Question arg0, Question arg1) {
		double currentLat;
		double currentLg;
		if(gps.canGetLocation()){ 		
			currentLat=gps.getLatitude(); // returns latitude
			currentLg = gps.getLongitude(); 

			double latObj1 = (double) arg0.getLat();
			double longObj1 =  (double) arg0.getLongitude();

			double latObj2 = (double) arg1.getLat();
			double longObj2 = (double) arg1.getLongitude();



			double distance1 = GeoUtils.distanceKm(latObj1, longObj1, currentLat, currentLg);
			double distance2 = GeoUtils.distanceKm(latObj2, longObj2, currentLat, currentLg);

			if(distance2 > distance1){
				return 1;
			} 
			else{
				return -1;	
			}

		}



		return 0;
	}

	public void setContext(Context view){
		this.view = view;
		gps = new GPSTracker(view);
	}
}