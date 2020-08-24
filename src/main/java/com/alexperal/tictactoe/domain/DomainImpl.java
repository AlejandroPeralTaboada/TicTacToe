package com.alexperal.tictactoe.domain;

public class DomainImpl {

	public DomainImpl() {
		var p1 = new Player("player 1");
		var p2 = new Player("player 2");
		var board = Board.empty();
		GameResult gameResult = new Game(board, p1, p2).playGame();
		gameResult.print();
	}
}
