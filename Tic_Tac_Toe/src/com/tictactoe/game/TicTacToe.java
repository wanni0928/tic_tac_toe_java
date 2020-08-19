package com.tictactoe.game;

import java.util.Arrays;
import java.util.Scanner;

import com.tictactoe.board.Board;
import com.tictactoe.player.AIPlayer;
import com.tictactoe.player.HumanPlayer;
import com.tictactoe.player.Playable;
import com.tictactoe.player.Player;

public class TicTacToe implements GameSystem {
	private Scanner scanner;
	private Player player;
	private Playable[] players;
	private Board board;
	private int turn;
	
	public TicTacToe(){
		this.scanner = new Scanner(System.in);
		this.board = new Board();
		this.turn = 0;
	}
	
	public TicTacToe(Playable player, Board board) {
		System.out.println(1235);
		this.scanner = new Scanner(System.in);
		this.board = new Board();
		this.turn = 0;
	}

	@Override
	public void play(Playable player, Board board) {
		Playable currentPlayer = null;
		Playable winner = null;
		String location = "";
		String query = "";
		Playable[] players = new Playable[2]; 
		
		if(this.turn == 0) {
			for(int i = 0; i < players.length; i++) {
				Player samplePlayer = null;
				System.out.printf("%d번째 플레이어는 ai 입니까?(y/n)", i+1);
				query = scanner.nextLine();
				switch (query) {
				case "y":
				case "Y":
					System.out.printf("%d번째 플레이어는 ai 입니다.", i+1);
					samplePlayer = new AIPlayer();
					samplePlayer.setComputer(true);
					break;
				case "n":
				case "N":
					System.out.printf("%d번째 플레이어는 사람 입니다.", i+1);
					samplePlayer = new HumanPlayer();
					samplePlayer.setComputer(false);
					break;
				default:
					System.out.println("오타를 치는 걸 보니, 당신은 사람이군요.");
					samplePlayer = new HumanPlayer();
					samplePlayer.setComputer(false);
					break;
				}
				samplePlayer.setName(String.format("%d번 플레이어", i+1));
				samplePlayer.setScore(0);
				players[i] = samplePlayer;
			}
		}
		players[0].putStone("");
		players[1].putStone("");
		
		currentPlayer = turn % 2 == 0 ? players[0] : players[1];
		location = scanner.nextLine();
		currentPlayer.putStone(location);
		winner = judge(players[0], players[1]);
		if(winner == null) {
			reset(board);
		}
	}

	@Override
	public Playable judge(Playable player1, Playable player2) {
		return null;
	}

	@Override
	public void exit() {
		System.exit(0);
	}
	
	@Override
	public void initialize() {
		this.board.setBoard(new char[3][3]);
		reset(this.board);
		play(player, this.board);
	}

	@Override
	public void reset(Board board) {
		for (char[] chars : board.getBoard()) {
            Arrays.fill(chars, 'ㆍ');
        }
	}

	@Override
	public boolean checkPlayer(String input) {
		return input.equals("y") ? true : false;
	}

}