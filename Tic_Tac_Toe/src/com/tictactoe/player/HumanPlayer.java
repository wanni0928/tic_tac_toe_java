package com.tictactoe.player;

public class HumanPlayer extends Player implements Playable {
	public HumanPlayer() {
		super();
	}
	
	public HumanPlayer(int score, String name, boolean isComputer) {
		super(score, name, isComputer);
	}

	@Override
	public char[] putStone(String location) {
		System.out.println("human put stone");
		return null;
	}

}
