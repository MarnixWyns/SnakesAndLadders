package be.kdg.SnakesAndLadders.view.Setup;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.Player;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class SetupPresenter {
    private SnakesAndLadders model;

    private SetupView view;
    private Stage primaryStage;
    private Scene setupScene;
    private int amountOfPlayers;

    public SetupPresenter(SnakesAndLadders model, GameView gameView, SetupView setupView, Stage primaryStage,
                          Scene setupScene, Scene gameScene) {
        this.model = model;
        this.view = setupView;
        this.primaryStage = primaryStage;
        this.setupScene = setupScene;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        //startButton intelligence
        view.getBtnStartGame().setOnAction(event -> {


            //Shorter version of above code
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player(view.getColorPickerP1().getValue(), view.getTfP1name().getText()));
            players.add(new Player(view.getColorPickerP2().getValue(), view.getTfP2name().getText()));
            players.add(new Player(view.getColorPickerP3().getValue(), view.getTfP3name().getText()));
            players.add(new Player(view.getColorPickerP4().getValue(), view.getTfP4name().getText()));



            //Should filter out all players that aren't valid
            for (Player player : players) {
                if (!player.getUsername().equals("")){
                    model.addPlayer(player);
                }
            }


            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
            view.getScene().setRoot(gameView);
            //gameView.getScene().getWindow().sizeToScene();
            gameView.getScene().getWindow().setHeight(600);
            gameView.getScene().getWindow().setWidth(1024);


        });

        /* //TODO: Something strange is going on
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

        view.getBtnExitGame().setOnAction(event -> {
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
        //endregion

        //region Fullscreen ToggleButtons


        view.getTbtnFullScreen().setOnAction(event -> {
            if (view.getTbtnFullScreen().isSelected()) {
                primaryStage.setFullScreen(true);
            } else {
                primaryStage.setFullScreen(false);
            }
        });
        //endregion

        //region ToggleGroup Amount of players
        view.getOnePlayer().setOnAction(event -> {
            amountOfPlayers = 1;

            view.getTfP2name().setDisable(true);
            view.getTfP3name().setDisable(true);
            view.getTfP4name().setDisable(true);

            view.getColorPickerP2().setDisable(true);
            view.getColorPickerP3().setDisable(true);
            view.getColorPickerP4().setDisable(true);

            view.getIvPlayer2().setVisible(false);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);

        });

        view.getTwoPlayers().setOnAction(event -> {
            amountOfPlayers = 2;

            view.getTfP2name().setDisable(false);
            view.getTfP3name().setDisable(true);
            view.getTfP4name().setDisable(true);

            view.getColorPickerP2().setDisable(false);
            view.getColorPickerP3().setDisable(true);
            view.getColorPickerP4().setDisable(true);

            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);

        });

        view.getThreePlayers().setOnAction(event -> {
            amountOfPlayers = 3;

            view.getTfP2name().setDisable(false);
            view.getTfP3name().setDisable(false);
            view.getTfP4name().setDisable(true);

            view.getColorPickerP2().setDisable(false);
            view.getColorPickerP3().setDisable(false);
            view.getColorPickerP4().setDisable(true);

            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(true);
            view.getIvPlayer4().setVisible(false);
        });

        view.getFourPlayers().setOnAction(event -> {
            amountOfPlayers = 4;

            view.getTfP2name().setDisable(false);
            view.getTfP3name().setDisable(false);
            view.getTfP4name().setDisable(false);

            view.getColorPickerP2().setDisable(false);
            view.getColorPickerP3().setDisable(false);
            view.getColorPickerP4().setDisable(false);

            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(true);
            view.getIvPlayer4().setVisible(true);
        });

        //connect comboboxes to pawncolors and change accordingly

    }

    private void updateView() {

    }

    private void showExitDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Hierdoor stopt het spel!");
        alert.setContentText("Ben je zeker?");
        alert.setTitle("Opgelet!");
        alert.getButtonTypes().clear();
        ButtonType neen = new ButtonType("Neen");
        ButtonType ja = new ButtonType("Ja");
        alert.getButtonTypes().addAll(neen, ja);
        alert.showAndWait();
        /*
        if (alert.getResult() == null || alert.getResult().equals(neen)) {
            event.consume();
        }*/
    }
}
