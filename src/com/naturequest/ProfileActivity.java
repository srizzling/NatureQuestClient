package com.naturequest;



import com.naturequest.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends Activity
{
	private EditText usernameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		this.usernameEditText = (EditText)findViewById(R.id.usernameEditText);
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		this.usernameEditText.setText(Game.getGame().getUser().getUsername());
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		String username = this.usernameEditText.getText().toString();

		if (username != null && !username.isEmpty())
		{
			Game.getGame().getUser().setUsername(username);
		}
	}
}
