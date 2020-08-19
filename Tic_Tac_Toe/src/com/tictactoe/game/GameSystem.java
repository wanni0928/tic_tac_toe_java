package com.tictactoe.game;

import com.tictactoe.board.Board;
import com.tictactoe.player.Playable;

public interface GameSystem {
	void play(Playable player, Board board);
	boolean checkPlayer(String input);
	Playable judge(Playable player1, Playable player2);
	void initialize();
	void reset(Board board);
	void exit();
}
