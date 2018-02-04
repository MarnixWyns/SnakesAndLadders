package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.Main;
import be.kdg.SnakesAndLadders.model.Dice;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
                gameView.getIvDice().setImage(new Image(gameView.getDIEURL() + dice.getValue() + ".png"));

                //TimeUnit.MILLISECONDS.sleep(30); TODO: Add decreasing delay when throwing dice
            }
            updateView();
        });

        //Switch scenes on start button press
        setupView.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateView();
            }
        });

        //startButton intelligence
        setupView.getBtnStartGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });


        //exitbutton intelligence
        gameView.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateView();
            }
        });

        setupView.getBtnExitGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateView();
            }
        });


        //fullscreen button intelligence
        gameView.getTbtnFullscreen().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateView();
            }
        });

        setupView.getTbtnFullScreen().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateView();
            }
        });

    }

    private void updateView() {

    }
}
