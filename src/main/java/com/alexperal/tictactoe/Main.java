package com.alexperal.tictactoe;

import com.alexperal.tictactoe.domain.DomainImpl;
import com.alexperal.tictactoe.mvp.MvpImpl;

public class Main {

	public static void main(String[] args) {
		System.out.println(
				"This program shows different implementations of tic tac toe in increasingly complexity");
		new DomainImpl();
	}
}