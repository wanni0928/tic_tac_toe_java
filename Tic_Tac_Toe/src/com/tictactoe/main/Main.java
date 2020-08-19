package com.tictactoe.main;

import com.tictactoe.board.Board;
import com.tictactoe.game.TicTacToe;
import com.tictactoe.player.AIPlayer;
import com.tictactoe.player.Player;

public class Main {
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe();
		game.initialize();
	}
}
