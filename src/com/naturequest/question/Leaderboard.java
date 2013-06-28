package com.naturequest.question;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.naturequest.User;

public class Leaderboard {
	private JSONArray leaderJSON;
	private ArrayList<User> users;

	public Leaderboard(JSONArray json){
		this.leaderJSON = json;
		users = new ArrayList<User>();
		init();
	}

	private void init(){
		for(int i = 0; i<leaderJSON.length(); i++){
			JSONObject userJSON;
			User temp = null;
			try {
				userJSON = leaderJSON.getJSONObject(i);
				temp = new User();
				temp.setUsername(userJSON.getString("username"));
				temp.setScore(userJSON.getInt("score"));
				temp.setPicture(userJSON.getString("picture"));
			

			} catch (JSONException e) {
				
				e.printStackTrace();
			}

			users.add(temp);			

		}
	}

	public ArrayList<User> getLeaderboard(){
		return users;
	}







}
