package com.alexperal.tictactoe.mvp;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GameView implements GameContract.View {
	private final Scanner scanner = new Scanner(System.in);

	@Override
	public void drawBoard(String[][] board) {
		System.out.println(Arrays.stream(board)
				.map(row -> String.join("|", row)+"\n")
				.collect(Collectors.joining("─┼─┼─\n")));
	}

	@Override
	public String getNextMove(String player) {
		System.out.printf("Player %s Introduce your next Move %n", player);
		return scanner.nextLine();
	}

	@Override
	public void printWinner(String player) {
		System.out.println("The winner is player: " + player);
	}

	@Override
	public void notifyError(String s) {
		System.out.println("There vas an Error: " + s);
	}

	@Override
	public void printTie() {
		System.out.println("Game ended in a Tie");
	}
}
