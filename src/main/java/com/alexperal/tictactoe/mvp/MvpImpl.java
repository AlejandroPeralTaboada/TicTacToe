package com.alexperal.tictactoe.mvp;

import static com.alexperal.tictactoe.mvp.GameContract.*;

public class MvpImpl {


	public MvpImpl() {
		Model model = new GameModel();
		View view = new GameView();
		Presenter presenter = new GamePresenter(model,view);
		presenter.playGame();
	}
}
