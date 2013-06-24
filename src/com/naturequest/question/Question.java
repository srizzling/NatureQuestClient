package com.naturequest.question;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;

public class Question
{
	private String question;
	
	private List<String> answers = new ArrayList<String>();
	
	private int correctAnswerNumber;

	private GeoPoint location;

	private boolean hasBeenAnswered;
	
	public Question(GeoPoint location)
	{
		this.location = location;
	}
	
	public GeoPoint getLocation()
	{
		return this.location;
	}
	
	public void setLocation(GeoPoint location)
	{
		this.location = location;
	}
	
	public void setQuestionDetails(String question, List<String> answers, int correctAnswerNumber)
	{
		this.question = question;
		this.answers = answers;
		this.correctAnswerNumber = correctAnswerNumber;
	}
	
	public String getQuestion()
	{
		return this.question;
	}
	
	public List<String> getAnswers()
	{
		return this.answers;
	}
	
	public boolean checkAnswer(int answerNumber)
	{
		return answerNumber == this.correctAnswerNumber;
	}
	
	public boolean getHasBeenAnswered()
	{
		return this.hasBeenAnswered;
	}
	
	public void setHasBeenAnswered()
	{
		this.hasBeenAnswered = true;
	}
}
