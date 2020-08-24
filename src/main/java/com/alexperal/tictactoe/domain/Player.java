package com.alexperal.tictactoe.domain;

import java.util.Objects;
import java.util.Scanner;

public class Player {
	private final String name;
	private BoardCell token;

	public Player(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		return Objects.equals(name, player.name);
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}

	public Movement getNextMovement(Board board) {
		while (true) {
			try {
				System.out.printf("Player \"%s\" Introduce your next Move %n", name);
				String input = new Scanner(System.in).nextLine();
				String[] coordinates = input.split(" ");
				int i = Integer.parseInt(coordinates[0]);
				int j = Integer.parseInt(coordinates[1]);
				Movement movement = new Movement(i, j);
				if (board.isInvalid(movement)) {
					System.out.printf("Sorry (%d,%d) is not a valid move%n", i, j);
					continue;
				}
				return movement;
			} catch (Exception e) {
				System.out.println("Please introduce two numbers to indicate the coordinates");
			}
		}
	}

	public void setToken(BoardCell token) {
		this.token = token;
	}

	public String getName() {
		return this.name;
	}
}
