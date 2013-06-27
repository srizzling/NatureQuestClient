package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class LeaderboardActivity extends Activity
{
	List<LeaderboardScore> scores = new ArrayList<LeaderboardScore>();
	
	private TextView leaderboardTitleTextView;
	private ImageView profileImageView;
	private TextView infoTextView;
	private TextView rankTitleTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leaderboard);
		
		this.leaderboardTitleTextView = (TextView)findViewById(R.id.leaderboardTitleTextView);
		
		// TODO set font
		//this.leaderboardTitleTextView.setTypeface(tf);
		
		this.profileImageView = (ImageView)findViewById(R.id.profileImageView);
		
		this.rankTitleTextView = (TextView)findViewById(R.id.rankingsTitleTextView);
		
		// TODO set font
		//this.rankTitleTextView.setTypeface(tf);
		
		this.infoTextView = (TextView)findViewById(R.id.infoTextView);
		
		// TODO set font
		//this.infoTextView.setTypeface(tf);
				
		// load scores from server here
		getScoresFromServer();
	}
	
	private void getScoresFromServer()
	{
		successfullyGotScores();
	}

	private void successfullyGotScores()
	{
		this.scores = new ArrayList<LeaderboardScore>(){{
			add(new LeaderboardScore("name", 1000, 200, "Zoolander"));
			add(new LeaderboardScore("Lit", -500, 0, "Log in"));
		}};
		
		ListView listView = (ListView)findViewById(R.id.listView);
		
		LeaderboardScoreAdapter adapter = new LeaderboardScoreAdapter(this, scores);
		listView.setAdapter(adapter);
		listView.setCacheColorHint(Color.TRANSPARENT);
		
		if (this.scores != null && !this.scores.isEmpty())
		{
			this.infoTextView.setText(this.scores.get(0).getInfo());
		}
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				LeaderboardActivity.this.infoTextView.setText(LeaderboardActivity.this.scores.get(position).getInfo());
			}
		});
	}
}
