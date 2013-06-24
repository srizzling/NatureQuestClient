package com.naturequest;

import java.util.ArrayList;
import java.util.List;

import com.naturequest.question.Question;

public class Game
{
	private int score;
	private Question question;
	
	private List<Question> questions = new ArrayList<Question>();
	
	private User user;
	
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
}
