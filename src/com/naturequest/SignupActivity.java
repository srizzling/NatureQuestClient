package com.naturequest;


import com.naturequest.R;

import android.app.Activity;
import android.os.Bundle;
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
		// TODO Auto-generated method stub
		
	}
}
