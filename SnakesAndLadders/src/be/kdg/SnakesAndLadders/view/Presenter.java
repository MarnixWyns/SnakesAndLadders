package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.Dice;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Presenter {
    private SnakesAndLadders model;
    private GameView gameView;
    private SetupView setupView;
    private Stage primaryStage;
    private Scene setupScene;
    private Scene gameScene;
    private int amountOfPlayers;

    public Presenter(SnakesAndLadders model, GameView gameView, SetupView setupView, Stage primaryStage,
                     Scene setupScene, Scene gameScene) {
        this.model = model;
        this.gameView = gameView;
        this.setupView = setupView;
        this.primaryStage = primaryStage;
        this.setupScene = setupScene;
        this.gameScene = gameScene;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        //Roll dice on button press
        gameView.getBtnRollDice().setOnAction(event -> {
            Random random = new Random();
            Dice dice = new Dice();

            for (int i = 0; i < random.nextInt(3) + 3; i++) {

                dice.getValue();
                gameView.getIvDice().setImage(new Image(gameView.getDIEURL() + dice.getValue() + ".png"));

                try {
                    TimeUnit.MILLISECONDS.sleep(10 ); //TODO: Imageview is a bitch, changes only after entire for loop
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            updateView();
        });

        //startButton intelligence
        setupView.getBtnStartGame().setOnAction(event -> {

            //TODO: Control duplicate colors


            primaryStage.setScene(gameScene);
            primaryStage.show();

        });


        //exitbutton intelligence
        gameView.getBtnExit().setOnAction(event -> {
            showExitDialog();
        });


        setupView.getBtnExitGame().setOnAction(event -> {
            showExitDialog();
        });
        //endregion

        //region Fullscreen ToggleButtons
        //fullscreen button intelligence
        gameView.getTbtnFullscreen().setOnAction(event -> {
            if (setupView.getTbtnFullScreen().isSelected()) {
                primaryStage.setFullScreen(true);
            } else {
                primaryStage.setFullScreen(false);
            }
        });

        setupView.getTbtnFullScreen().setOnAction(event -> {
            if (setupView.getTbtnFullScreen().isSelected()) {
                primaryStage.setFullScreen(true);
            } else {
                primaryStage.setFullScreen(false);
            }
        });
        //endregion

        //region ToggleGroup Amount of players
        setupView.getOnePlayer().setOnAction(event -> {
            amountOfPlayers = 1;

            setupView.getTfP2name().setDisable(true);
            setupView.getTfP3name().setDisable(true);
            setupView.getTfP4name().setDisable(true);

            setupView.getColorPickerP2().setDisable(true);
            setupView.getColorPickerP3().setDisable(true);
            setupView.getColorPickerP4().setDisable(true);

            setupView.getIvPlayer2().setVisible(false);
            setupView.getIvPlayer3().setVisible(false);
            setupView.getIvPlayer4().setVisible(false);
        });

        setupView.getTwoPlayers().setOnAction(event -> {
            amountOfPlayers = 2;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(true);
            setupView.getTfP4name().setDisable(true);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(true);
            setupView.getColorPickerP4().setDisable(true);

            setupView.getIvPlayer2().setVisible(true);
            setupView.getIvPlayer3().setVisible(false);
            setupView.getIvPlayer4().setVisible(false);

        });

        setupView.getThreePlayers().setOnAction(event -> {
            amountOfPlayers = 3;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(false);
            setupView.getTfP4name().setDisable(true);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(false);
            setupView.getColorPickerP4().setDisable(true);

            setupView.getIvPlayer2().setVisible(true);
            setupView.getIvPlayer3().setVisible(true);
            setupView.getIvPlayer4().setVisible(false);
        });

        setupView.getFourPlayers().setOnAction(event -> {
            amountOfPlayers = 4;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(false);
            setupView.getTfP4name().setDisable(false);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(false);
            setupView.getColorPickerP4().setDisable(false);

            setupView.getIvPlayer2().setVisible(true);
            setupView.getIvPlayer3().setVisible(true);
            setupView.getIvPlayer4().setVisible(true);
        });
        //endregion

    }

    private void updateView() {

    }

    private void showExitDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setContentText("Are you sure you want to exit? Your progress won't be saved.");
        alert.initModality(Modality.APPLICATION_MODAL);
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("Cancel");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

        Optional<ButtonType> alertOptions = alert.showAndWait();
        if (alertOptions.get() == buttonTypeOne) {
            System.exit(0);
        }
        if (alertOptions.get() == buttonTypeTwo) {
            alert.close();
        }
    }
}
