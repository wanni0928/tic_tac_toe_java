package com.tictactoe.game;

import com.tictactoe.board.Board;

public interface GameSystem {
	void initialize();
	void reset(Board board);
	boolean isFinished();
}
