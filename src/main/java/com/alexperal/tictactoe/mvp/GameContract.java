package com.alexperal.tictactoe.mvp;

public interface GameContract {

	interface View {

		void drawBoard(String[][] board);

		String getNextMove(String player);

		void printWinner(String player);

		void notifyError(String s);

		void printTie();
	}

	interface Presenter {

		void playGame();
	}

	interface Model {

		int[][] getBoard();

		boolean isO(int i);

		boolean isX(int i);

		void switchPlayer();

		boolean playing();

		void applyMove(Move move);

		boolean won();

		String getPlayer();

		boolean isInvalidMove(int i, int j);

		record Move(int i, int j) {
		}
	}
}
