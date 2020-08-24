package com.alexperal.tictactoe.basic;

import java.util.Arrays;
import java.util.Scanner;

public class BasicImpl {
	record Move(int i, int j) {
	}

	final static int EMPTY = 0;
	final static int X = 1;
	final static int O = 2;
	final int[][] board = new int[3][3];
	int currentPlayer = 1;

	public BasicImpl() {
		for (int[] row : board) {
			Arrays.fill(row, 0);
		}
		printBoard();
		while (playing()) {
			Move nextMove = getNextMove();
			applyMove(nextMove);
			printBoard();
			if (won()) {
				System.out.printf("Player %d won", currentPlayer);
				return;
			} else {
				changePlayer();
			}
		}
		System.out.println("Tie");
	}

	private void printBoard() {
		for (int[] row : board) {
			for (int cell : row) {
				System.out.print(cell==EMPTY?" ":(cell==X)?"X ":"O ");
			}
			System.out.println();
		}
	}

	private boolean playing() {
		boolean tie = true;
		for (int[] row : board) {
			for (int cell : row) {
				if (cell == EMPTY) {
					tie = false;
					break;
				}
			}
		}
		return !tie && !won();
	}

	private boolean won() {
		boolean diagonal1 = board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2];
		boolean diagonal2 = board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0];
		boolean rows = false;
		for (int[] row : board) {
			rows |= row[0] != EMPTY && row[0] == row[1] && row[0] == row[2];
		}
		boolean columns = false;
		for (int i = 0; i < board.length; i++) {
			columns |= board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i];
		}
		return diagonal1 || diagonal2 || rows || columns;
	}

	private void applyMove(Move move) {
		int token;
		if (currentPlayer == 1) {
			token = X;
		} else {
			token = O;
		}
		board[move.i][move.j] = token;
	}

	private void changePlayer() {
		if (currentPlayer == 1) {
			currentPlayer = 2;
		} else {
			currentPlayer = 1;
		}
	}

	private Move getNextMove() {
		while (true) {
			try {
				System.out.printf("Player %d Introduce your next Move %n",currentPlayer);
				Scanner in = new Scanner(System.in);
				String input = in.nextLine();
				String[] coordinates = input.split(" ");
				int i = Integer.parseInt(coordinates[0]);
				int j = Integer.parseInt(coordinates[1]);
				if (this.board[i][j] != EMPTY) {
					System.out.printf("Sorry (%d,%d cell is already occupied%n", i, j);
					continue;
				}
				return new Move(i, j);
			} catch (Exception e) {
				System.out.println("Please introduce two numbers to indicate the coordinates");
			}
		}
	}


}
