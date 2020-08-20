package com.tictactoe.player;

public class AIPlayer extends Player implements Playable {
	public AIPlayer() {
		super();
	}
	
	

	public AIPlayer(int score, String name, boolean isComputer) {
		super(score, name, isComputer);
	}

	@Override
	public int getScore() {
		return super.getScore();
	}

	@Override
	public void setScore(int score) {
		super.setScore(score);
	}

	@Override
	public String[] putStone(String location) {
		return location.split("[ \\t\\n\\x0B\\f\\r]");
	}

}
