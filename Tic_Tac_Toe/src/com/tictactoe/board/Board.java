package com.tictactoe.board;

public class Board {
	private Position position;
	private char[][] board;
	
	public Board() {
		this.board = board;
	}

	public Board(Position position, char[][] board) {
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

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
}
