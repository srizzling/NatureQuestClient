package com.naturequest;



import java.util.ArrayList;
import java.util.List;

import com.naturequest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileActivity extends Activity {
	 
	Button button;
	Button theme;
	ImageView proImage;
	ImageView bgImage;
	List<Integer> picArr = new ArrayList<Integer>();
	List<Integer> bgArr = new ArrayList<Integer>();
	private int count;
	private int themeCount;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		picArr.add(R.drawable.locations_button);
		picArr.add(R.drawable.camera_button);
		picArr.add(R.drawable.profile_button);
		
		bgArr.add(R.drawable.background);
		bgArr.add(R.drawable.background2);
		bgArr.add(R.drawable.background3);
		
		addListenerOnButton();
	}
 
	
	public void addListenerOnButton() {
 
		proImage = (ImageView) findViewById(R.id.imageView1);
		bgImage = (ImageView) findViewById(R.id.bgTheme);
		
		button = (Button) findViewById(R.id.btnChangeImage);
		theme = (Button) findViewById(R.id.btnChangeTheme);
		
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
				
				
				if(count<picArr.size()-1)
				{
					count++;
				}
				else{
					count=0;
				}
				proImage.setImageResource(picArr.get(count));
			}
 
		});
		theme.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
				
				
				if(themeCount<bgArr.size()-1)
				{
					themeCount++;
				}
				else{
					themeCount=0;
				}
Log.d("Profile Activity", "Change theme button pressed");
Game.getGame().setBackgroundTheme(bgArr.get(themeCount));
}
 
		});
 
	
 
	}
 
}
