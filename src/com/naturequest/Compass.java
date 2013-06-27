package com.naturequest;

import java.util.List;

import com.naturequest.question.Question;
import com.naturequest.radar.GPSTracker;
import com.naturequest.radar.GeoUtils;

import android.content.Context;
import android.hardware.GeomagneticField;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Compass implements SensorEventListener {
	private static final String TAG = "Compass";

	private SensorManager sensorManager;
	private Sensor gsensor;
	private Sensor msensor;
	private float[] mGravity = new float[3];
	private float[] mGeomagnetic = new float[3];
	private float azimuth = 0f;
	private float currectAzimuth = 0;

	// compass arrow to rotate
	public ImageView arrowView = null;

	private Context context;

	public TextView bearing;

	private Location myLocation;

	private Question targetQ;

	public Compass(Context context) {
		this.context=context;
		sensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		msensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
	}

	public void start() {
		GPSTracker gps = new GPSTracker(context);
		myLocation = gps.getLocation();
		List<Question> questions= Game.getGame().getSortedQuestions(context);
		targetQ=questions.get(0);
		sensorManager.registerListener(this, gsensor,
				SensorManager.SENSOR_DELAY_GAME);
		sensorManager.registerListener(this, msensor,
				SensorManager.SENSOR_DELAY_GAME);
	}

	public void stop() {
		GPSTracker gps = new GPSTracker(context);
		myLocation = gps.getLocation();
		sensorManager.unregisterListener(this);
	}

	private void adjustArrow() {
		if (arrowView == null) {
			Log.i(TAG, "arrow view is not set");
			return;
		}

		Log.i(TAG, "will set rotation from " + currectAzimuth + " to "
				+ azimuth);

		Animation an = new RotateAnimation(-currectAzimuth, -azimuth,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		currectAzimuth = azimuth;

		an.setDuration(500);
		an.setRepeatCount(0);
		an.setFillAfter(true);

		arrowView.startAnimation(an);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		final float alpha = 0.97f;

		synchronized (this) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

				mGravity[0] = alpha * mGravity[0] + (1 - alpha)
						* event.values[0];
				mGravity[1] = alpha * mGravity[1] + (1 - alpha)
						* event.values[1];
				mGravity[2] = alpha * mGravity[2] + (1 - alpha)
						* event.values[2];

				// mGravity = event.values;

				// Log.e(TAG, Float.toString(mGravity[0]));
			}

			if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				// mGeomagnetic = event.values;

				mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha)
						* event.values[0];
				mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha)
						* event.values[1];
				mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha)
						* event.values[2];
				// Log.e(TAG, Float.toString(event.values[0]));

			}

			float R[] = new float[9];
			float I[] = new float[9];
			boolean success = SensorManager.getRotationMatrix(R, I, mGravity,
					mGeomagnetic);
			if (success) {
				float orientation[] = new float[3];
				SensorManager.getOrientation(R, orientation);

				// Log.d(TAG, "azimuth (rad): " + azimuth);
				azimuth = (float) Math.toDegrees(orientation[0]); // orientation
				
				Location destLocation = new Location("test");
				//List<Question> questions = Game.getGame().getSortedQuestions(context);
				//Question q = questions.get(0);				
				
				destLocation.setLatitude(targetQ.getLat());
				destLocation.setLongitude(targetQ.getLongitude());
				

			

				GeomagneticField geoField = new GeomagneticField(
						Double.valueOf(myLocation.getLatitude()).floatValue(),
						Double.valueOf(myLocation.getLongitude()).floatValue(),
						Double.valueOf(myLocation.getAltitude()).floatValue(),
						System.currentTimeMillis());
				azimuth += geoField.getDeclination(); 
				
				float bearing = myLocation.bearingTo(destLocation);
				azimuth = azimuth - bearing + 90;



				
				adjustArrow();
			}
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
