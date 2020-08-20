package com.tictactoe.player;

public class HumanPlayer extends Player implements Playable {
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(int score, String name, boolean isComputer) {
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
		return super.putStone(location);
	}

}
