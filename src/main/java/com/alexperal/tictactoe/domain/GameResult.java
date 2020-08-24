package com.alexperal.tictactoe.domain;

import java.util.Objects;
import java.util.Optional;

public class GameResult {
	private final Player winner;
	private final boolean tie;

	public GameResult(Player winner) {
		this(Objects.requireNonNull(winner), false);
	}

	public static GameResult tie() {
		return new GameResult(null, true);
	}

	private GameResult(Player winner, boolean tie) {
		this.winner = winner;
		this.tie = tie;
	}

	public void print() {
		if (tie) {
			System.out.println("This was a Tie");
		} else {
			System.out.println("The winer is player: " + winner.getName());
		}
	}

	public boolean isTie() {
		return tie;
	}

	public Optional<Player> getWinner() {
		return Optional.ofNullable(winner);
	}
}
