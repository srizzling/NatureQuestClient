package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.R;
import com.naturequest.question.Leaderboard;
import com.naturequest.question.Question;
import com.naturequest.serverapi.QuestAPI;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{
	private EditText usernameEditText;
	private EditText passwordEditText;
	private Button loginButton;
	private Button signupButton;
//change

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		this.usernameEditText = (EditText)findViewById(R.id.usernameEditText);
		this.passwordEditText = (EditText)findViewById(R.id.passwordEditText);

		this.loginButton = (Button)findViewById(R.id.loginButton);

		this.loginButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				login();
			}
		});

		this.signupButton = (Button)findViewById(R.id.signupButton);

		this.signupButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View arg0)
			{
				signup();
			}
		});
	}

	protected void login()
	{
		String username = this.usernameEditText.getText().toString();
		String password = this.passwordEditText.getText().toString();

		if (username == null || username.isEmpty())
		{
			CustomDialog customDialog = new CustomDialog(this, "Error");

			customDialog.showTextView("Please enter your username.");
			customDialog.setPrimaryButton("Ok", null);

			customDialog.show();
		}
		else if (password == null || password.isEmpty())
		{
			CustomDialog customDialog = new CustomDialog(this, "Error");

			customDialog.showTextView("Please enter your password.");
			customDialog.setPrimaryButton("Ok", null);

			customDialog.show();
		}
		else
		{
			//TODO
			//TODO remove this after, should check user and get question locations, leaderboard stuff here



			successfulLogin();




		}
	}


	//	/**
	//	 * Not sure if this method is correct.. Override with own method.
	//	 */
	//	private void successfulLogin()
	//	{
	//		User user = new User(this.usernameEditText.getText().toString(), this);
	//		
	//		List<Question> questions = new ArrayList<Question>();
	//		
	//		questions.add(new Question(new GeoPoint((int)(-40.214108 * 1E6), (int)(176.101489 * 1E6))));
	//		
	//		Game game = new Game(user, questions);
	//		Game.setGame(game);
	//		
	//		Intent intent = new Intent(this, MainMenuActivity.class);
	//		startActivity(intent);
	//	}

	private void successfulLogin(){
		makeServerCall();



	}



	private User makeServerCall() {

		final Intent intent = new Intent(this, QuestConfirmActivity.class);
		String username=this.usernameEditText.getText().toString();
		String password=this.passwordEditText.getText().toString();

		final User newUser = new User(username, "");

		RequestParams params = new RequestParams();
		params.put("email",username);
		params.put("password",password);
		final Context activityContext = this;

		QuestAPI.post("login.json", params, new JsonHttpResponseHandler() {				

			@Override
			public void onSuccess(JSONObject user) {       	               
				String token;
				String message;
				try {					
					token = user.getString("token");
					String picture = user.getString("picture");
					Log.d("picture", picture);
					Log.d("token",token);
					JSONArray json = user.getJSONArray("leaderboard");
					newUser.setToken(token);
					newUser.setPicture(picture);
					Game game = new Game(newUser);
					Game.setGame(game);
					Leaderboard leader = new Leaderboard(json);					
					Game.getGame().setLeaderboard(leader.getLeaderboard());		
					
					
					JSONArray questJSON = user.getJSONArray("quests");
					List<JSONObject> quests = new ArrayList<JSONObject>();	
					for(int i=0; i<questJSON.length(); i++){
						JSONObject questObj=questJSON.getJSONObject(i);						
						quests.add(questObj);
					}
									
								
					Game.getGame().setQuests(quests);
					
					Intent intent = new Intent(LoginActivity.this, QuestConfirmActivity.class);
					startActivity(intent);

					
						
					finish();


				} catch (JSONException e) {

					Log.d("Status","This happens");
					Log.d("Status",e.getMessage());
				}



			}

			@Override
			protected Object parseResponse(String arg0) throws JSONException {
				Log.d("Message", arg0);

				//Create JSON
				try{
					JSONObject response = new JSONObject(arg0);
					String message=response.getString("message");
					CustomDialog customDialog = new CustomDialog(activityContext, "Error");
					customDialog.showTextView(message);
					customDialog.setPrimaryButton("Ok", null);
					customDialog.show();
				}
				catch(JSONException e){
					
					
				}




				return super.parseResponse(arg0);
			}




		});



		return newUser;
	}

	protected void signup()
	{
		Intent intent = new Intent(this, SignupActivity.class);
		startActivity(intent);
	}
}