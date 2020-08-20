package com.tictactoe.player;

public class Player implements Playable {
	private int score;
	private String name;
	private boolean isComputer;
	
	public Player() {
		
	}

	public Player(int score, String name, boolean isComputer) {
		super();
		this.score = score;
		this.name = name;
		this.isComputer = isComputer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isComputer() {
		return isComputer;
	}

	public void setComputer(boolean isComputer) {
		this.isComputer = isComputer;
	}

	@Override
	public String[] putStone(String location) {
		return location.split("[ \\t\\n\\x0B\\f\\r]");
	}
}
