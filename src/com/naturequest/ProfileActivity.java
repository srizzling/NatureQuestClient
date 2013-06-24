package com.naturequest;



import com.naturequest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends Activity
{
	private EditText usernameEditText;
	private Button selectQuestButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		this.usernameEditText = (EditText)findViewById(R.id.usernameEditText);
		
		this.selectQuestButton = (Button)findViewById(R.id.selectQuestButton);
		
		this.selectQuestButton.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View arg0)
			{
				Intent intent = new Intent (ProfileActivity.this,QuestConfirmActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		this.usernameEditText.setText(Game.getGame().getCurrentUser().getUsername());
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		String username = this.usernameEditText.getText().toString();

		if (username != null && !username.isEmpty())
		{
			Game.getGame().getCurrentUser().setUsername(username);
		}
		
		this.selectQuestButton.setText(Game.getGame().getCurrentQuest());
	}
}
