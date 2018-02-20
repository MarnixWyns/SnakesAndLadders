package be.kdg.SnakesAndLadders.view.Setup;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.PieceColor;
import be.kdg.SnakesAndLadders.model.Player;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.model.SnakesAndLaddersException;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
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

            try {
                //Get difficulty
                if (view.getEasyDifficulty().isSelected()){
                    model.setSelectedDifficulty(SnakesAndLadders.Difficulty.EASY);
                } else if (view.getNormalDifficulty().isSelected()){
                    model.setSelectedDifficulty(SnakesAndLadders.Difficulty.NORMAL);
                } else if (view.getHardDifficulty().isSelected()){
                    model.setSelectedDifficulty(SnakesAndLadders.Difficulty.HARD);
                } else throw new SnakesAndLaddersException("No selected difficulty");

                //Get players
                ArrayList<Player> players = new ArrayList<>();

                ArrayList<TextField> playerNames = new ArrayList<>();
                playerNames.addAll(Arrays.asList(view.getTfP1name(), view.getTfP2name(), view.getTfP3name(), view.getTfP4name()));

                for (TextField playerName : playerNames) {
                    if (playerName.getText().length() > 10){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("wrong name format");
                    }
                }

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

                //TODO: Add computer player
                if (model.getPlayers().size() == 1 || view.getOnePlayer().isSelected()){
                    model.addPlayer(new Player(PieceColor.YELLOW, "Computer"));
                }

                //Switch between scenes from setup to Game
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
                view.getScene().setRoot(gameView);
                //gameView.getScene().getWindow().sizeToScene();
                gameView.getScene().getWindow().setHeight(600);
                gameView.getScene().getWindow().setWidth(1024);


            } catch (IndexOutOfBoundsException i){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Please give at least 1 player a name");
                alert.setTitle("No player names");
                alert.showAndWait();
            }


        });

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
                view.getSetupMenu().setPadding(new Insets(75,50,0,0));
                view.getBoardBackground().setPadding(new Insets(150,75,25,175));
            } else {
                primaryStage.setFullScreen(false);
                view.getSetupMenu().setPadding(new Insets(0));
                view.getBoardBackground().setPadding(new Insets(30,0,30,90));
            }
        });
        //endregion

        //region ToggleGroup Amount of players
        view.getOnePlayer().setOnAction(event -> {
            amountOfPlayers = 1;

            //todo Ruben fix itemlistener
            view.getColorPickerP1().getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
                @Override
                public void onChanged(Change<? extends Node> c) {
            if(view.getColorPickerP1().getSelectionModel().isSelected(2)){
                view.getIvPlayer1().setImage(view.getIvPlayer1NewColor());
            }
                }
            });

            /*
            if(view.getColorPickerP1().getSelectionModel().isSelected(2)){
                view.getIvPlayer1().setImage(view.getIvPlayer1NewColor());
            }
            */

            disableFields(true, true,true);

            hidePlayers(false, false, false);
        });

        view.getTwoPlayers().setOnAction(event -> {
            amountOfPlayers = 2;

            disableFields(false, true,true);

            hidePlayers(true, false, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
        });

        view.getThreePlayers().setOnAction(event -> {
            amountOfPlayers = 3;

            disableFields(false, false,true);

            hidePlayers(true, true, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);

        });

        view.getFourPlayers().setOnAction(event -> {
            amountOfPlayers = 4;

            disableFields(false, false, false);

            hidePlayers(true, true,true);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);
            view.getColorPickerP4().getSelectionModel().select(3);

        });
        //endregion

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

    private void disableFields(boolean dis1, boolean dis2, boolean dis3){
        view.getTfP2name().setDisable(dis1);
        view.getTfP3name().setDisable(dis2);
        view.getTfP4name().setDisable(dis3);

        view.getColorPickerP2().setDisable(dis1);
        view.getColorPickerP3().setDisable(dis2);
        view.getColorPickerP4().setDisable(dis3);
    }

    private void hidePlayers(boolean dis1, boolean dis2, boolean dis3){
        view.getIvPlayer2().setVisible(dis1);
        view.getIvPlayer3().setVisible(dis2);
        view.getIvPlayer4().setVisible(dis3);
    }
}
