package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.Dice;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

import java.util.Optional;
import java.util.Random;

public class Presenter {
    private SnakesAndLadders model;
    private GameView gameView;
    private SetupView setupView;
    private int amountOfPlayers;

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
        setupView.getBtnStartGame().setOnAction(event -> {
            updateView();
        });

        //startButton intelligence
        setupView.getBtnStartGame().setOnAction(event -> {

            //region Control if all users have color value selected
            if (amountOfPlayers == 1) {
                if (setupView.getColorPickerP1().getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Bad color selection");
                    alert.setContentText("One or more users hasn't chosen a color, please make sure every player has selected a color.");
                    alert.showAndWait();
                }
            }
            if (amountOfPlayers == 2) {
                if (setupView.getColorPickerP1().getValue() == null || setupView.getColorPickerP2().getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Bad color selection");
                    alert.setContentText("One or more users hasn't chosen a color, please make sure every player has selected a color.");
                    alert.showAndWait();
                }
            }
            if (amountOfPlayers == 3) {
                if (setupView.getColorPickerP1().getValue() == null || setupView.getColorPickerP2().getValue() == null || setupView.getColorPickerP3().getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Bad color selection");
                    alert.setContentText("One or more users hasn't chosen a color, please make sure every player has selected a color.");
                    alert.showAndWait();
                }
            }
            if (amountOfPlayers == 4) {
                if (setupView.getColorPickerP1().getValue() == null ||
                        setupView.getColorPickerP2().getValue() == null ||
                        setupView.getColorPickerP3().getValue() == null ||
                        setupView.getColorPickerP4().getValue() == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Bad color selection");
                    alert.setContentText("One or more users hasn't chosen a color, please make sure every player has selected a color.");
                    alert.showAndWait();
                }
            }
            //endregion
            //TODO: Clean up mess (Function?)


        });


        //region Exit Buttons
        //exitbutton intelligence
        gameView.getBtnExit().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit game");
            alert.setContentText("Are you sure you want to exit? Your progress won't be saved.");

            Optional<ButtonType> options = alert.showAndWait();
            if (options.get() == ButtonType.YES) {
                Platform.exit(); //TODO: Does not close, has to be in Main class I think
                System.exit(0);
            }
            if (options.get() == ButtonType.CANCEL) {
                //Does nothing other than close alert
            }
            updateView();
        });

        setupView.getBtnExitGame().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit game");
            alert.setContentText("Are you sure you want to exit?");

            Optional<ButtonType> options = alert.showAndWait();
            if (options.get() == ButtonType.YES) {
                Platform.exit(); //TODO: Does not close, has to be in Main class I think
                System.exit(0);
            }
            if (options.get() == ButtonType.CANCEL) {
                //Does nothing other than close alert
            }

        });
        //endregion

        //region Fullscreen ToggleButtons
        //fullscreen button intelligence
        gameView.getTbtnFullscreen().setOnAction(event -> {
            updateView();
        });

        setupView.getTbtnFullScreen().setOnAction(event -> {
                    updateView();
                }
        );
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
        });

        setupView.getTwoPlayers().setOnAction(event -> {
            amountOfPlayers = 2;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(true);
            setupView.getTfP4name().setDisable(true);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(true);
            setupView.getColorPickerP4().setDisable(true);
        });

        setupView.getThreePlayers().setOnAction(event -> {
            amountOfPlayers = 3;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(false);
            setupView.getTfP4name().setDisable(true);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(false);
            setupView.getColorPickerP4().setDisable(true);
        });

        setupView.getFourPlayers().setOnAction(event -> {
            amountOfPlayers = 4;

            setupView.getTfP2name().setDisable(false);
            setupView.getTfP3name().setDisable(false);
            setupView.getTfP4name().setDisable(false);

            setupView.getColorPickerP2().setDisable(false);
            setupView.getColorPickerP3().setDisable(false);
            setupView.getColorPickerP4().setDisable(false);
        });
        //endregion

    }

    private void updateView() {

    }
}
