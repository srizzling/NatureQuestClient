package com.naturequest.question;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;

public class Question
{

	private String question;	
	private ArrayList<String> answers;
	private String correctAns;

	public Question(JSONObject question){
		try {
			this.question = question.getString("question");
			String answer1 = question.getString("SAnswer");
			
			
			
			
			
			
			
			
		} catch (JSONException e) {
			
			
		}	
	}
}
