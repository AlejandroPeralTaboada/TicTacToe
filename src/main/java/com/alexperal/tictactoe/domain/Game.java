package com.alexperal.tictactoe.domain;

import java.util.Objects;

public class Game {

	private Board board;
	private final Player player1;
	private final Player player2;
	private Player currentPlayer;
	private BoardCell currentToken;


	public Game(Board board, Player player1, Player player2) {
		Objects.requireNonNull(board, "A board is required");
		Objects.requireNonNull(board, "Player 1 is required");
		Objects.requireNonNull(board, "Player 2 is required");
		if (player1.equals(player2)) {
			throw new IllegalArgumentException("Player 1 and 2 must be different players");
		}
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		currentPlayer = player1;
		currentToken = BoardCell.X;
		player1.setToken(BoardCell.X);
		player1.setToken(BoardCell.O);
	}

	public GameResult playGame() {
		var painter = new ConsoleBoardPainter();
		painter.drawBoard(board);
		if (board.won()) {
			return new GameResult(currentPlayer);
		}
		while (board.playing()) {
			Movement movement = currentPlayer.getNextMovement(board);
			board = board.applyMovement(movement, currentToken);
			painter.drawBoard(board);
			if (board.won()) {
				return new GameResult(currentPlayer);
			} else {
				switchPlayer();
			}
		}
		return GameResult.tie();

	}

	private void switchPlayer() {
		currentPlayer = this.currentPlayer.equals(player1) ? player2 : player1;
		currentToken = currentToken == BoardCell.X ? BoardCell.O : BoardCell.X;
	}
}
