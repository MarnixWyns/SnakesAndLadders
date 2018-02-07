package be.kdg.SnakesAndLadders.view.Game;/*
 * Marnix Wyns
 * 7/02/2018
 */

import be.kdg.SnakesAndLadders.model.Dice;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;

    public GamePresenter(GameView view, SnakesAndLadders snakesAndLadders) {
        this.view = view;
        this.model = snakesAndLadders;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        //Roll dice on button press
        view.getBtnRollDice().setOnAction(event -> {

            view.getIvDice().setImage(new Image(view.getDIEURL() + model.throwDice() + ".png"));

            updateView();
        });


        //exitbutton intelligence
        view.getBtnExit().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to exit? Your progress will not be saved");
            alert.setContentText("Are you sure?");
            alert.setTitle("Warning!");
            alert.getButtonTypes().clear();
            ButtonType no = new ButtonType("No");
            ButtonType yes = new ButtonType("Yes");
            alert.getButtonTypes().addAll(no, yes);
            alert.showAndWait();

            if (alert.getResult() == null || alert.getResult().equals(no)) {
                event.consume();
            } else System.exit(0);
        });

        //TODO: Switching scene also throws a closeRequest, pointing to a nullpointer
        /*
        view.getScene().getWindow().setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Hierdoor stopt het spel!");
            alert.setContentText("Ben je zeker?");
            alert.setTitle("Opgelet!");
            alert.getButtonTypes().clear();
            ButtonType neen = new ButtonType("Neen");
            ButtonType ja = new ButtonType("Ja");
            alert.getButtonTypes().addAll(neen, ja);
            alert.showAndWait();

            if (alert.getResult() == null || alert.getResult().equals(neen)) {
                event.consume();
            } else System.exit(0);
        });
        */



        /* //TODO: RE Enable
        //fullscreen button intelligence
        view.getTbtnFullscreen().setOnAction(event -> {
            if (view.getTbtnFullscreen().isSelected()) {
                primaryStage.setFullScreen(true);
            } else {
                primaryStage.setFullScreen(false);
            }
        });
        */
    }

    private void updateView() {
        view.getLblplayerName().setText(model.getCurrentPlayer());
    }
}
