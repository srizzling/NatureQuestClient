package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.naturequest.question.Leaderboard;
import com.naturequest.serverapi.QuestAPI;


import android.app.Activity;
import android.content.Context;
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


	private TextView leaderboardTitleTextView;
	private ImageView profileImageView;
	private TextView infoTextView;
	private TextView rankTitleTextView;
	private List<User> scores;
	private LeaderboardScoreAdapter adapter;



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
		scores = new ArrayList<User>();
		scores= Game.getGame().getLeaderboard();	
		
		
		ListView listView = (ListView)findViewById(R.id.listView);
		 adapter = new LeaderboardScoreAdapter(this, scores);
		listView.setAdapter(adapter);
		listView.setCacheColorHint(Color.TRANSPARENT);
		
		if (scores != null && !scores.isEmpty())
		{
			infoTextView.setText(Game.getGame().getCurrentUser().getInfo());
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
