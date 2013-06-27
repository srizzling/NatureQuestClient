package com.naturequest;

public class LeaderboardScore
{
	private String name;
	private int score;
	private int questsTaken;
	private String currentQuest;

	public LeaderboardScore(String name, int score, int questsTaken, String currentQuest)
	{
		this.name = name;
		this.score = score;
		this.questsTaken = questsTaken;
		this.currentQuest = currentQuest;
	}

	public String getName()
	{
		return this.name;
	}

	public String getInfo()
	{
		return  "Name - " + this.name + "\n" +
				"Score - " + String.valueOf(this.score) + "\n" +
				"Quests Take - " + String.valueOf(this.questsTaken) + "\n" +
				"Current Quest - " + this.currentQuest;
	}

	public int getScore()
	{
		return this.score;
	}
}