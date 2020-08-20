package com.tictactoe.board;

public class Board {
	private Position position;
	private String[][] board;
	
	public Board() {
		this.position = position;
		this.board = board;
	}

	public Board(Position position, String[][] board) {
		super();
		this.position = position;
		this.board = board;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}
	
}
