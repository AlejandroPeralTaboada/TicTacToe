package com.alexperal.tictactoe.mvp;

public class GamePresenter implements GameContract.Presenter {
	private final GameContract.Model model;
	private final GameContract.View view;

	public GamePresenter(GameContract.Model model, GameContract.View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void playGame() {
		view.drawBoard(boardToString());
		while (model.playing()) {
			model.applyMove(getNextMove());
			view.drawBoard(boardToString());
			if (model.won()) {
				view.printWinner(model.getPlayer());
				return;
			} else {
				model.switchPlayer();
			}
		}
		view.printTie();
	}

	private GameContract.Model.Move getNextMove() {
		while (true) {
			try {
				String input = view.getNextMove(model.getPlayer());
				String[] coordinates = input.split(" ");
				int i = Integer.parseInt(coordinates[0]);
				int j = Integer.parseInt(coordinates[1]);
				if (model.isInvalidMove(i, j)) {
					view.notifyError("Sorry (%d,%d) is not a valid move%n".formatted(i, j));
					continue;
				}
				return new GameContract.Model.Move(i, j);
			} catch (Exception e) {
				view.notifyError("Please introduce two numbers to indicate the coordinates");
			}
		}
	}

	private String[][] boardToString() {
		String[][] result = new String[3][3];
		int[][] board = model.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				result[i][j] = translate(board[i][j]);
			}
		}
		return result;
	}

	private String translate(int i) {
		if (model.isX(i)) {
			return "X";
		}
		if (model.isO(i)) {
			return "O";
		}
		return " ";
	}
}
