package com.tictactoe.game;

import com.tictactoe.board.Board;
import com.tictactoe.player.Player;

public interface GameSystem {
	void initialize();
	void reset(Board board);
	boolean isFinished();
}
