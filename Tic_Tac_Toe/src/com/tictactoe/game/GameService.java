package com.tictactoe.game;

import com.tictactoe.board.Board;
import com.tictactoe.player.Player;

public interface GameService {
	void play(Player player, Board board);
	boolean checkPlayer(String input);
	Player judge(Player player1, Player player2, String[][] array);
	boolean checkAfterHorrizonCnt(int cnt, String[][] array, String stone);
}
