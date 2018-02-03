package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.Dice;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import javafx.scene.image.Image;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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

        //Roll dice on button press
        gameView.getBtnRollDice().setOnAction(event -> {
            Random random = new Random();
            Dice dice = new Dice();

            for (int i = 0; i < random.nextInt(9) + 10; i++) {

                dice.throwDice();
                gameView.getIvDice().setImage(new Image(gameView.getDIEURL() + dice.getValue() + ".jpg"));

                //TimeUnit.MILLISECONDS.sleep(30); TODO: Add decreasing delay when throwing dice
            }

            updateView();
        });


    }

    private void updateView() {

    }


}
