package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LeaderboardScoreAdapter extends ArrayAdapter<LeaderboardScore>
{
	public LeaderboardScoreAdapter(Context context, List<LeaderboardScore> items)
	{
		super(context, R.layout.leaderboard_list_item, new ArrayList<LeaderboardScore>(items));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LeaderboardScore leaderboardScoreItem = getItem(position);
		
		if (convertView == null)
		{
			LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.leaderboard_list_item, null);
		}
		
		TextView rankTextView = (TextView)convertView.findViewById(R.id.rankTextView);

		// TODO set font
		//rankTextView.setTypeface(tf);
		
		TextView nameTextView = (TextView)convertView.findViewById(R.id.nameScoreTextView);

		// TODO set font
		//nameTextView.setTypeface(tf);
		
		rankTextView.setText(String.valueOf(position + 1));
		nameTextView.setText(leaderboardScoreItem.getName() + " - " + String.valueOf(leaderboardScoreItem.getScore()));
		
		return convertView;
	}
}
