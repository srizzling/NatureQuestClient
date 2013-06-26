package com.naturequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.R;
import com.naturequest.serverapi.QuestAPI;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity
{
	private EditText nameEditText;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText confirmPasswordEditText;
	private Button signupButton;

	private String name;
	private String email;
	private String password;
	private String confirmPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		this.nameEditText = (EditText)findViewById(R.id.nameEditText);
		this.emailEditText = (EditText)findViewById(R.id.emailEditText);
		this.passwordEditText = (EditText)findViewById(R.id.passwordEditText);
		this.confirmPasswordEditText = (EditText)findViewById(R.id.confirmPasswordEditText);
		this.signupButton = (Button)findViewById(R.id.signupButton);

		this.signupButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View arg0)
			{
				onSignupClicked();
			}
		});
	}

	protected void onSignupClicked()
	{
		String dialogText = "";
		boolean isMissingField = false;

		this.name = this.nameEditText.getText().toString();
		this.email = this.emailEditText.getText().toString();
		this.password = this.passwordEditText.getText().toString();
		this.confirmPassword = this.confirmPasswordEditText.getText().toString();

		if (this.name == null || this.name.trim().isEmpty())
		{
			dialogText = "Please enter your name";
			isMissingField = true;
		}

		if (isMissingField)
		{
			CustomDialog customDialog = new CustomDialog(this, "Error");
			customDialog.showTextView(dialogText);
			customDialog.setPrimaryButton("Ok", null);
			customDialog.show();
		}
		else
		{
			attemptSignup();
		}
	}

	private void attemptSignup()
	{
		final Context rootApp = this;
		final Context rootApp2 = this;
		RequestParams params = new RequestParams();
		params.put("email", email);
		params.put("password", password);
		params.put("password_confirmation", confirmPassword);
		QuestAPI.post("signup.json", params, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject arg0) {
				String success = null;
				try {
					success = arg0.getString("success");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(success.equals("true")){
					String info = null;
					try {
						info = arg0.getString("info");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					CustomDialog customDialog = new CustomDialog(rootApp, "Status");
					customDialog.showTextView(info);
					customDialog.setPrimaryButton("Ok", null);
					customDialog.show();
				}

			}
			@Override
			protected Object parseResponse(String arg0) throws JSONException {
				Log.d("response", arg0);
				JSONObject json = new JSONObject(arg0);
				String success=json.getString("success");
			
				
				if(success.equals("false")){
					JSONArray info = json.getJSONArray("info");
					CustomDialog customDialog = new CustomDialog(rootApp, "Status");
					customDialog.showTextView(info.getString(0));
					customDialog.setPrimaryButton("Ok", null);
					customDialog.show();
				}




				return super.parseResponse(arg0);
			}

		});

	}
}
