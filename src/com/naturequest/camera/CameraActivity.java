package com.naturequest.camera;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.CustomDialog;
import com.naturequest.R;
import com.naturequest.question.QuestionActivity;
import com.naturequest.radar.GPSTracker;
import com.naturequest.serverapi.QuestAPI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

public class CameraActivity extends Activity implements Camera.PreviewCallback, ZBarConstants{

	private static final String TAG = "ZBarScannerActivity";
	private CameraPreview mPreview;
	private Camera mCamera;
	private ImageScanner mScanner;
	private Handler mAutoFocusHandler;
	private boolean mPreviewing = true;
	private boolean geotag=false;
	private Context activityContext;
	private boolean success;
	private String message;

	static {
		System.loadLibrary("iconv");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		
		if(!isCameraAvailable()) {
			// Cancel request if there is no rear-facing camera.
			cancelRequest();
			return;
		}

		// Hide the window title.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mAutoFocusHandler = new Handler();

		// Create and configure the ImageScanner;
		setupScanner();

		// Create a RelativeLayout container that will hold a SurfaceView,
		// and set it as the content of our activity.
		mPreview = new CameraPreview(this, this, autoFocusCB);
		setContentView(R.layout.camera);
		FrameLayout cameraLayout = (FrameLayout) findViewById(R.id.cameraPreview);
		cameraLayout.addView(mPreview);


		//Geotag
		final ToggleButton geotagButton = (ToggleButton) findViewById(R.id.toggleButton1);
		geotagButton.setText("Geotag");

		final TextView geoLabel = (TextView) findViewById(R.id.geoLabel);
		geoLabel.setText("Geotag can be only done by owners of the QRCode");

		geotagButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					geotagButton.setText("Geotag");
					geotag=true;
				}
				else{
					geotagButton.setText("Geotag");
					geotag=false;
				}

			}
		});

	}



	public void setupScanner() {
		mScanner = new ImageScanner();
		mScanner.setConfig(0, Config.X_DENSITY, 3);
		mScanner.setConfig(0, Config.Y_DENSITY, 3);

		int[] symbols = getIntent().getIntArrayExtra(SCAN_MODES);
		if (symbols != null) {
			mScanner.setConfig(Symbol.NONE, Config.ENABLE, 0);
			for (int symbol : symbols) {
				mScanner.setConfig(symbol, Config.ENABLE, 1);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Open the default i.e. the first rear facing camera.
		mCamera = Camera.open();
		if(mCamera == null) {
			// Cancel request if mCamera is null.
			cancelRequest();
			return;
		}

		mPreview.setCamera(mCamera);
		mPreview.showSurfaceView();

		mPreviewing = true;
	}

	@Override
	protected void onPause() {
		super.onPause();

		// Because the Camera object is a shared resource, it's very
		// important to release it when the activity is paused.
		if (mCamera != null) {
			mPreview.setCamera(null);
			mCamera.cancelAutoFocus();
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();

			// According to Jason Kuang on http://stackoverflow.com/questions/6519120/how-to-recover-camera-preview-from-sleep,
			// there might be surface recreation problems when the device goes to sleep. So lets just hide it and
			// recreate on resume
			mPreview.hideSurfaceView();

			mPreviewing = false;
			mCamera = null;
		}
	}

	public boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	public void cancelRequest() {
		Intent dataIntent = new Intent();
		dataIntent.putExtra(ERROR_INFO, "Camera unavailable");
		setResult(Activity.RESULT_CANCELED, dataIntent);
		finish();
	}

	public void onPreviewFrame(byte[] data, Camera camera) {
		activityContext=this;
		Camera.Parameters parameters = camera.getParameters();
		Camera.Size size = parameters.getPreviewSize();

		Image barcode = new Image(size.width, size.height, "Y800");
		barcode.setData(data);

		int result = mScanner.scanImage(barcode);

		if (result != 0) {
			// Because the Camera object is a shared resource, it's very
			// important to release it when the activity is paused.
			if (mCamera != null) {
				mPreview.setCamera(null);
				mCamera.cancelAutoFocus();
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();
				mCamera.release();

				// According to Jason Kuang on http://stackoverflow.com/questions/6519120/how-to-recover-camera-preview-from-sleep,
				// there might be surface recreation problems when the device goes to sleep. So lets just hide it and
				// recreate on resume
				mPreview.hideSurfaceView();

				mPreviewing = false;
				mCamera = null;
			}





			SymbolSet syms = mScanner.getResults();
			for (Symbol sym : syms) {
				String symData = sym.getData();
				if (!TextUtils.isEmpty(symData)) {
					Intent dataIntent = new Intent(this, QuestionActivity.class);


					Bundle bundle = new Bundle();
					//Do a check of the data here

					if(symData.length() != 4){


						final CustomDialog customDialog = new CustomDialog(this, "Error");
						customDialog.showTextView("This is not a Nature Quest QR Code");
						customDialog.setPrimaryButton("Ok", new OnClickListener() {

							@Override
							public void onClick(View v) {				

								mCamera = Camera.open();
								if(mCamera == null) {
									// Cancel request if mCamera is null.
									cancelRequest();
									return;
								}

								mPreview.setCamera(mCamera);
								mPreview.showSurfaceView();

								mPreviewing = true;
								customDialog.dismiss();
							}
						});
						customDialog.show();



					}else{
						bundle.putString("ref", symData);
						if(geotag==false){
							dataIntent.putExtras(bundle);
							startActivity(dataIntent);
						}
						else{
							GPSTracker gps = new GPSTracker(this);
							if(gps.canGetLocation()){ 
								RequestParams params = new RequestParams();
								double lat=gps.getLatitude(); // returns latitude
								double lg = gps.getLongitude(); // returns longitude
								params.put("r",symData);
								params.put("long",""+lg);
								params.put("lat", ""+lat);
								
								QuestAPI.post("geotag.json", params, new JsonHttpResponseHandler(){
									
									
									@Override
									public void onSuccess(JSONObject response){
										message = "";
										try {
											message = response.getString("message");
											success=response.getBoolean("success");
										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
									}
									@Override
									protected Object parseResponse(String arg0)
											throws JSONException {
										Log.d("message",arg0);
										message = "";
										try{
											JSONObject response = new JSONObject(arg0);
											message=response.getString("message");
											success=response.getBoolean("success");
											
										}
										catch(JSONException e){
											
											
										}
										return super.parseResponse(arg0);
									}
						
									
									
									
									
								});
								
								
							}
						}
					}
					if(success){
						Toast.makeText(this, "Sucessful! "+message, Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(this, "Error! "+message, Toast.LENGTH_LONG).show();
					}

					break;
				}
			}
		}
	}
	private Runnable doAutoFocus = new Runnable() {
		public void run() {
			if(mCamera != null && mPreviewing) {
				mCamera.autoFocus(autoFocusCB);
			}
		}
	};

	// Mimic continuous auto-focusing
	Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			mAutoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};
}