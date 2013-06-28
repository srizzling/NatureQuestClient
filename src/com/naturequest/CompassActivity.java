package com.naturequest;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.naturequest.R;

public class CompassActivity extends FragmentActivity {

	private static final String TAG = "CompassActivity";

	private Compass compass;
	private Spinner spinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass);
		spinner = (Spinner) findViewById(R.id.spinner1);
		
		List<String> list = Game.getGame().getQuestionsStr();
	
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);

		compass = new Compass(this);
		compass.arrowView = (ImageView) findViewById(R.id.main_image_arrow);
		compass.bearing = (TextView) findViewById(R.id.bearing);
		
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "start compass");
		compass.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		compass.stop();
	}

	@Override
	protected void onResume() {
		super.onResume();
		compass.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "stop compass");
		compass.stop();
	}

}
