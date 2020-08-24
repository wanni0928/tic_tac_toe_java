package com.tictactoe.game;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import com.tictactoe.board.Board;
import com.tictactoe.player.AIPlayer;
import com.tictactoe.player.HumanPlayer;
import com.tictactoe.player.Playable;
import com.tictactoe.player.Player;
import com.tictactoe.utils.Utils;

public class Game implements GameSystem, GameService {
	private Scanner scanner;
	private Player player;
	private Player[] players;
	private Board board;
	private String blank = "ㆍ";
	private String black = "●";
	private String white = "○";
	private Utils utils = new Utils();
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
	
//	public String[][] deepCopy(String[][] arSrc){
//        String[][] arDest = new String[3][3];
//        for (int i = 0; i < arDest.length; i++) {
//            System.arraycopy(arSrc[i], 0, arDest[i], 0, arDest.length);
//        }
//        return arDest;
//    }
	
	@Override
	public void play(Player player, Board board) {
		Player currentPlayer;
		Player winner = null;
		Player vrPlayer;
		String[][] gameBoard = board.getBoard();
		String[][] vrGameBoard = new String[3][3];
		String[][] corners = {{"0", "0"}, {"0", "2"}, {"2", "0"}, {"2", "2"}};
		String[] location = new String[2];
		String[] vrLocation = new String[2];
		String query;
		String stone;
		String vrStone;
		int idx;
		int limit = 0;
		int blankCnt = 0;
		
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
		
		while(players[0].getScore() != limit && players[1].getScore() != limit ) {
			currentPlayer = turn % 2 == 0 ? players[0] : players[1];
			stone = currentPlayer == players[0] ? white : black;
			vrStone = stone == white ? black : white;
			blankCnt = 0;
			System.out.println(currentPlayer.getName() + "의 차례입니다. 놓고 싶은 돌의 위치를 입력하세요. (x y)");
			try {
				if(currentPlayer instanceof HumanPlayer) {
					System.out.println("human");
					location = currentPlayer.putStone(scanner.nextLine());
					if(gameBoard[Integer.parseInt(location[0])][Integer.parseInt(location[1])] != blank) {
						System.out.println("이미 놓여있습니다.");
						continue;
					}
				}
				
				if(currentPlayer instanceof AIPlayer) {
					System.out.println("ai");
					System.out.println(vrStone);
					vrPlayer = currentPlayer == players[0] ? players[1] : players[0];
					vrLoop:
					for (int i = 0; i < gameBoard.length; i++) {
						for (int j = 0; j < gameBoard.length; j++) {
							vrGameBoard = utils.deepCopy(gameBoard);
							vrLocation[0] = i + "";
							vrLocation[1] = j + "";
							if(vrGameBoard[Integer.parseInt(vrLocation[0])][Integer.parseInt(vrLocation[1])] != blank) continue;
							vrGameBoard[Integer.parseInt(vrLocation[0])][Integer.parseInt(vrLocation[1])] = vrStone;
							System.out.println("ai vr test");
							for (String[] arr : vrGameBoard) {
								System.out.println(Arrays.toString(arr));
							}
							System.out.println();
							winner = judge(players[0], players[1], vrGameBoard);
							if(winner == vrPlayer) {
								System.out.println("여기를 막아라"); 
								break vrLoop;
							} 
						}
					}
					
					vrStone = stone;
					vrLoop:
					for (int i = 0; i < gameBoard.length; i++) {
						for (int j = 0; j < gameBoard.length; j++) {
							vrGameBoard = utils.deepCopy(gameBoard);
							vrLocation[0] = i + "";
							vrLocation[1] = j + "";
							if(vrGameBoard[Integer.parseInt(vrLocation[0])][Integer.parseInt(vrLocation[1])] != blank) continue;
							vrGameBoard[Integer.parseInt(vrLocation[0])][Integer.parseInt(vrLocation[1])] = vrStone;
							System.out.println("ai vr test");
							for (String[] arr : vrGameBoard) {
								System.out.println(Arrays.toString(arr));
							}
							System.out.println();
							winner = judge(players[0], players[1], vrGameBoard);
							if(winner == currentPlayer) {
								System.out.println("여기다"); 
								break vrLoop;
							} 
						}
					}
				
					if(winner != currentPlayer) {
						int i = 0;
						for(i = 0; i < corners.length; i++) {
							if(gameBoard[Integer.parseInt(corners[i][0])][Integer.parseInt(corners[i][1])] == blank) {
								location = corners[i];
								break;
							}
						}
					}else {
						location = vrLocation;
						System.out.println(Arrays.toString(location));
					}
				}
				
				gameBoard[Integer.parseInt(location[0])][Integer.parseInt(location[1])] = stone;
				
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
			
			System.out.println("current gameboard");
			for (String[] arr : gameBoard) {
				System.out.println(Arrays.toString(arr));
			}
			
			
			winner = judge(players[0], players[1], gameBoard);
			
			if(winner != null) {
				idx = Arrays.asList(players).indexOf(winner);
				players[idx].setScore(players[idx].getScore() + 1);
				System.out.println(players[idx].getName() + " win");
				System.out.println(players[idx].getScore());
				reset(board);
			} else {
				for (String[] row : gameBoard) {					
					blankCnt += Arrays.stream(row).filter(x -> x.equals(blank)).toArray().length;
				}
				System.out.println(blankCnt);
				if(blankCnt == 0) {
					System.out.println("Draw! game reset!");
					reset(board);
				}
			}
			turn++;
		}
	}

	@Override
	public Player judge(Player player1, Player player2, String[][] array) {
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
		
		if(checkAfterHorrizonCnt(whiteVerticalCnt, array, white)) return player1;
		if(checkAfterHorrizonCnt(blackVerticalCnt, array, black)) return player2;
		
		return null;
	}
	
	public void judge() {
		
	}
	
	@Override
	public boolean checkAfterHorrizonCnt(int cnt, String[][] array, String stone) {
		if(cnt == 3) {
			for (int i = 0; i < array.length; i++) {
				if(i == 1) { 
					// diagonal
					if((array[0][i-1] == stone 
							&& array[1][i] == stone 
							&& array[2][i+1] == stone) 
							|| 
							(array[0][i+1] == stone 
							&& array[1][i] == stone 
							&& array[2][i-1] == stone)) {
						return true;
					}
				}
				//vertical
				if(array[0][i] == stone && array[1][i] == stone && array[2][i] == stone) {
					return true;
				}
			}
		}
		return false;
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