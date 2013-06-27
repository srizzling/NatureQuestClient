package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.naturequest.question.Question;

public class Game
{
	private MainMenuActivity mainMenuActivity;
	
	private int score;
	private Question question;
	
	private List<Question> questions = new ArrayList<Question>();
	private List<String> quests = new ArrayList<String>();
	
	private User user;
	private String currentQuest;
	
	private static Game currentGame;
	
	public MainMenuActivity getMainMenuActivity()
	{
		return this.mainMenuActivity;
	}

	public void setMainMenuActivity(MainMenuActivity mainMenuActivity)
	{
		this.mainMenuActivity = mainMenuActivity;
	}

	public void setBackgroundTheme(int theme)
	{
	Log.d("Set theme", "tick");
	this.mainMenuActivity.setBackgroundTheme(theme);
	}
	
	public Game(User user)
	{
		this.user = user;
		
	}
	
	public static Game getGame()
	{
		return currentGame;
	}
	
	public static void setGame(Game game)
	{
		currentGame = game;
	}
	
	public User getCurrentUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public void logoutCurrentUser(){
		this.user=null;
	}
	public int getScore()
	{
		return this.score;
	}
	
	public void setScore(int points)
	{
		this.score = this.score + points;
	}

	public Question getQuestion()
	{
		return this.question;
	}
	
	public void setQuestion(Question question)
	{
		this.question = question;
	}
	
	public List<Question> getQuestions()
	{
		return this.questions;
	}
	
	public void setQuestions(List<Question> questions)
	{
		this.questions = questions;
	}

	public boolean loggedIn() {
		if(user!=null){
			return true;
		}
		return false;
	}
	
	public List<String> getQuests()
	{
		return this.quests;
	}
	
	public void setQuests(List<String> quests)
	{
		this.quests = quests;
	}

	public String getCurrentQuest()
	{
		return this.currentQuest;
	}
	
	public void setCurrentQuest(String currentQuest)
	{
		this.currentQuest = currentQuest;
	}
	
	
}
