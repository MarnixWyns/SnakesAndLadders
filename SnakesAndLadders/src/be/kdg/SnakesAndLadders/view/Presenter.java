package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;

public class Presenter {
    private SnakesAndLadders model;
    private GameView gameView;
    private SetupView setupView;

    public Presenter(SnakesAndLadders model, GameView gameView, SetupView setupView) {
        this.model = model;
        this.gameView = gameView;
        this.setupView = setupView;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {

    }
}
