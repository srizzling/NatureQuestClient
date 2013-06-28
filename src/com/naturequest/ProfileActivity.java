package com.naturequest;



import java.util.ArrayList;
import java.util.List;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.R;
import com.naturequest.serverapi.QuestAPI;

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
	private boolean saveVisable;
	ImageView proImage;
	ImageView bgImage;
	List<Integer> picArr = new ArrayList<Integer>();
	List<Integer> bgArr = new ArrayList<Integer>();
	private int count;
	private int themeCount;
	private int pid;
	private Integer pickedImage;
	private Button save;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		saveVisable=false;
		setContentView(R.layout.profile);
		proImage = (ImageView) findViewById(R.id.imageView1);

		String profilePic = Game.getGame().getCurrentUser().getPicture();
		pid=0;
		
		
		picArr.add(R.drawable.profile_panda);
		picArr.add(R.drawable.profile_chick);
		picArr.add(R.drawable.profile_walrus);
		picArr.add(R.drawable.profile_bunny);
		
		
		if(profilePic.equals("panda")){
			pid = R.drawable.profile_panda;
		}
		else if(profilePic.equals("chick")){
			pid = R.drawable.profile_chick;
		}
		else if(profilePic.equals("walrus")){
			pid = R.drawable.profile_walrus;
		}
		else if(profilePic.equals("bunny")){
			pid = R.drawable.profile_bunny;
		}

		proImage.setImageResource(pid);



		bgArr.add(R.drawable.background);
		bgArr.add(R.drawable.background2);
		bgArr.add(R.drawable.background3);

		addListenerOnButton();
	}
	@Override
	protected void onResume() {

		super.onResume();


		save.setEnabled(!saveVisable);

	}


	public void addListenerOnButton() {

		proImage = (ImageView) findViewById(R.id.imageView1);
		bgImage = (ImageView) findViewById(R.id.bgTheme);
		//proImage.setImageResource();


		button = (Button) findViewById(R.id.btnChangeImage);
		theme = (Button) findViewById(R.id.btnChangeTheme);

		save = (Button) findViewById(R.id.savePicture);

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
				pickedImage = picArr.get(count);

				String pictureToSave="";
				if(pickedImage==R.drawable.profile_panda){
					pictureToSave="panda";
				}
				else if(pickedImage==R.drawable.profile_chick){
					pictureToSave="chick";
				}
				else if(pickedImage==R.drawable.profile_bunny){
					pictureToSave="bunny";
				}
				else if(pickedImage==R.drawable.profile_walrus){
					pictureToSave="walrus";
				}
				saveVisable = false;
				Game.getGame().getCurrentUser().setPicture(pictureToSave);

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

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				savePicture();
			}
		});



	}

	public void savePicture(){

		String pictureToSave="";
		if(pickedImage==R.drawable.profile_panda){
			pictureToSave="panda";
		}
		else if(pickedImage==R.drawable.profile_chick){
			pictureToSave="chick";
		}
		else if(pickedImage==R.drawable.profile_bunny){
			pictureToSave="bunny";
		}
		else if(pickedImage==R.drawable.profile_walrus){
			pictureToSave="walrus";
		}

		Game.getGame().getCurrentUser().setPicture(pictureToSave);



		//Save Picture to Server....
		RequestParams params = new RequestParams();
		params.put("picture",pictureToSave);
		QuestAPI.post("upPicture", params, new JsonHttpResponseHandler());


	}

}
