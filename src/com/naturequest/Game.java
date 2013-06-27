package com.naturequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.naturequest.question.Question;
import com.naturequest.radar.GPSTracker;

public class Game
{
	private int score;
	private Question question;

	private List<Question> questions = new ArrayList<Question>();
	private List<JSONObject> quests = new ArrayList<JSONObject>();

	private User user;
	private JSONObject currentQuest;
	private ArrayList<User> leaders;

	private static Game currentGame;

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

	public void setQuestions(List<JSONObject> questions2)
	{
		for(JSONObject q : questions2){
			Question questionObj = new Question(q);
			this.questions.add(questionObj);
		}
		
		
	}

	public boolean loggedIn() {
		if(user!=null){
			return true;
		}
		return false;
	}

	public List<String> getQuests()
	{
		List<String> temp = new ArrayList<String>();
		for(JSONObject questObj:this.quests){
			String questName = null;
			try {
				questName = questObj.getString("name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			temp.add(questName);
		}


		return temp;
	}

	public void setQuests(List<JSONObject> quests)
	{
		this.quests = quests;
	}

	public String getCurrentQuest()
	{
		try {
			return this.currentQuest.getString("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setCurrentQuest(String currentQuest)
	{
		
		
		for(JSONObject questObj:quests){
			try {
				if(currentQuest.equals(questObj.getString("name"))){
					this.currentQuest=questObj;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public int getCurrentQuestID(){
		try {
			return currentQuest.getInt("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public List<Question> getSortedQuestions(Context context){
		List<Question> temp = new ArrayList<Question>(this.questions);
		DistanceComparator compare = new DistanceComparator();
		compare.setContext(context);
		Collections.sort(this.questions,compare);
		return temp;
	}
	
	public void setLeaderboard(ArrayList<User> leaders){
		this.leaders=leaders;
		
	}
	
	public List<User> getLeaderboard(){
		return leaders;
	}
	


	
}
