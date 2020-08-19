package com.tictactoe.player;

public class AIPlayer extends Player implements Playable {
	public AIPlayer() {
		super();
	}
	
	

	public AIPlayer(int score, String name, boolean isComputer) {
		super(score, name, isComputer);
	}



	@Override
	public char[] putStone(String location) {
		// TODO Auto-generated method stub
		System.out.println("ai put stone");
		return null;
	}

}
