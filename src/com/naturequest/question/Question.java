package com.naturequest.question;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;

public class Question
{

	private String question;	
	private ArrayList<String> answers;
	private String correctAns;
	
	private long longitude;
	private long lat;

	public Question(JSONObject question){
		try {
			this.question = question.getString("question");
			String answer1 = question.getString("SAnswer");
			String answer2 = question.getString("Answer2");
			String answer3 = question.getString("Answer3");
			String answer4 = question.getString("Answer4");
			
			correctAns = answer1;
			answers=new ArrayList<String>();
			answers.add(answer1);
			answers.add(answer2);
			answers.add(answer3);
			answers.add(answer4);
			
			
			this.setLat(question.getLong("lat"));
			this.setLongitude(question.getLong("Long"));
			
			
			
			
		} catch (JSONException e) {	}	
	}
	
	public Question(){
		
	}
	
	public ArrayList<String> getAnswers(){
		Collections.shuffle(answers);
		return answers;
	}
	
	/*
	 * Returns true if the answer is correct
	 */
	public boolean checkAnswer(String answer){
		if(answer.equals(correctAns)){
			return true;
		}
		return false;
	}

	public String getQuestion() {
		return question;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}
}
