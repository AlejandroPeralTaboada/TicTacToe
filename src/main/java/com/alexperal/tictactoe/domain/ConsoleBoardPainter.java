package com.alexperal.tictactoe.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleBoardPainter {
	private static String formatBoardCell(BoardCell cell) {
		return switch (cell) {
			case X -> "X";
			case O -> "O";
			case EMPTY -> " ";
		};
	}

	public void drawBoard(Board board) {
		System.out.println(Arrays.stream(board.board())
				.map(row -> Arrays.stream(row)
						.map(ConsoleBoardPainter::formatBoardCell)
						.collect(Collectors.joining("|")) + "\n")
				.collect(Collectors.joining("─┼─┼─\n")));
	}
}
