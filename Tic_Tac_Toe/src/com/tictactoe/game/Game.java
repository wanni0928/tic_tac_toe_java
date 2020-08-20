package com.tictactoe.game;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import com.tictactoe.board.Board;
import com.tictactoe.player.AIPlayer;
import com.tictactoe.player.HumanPlayer;
import com.tictactoe.player.Playable;
import com.tictactoe.player.Player;

public class Game implements GameSystem, GameService {
	private Scanner scanner;
	private Player player;
	private Player[] players;
	private Board board;
	private String blank = "ㆍ";
	private String black = "●";
	private String white = "○";
	private int turn;
	
	public Game(){
		this.scanner = new Scanner(System.in);
		this.board = new Board();
		this.turn = 0;
	}
	
	public Game(Playable player, Board board) {
		System.out.println(1235);
		this.scanner = new Scanner(System.in);
		this.board = new Board();
		this.turn = 0;
	}
	
	@Override
	public void play(Player player, Board board) {
		Player currentPlayer;
		Player winner;
		String[][] gameBoard = board.getBoard();
		String[] location;
		String query;
		String stone;
		int idx;
		int limit = 0;
		int cnt = 0;
		
		players = new Player[2]; 
		
		if(this.turn == 0) {
			System.out.println("승리 제한 횟수를 입력해주세요.(정수로 입력해수제요.)");
			try {
				limit = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("잘못입력 하셨네요. 프로그램을 종료합니다. 다시 하세요.");
				System.exit(0);
			}
			
			for(int i = 0; i < players.length; i++) {
				Player samplePlayer = null;
				System.out.printf("%d번째 플레이어는 ai 입니까?(y/n)\n", i+1);
				query = scanner.nextLine();
				switch (query) {
				case "y":
				case "Y":
					System.out.printf("%d번째 플레이어는 ai 입니다.\n", i+1);
					samplePlayer = new AIPlayer();
					samplePlayer.setComputer(true);
					break;
				case "n":
				case "N":
					System.out.printf("%d번째 플레이어는 사람 입니다.\n", i+1);
					samplePlayer = new Player();
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
		
		while(players[0].getScore() != limit && players[1].getScore() != limit ) {
			currentPlayer = turn % 2 == 0 ? players[0] : players[1];
			stone = currentPlayer == players[0] ? white : black;
			System.out.println(currentPlayer.getName() + "의 차례입니다. 놓고 싶은 돌의 위치를 입력하세요. (x y)");
			try {
				location = currentPlayer.putStone(scanner.nextLine());
				if(gameBoard[Integer.parseInt(location[0])][Integer.parseInt(location[1])] != blank) {
					System.out.println("이미 놓여있습니다.");
					continue;
				}
				gameBoard[Integer.parseInt(location[0])][Integer.parseInt(location[1])] = stone;
				
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			
			for (String[] arr : gameBoard) {
				System.out.println(Arrays.toString(arr));
			}
			winner = judge(players[0], players[1], gameBoard);
			
			if(winner != null) {
				idx = Arrays.asList(players).indexOf(winner);
				players[idx].setScore(players[idx].getScore() + 1);
				System.out.println(players[idx].getScore());
				reset(board);
			}
			turn++;
		}
	}

	@Override
	public Player judge(Player player1, Player player2, String[][] array) {
//		Player winner = null;
		int blackHorizonCnt = 0;
		int whiteHorizoCnt = 0;
		int blackVerticalCnt = 0;
		int whiteVerticalCnt = 0;
		
		for (String[] strings : array) {
			// Horizon
			blackHorizonCnt = Arrays.stream(strings).filter(x -> x.equals(black)).toArray().length;
			whiteHorizoCnt = Arrays.stream(strings).filter(x -> x.equals(white)).toArray().length;
			if(whiteHorizoCnt > 0) whiteVerticalCnt++;
			if(blackHorizonCnt > 0) blackVerticalCnt++;
			if(whiteHorizoCnt == 3) return player1;
			if(blackHorizonCnt == 3) return player2;
		}
		
		if(whiteVerticalCnt == 3) {
			//vertical
			for (int i = 0; i < array.length; i++) {
				if(i == 1) { // diagonal
					if((array[0][i-1] == white 
							&& array[1][i] == white 
							&& array[2][i+1] == white) || 
							(array[0][i+1] == white 
							&& array[1][i] == white 
							&& array[2][i-1] == white)) {
						System.out.println("1번 대각선");
						return player1;
					}
				}
				if(array[0][i] == white && array[1][i] == white && array[2][i] == white) {
					return player1;
				}
			}
		}
		
		if(blackVerticalCnt == 3) {
			//vertical
			for (int i = 0; i < array.length; i++) {
				if(i == 1) { // diagonal
					if((array[0][i-1] == black
							&& array[1][i] == black 
							&& array[2][i+1] == black) || 
							(array[0][i+1] == black 
							&& array[1][i] == black 
							&& array[2][i-1] == black)) {
						System.out.println("2번 대각선");
						return player2;
					}
				}
				if(array[0][i] == black && array[1][i] == black && array[2][i] == black) {
					return player2;
				}
			}
		}
		
//		if(blackVerticalCnt == 3) return player2;
		
		return null;
	}

	@Override
	public boolean isFinished() {
		return true;
	}
	
	@Override
	public void initialize() {
		this.board.setBoard(new String[3][3]);
		reset(this.board);
		play(player, this.board);
	}

	@Override
	public void reset(Board board) {
		for (String[] arr : board.getBoard()) {
            Arrays.fill(arr, "ㆍ");
        }
	}

	@Override
	public boolean checkPlayer(String input) {
		return input.equals("y") ? true : false;
	}

}