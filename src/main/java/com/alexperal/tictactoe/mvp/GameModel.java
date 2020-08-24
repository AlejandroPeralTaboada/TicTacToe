package com.alexperal.tictactoe.mvp;

public class GameModel implements GameContract.Model {

	private final static int EMPTY = 0;
	private final static int X = 1;
	private final static int O = 2;
	private final int[][] board = new int[3][3];
	private String player = "1";

	@Override
	public int[][] getBoard() {
		return board;
	}

	@Override
	public boolean isO(int i) {
		return i == O;
	}

	@Override
	public boolean isX(int i) {
		return i == X;
	}

	@Override
	public void switchPlayer() {
		player = player.equals("1") ? "2" : "1";
	}

	@Override
	public void applyMove(Move move) {
		if (isInvalidMove(move.i(), move.j())) {
			throw new IllegalArgumentException("This movement is not valid: " + move);
		}
		board[move.i()][move.j()] = getPlayerToken();
	}

	private int getPlayerToken() {
		return player.equals("1") ? X : O;
	}

	@Override
	public boolean playing() {
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

	@Override
	public boolean won() {
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

	@Override
	public String getPlayer() {
		return player;
	}

	@Override
	public boolean isInvalidMove(int i, int j) {
		return i < 0 || i > 2 || j < 0 || j > 2 || board[i][j] != EMPTY;
	}
}
