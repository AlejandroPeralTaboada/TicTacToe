package com.alexperal.tictactoe.domain;

import java.util.Arrays;
import java.util.Objects;

import static com.alexperal.tictactoe.domain.BoardCell.EMPTY;

public class Board {


	private final BoardCell[][] board = new BoardCell[3][3];

	public static Board empty() {
		Board board = new Board();
		for (BoardCell[] row : board.board) {
			Arrays.fill(row, EMPTY);
		}
		return board;
	}

	private Board() {

	}

	public boolean playing() {
		boolean tie = true;
		for (BoardCell[] row : board) {
			for (BoardCell cell : row) {
				if (cell == EMPTY) {
					tie = false;
					break;
				}
			}
		}
		return !tie && !won();
	}

	public boolean won() {
		boolean diagonal1 = board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2];
		boolean diagonal2 = board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0];
		boolean rows = false;
		for (BoardCell[] row : board) {
			rows |= row[0] != EMPTY && row[0] == row[1] && row[0] == row[2];
		}
		boolean columns = false;
		for (int i = 0; i < board.length; i++) {
			columns |= board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i];
		}
		return diagonal1 || diagonal2 || rows || columns;
	}

	public Board applyMovement(Movement movement, BoardCell token) {
		if (isInvalid(movement)) {
			throw new IllegalArgumentException("Movement is not valid:" + movement);
		}
		if (token == EMPTY) {
			throw new IllegalArgumentException("Expected a non Empty Token");
		}
		Board newBoard = new Board();
		for (int i = 0; i < this.board.length; i++) {
			System.arraycopy(this.board[i], 0, newBoard.board[i], 0, this.board.length);
		}
		newBoard.board[movement.i()][movement.j()] = token;
		return newBoard;
	}

	public boolean isInvalid(Movement movement) {
		Objects.requireNonNull(movement);
		int i = movement.i();
		int j = movement.j();
		return i < 0 || i > 2 || j < 0 || j > 2 || board[i][j] != EMPTY;
	}

	public BoardCell[][] board(){
		BoardCell[][] cells = new BoardCell[3][3];
		for (int i = 0; i < this.board.length; i++) {
			System.arraycopy(board[i], 0, cells[i], 0, this.board.length);
		}
		return cells;
	}

}
