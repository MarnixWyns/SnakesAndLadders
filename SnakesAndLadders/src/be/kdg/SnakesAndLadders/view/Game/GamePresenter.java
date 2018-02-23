package be.kdg.SnakesAndLadders.view.Game;/*
 * Marnix Wyns
 * 7/02/2018
 */

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Setup.SetupPresenter;
import be.kdg.SnakesAndLadders.view.Setup.SetupView;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private ArrayList<String> scoreboard;
    private int score = 0;

    public GamePresenter(GameView view, SnakesAndLadders snakesAndLadders, Stage primarystage) {
        this.view = view;
        this.model = snakesAndLadders;
        this.primaryStage = primarystage;
        scoreboard = new ArrayList<>();

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        //Roll dice on button press
        view.getBtnRollDice().setOnAction(event -> {
            int dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));

            model.getCurrentPlayer().addToPlayerPos(dice);

            updateView();
        });

        view.getBtnHome().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to return to setup screen?");
            alert.setTitle("Return to Home Screen");
            alert.showAndWait();

            SetupView setupView = new SetupView();

            if (alert.getResult() == null) {
                event.consume();
            } else view.getScene().setRoot(setupView);
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


        //fullscreen button intelligence
        view.getTbtnFullscreen().setOnAction(event -> {
            if (view.getTbtnFullscreen().isSelected()) {
                primaryStage.setFullScreen(true);
                view.getGameButtons().setPadding(new Insets(75, 50, 0, 0));
                view.getBoardBackground().setPadding(new Insets(150, 75, 25, 175));
            } else {
                primaryStage.setFullScreen(false);
                view.getGameButtons().setPadding(new Insets(50, 0, 0, 50));
                view.getBoardBackground().setPadding(new Insets(30, 0, 30, 90));
            }
        });

        if (model.getPlayers().size() == 1) {
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);
        } else if (model.getPlayers().size() == 2) {
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);
        } else if (model.getPlayers().size() == 3) {
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(true);
            view.getIvPlayer4().setVisible(false);
        } else {
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(true);
            view.getIvPlayer4().setVisible(true);
        }

        view.getIvPlayer1().setImage(model.getColorPlayer1());
        view.getIvPlayer2().setImage(model.getColorPlayer2());
        view.getIvPlayer3().setImage(model.getColorPlayer3());
        view.getIvPlayer4().setImage(model.getColorPlayer4());

    }

    private void updateView() {

        //TODO: Disable a player if position 100 is reached, store playername in arrayList

        view.getLblplayerName().setText(model.getCurrentPlayerName());


        if (model.getCurrentPlayer().getPlayerPos() == 100) {

            model.getCurrentPlayer().setFinished(true);
            scoreboard.add(model.getCurrentPlayerName());


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();


            scoreboard.add(score,model.getCurrentPlayerName());
            score++;

            model.disablePlayer(model.getCurrentPlayer());
            model.nextPlayer();
        }

        //TODO: Try to compact this
        //TODO: The initial startup does an updateview but the dice hasn't been rolled yet so player1 loses a turn

        if (model.getCurrentPlayerId() == 0 && !model.getCurrentPlayer().isFinished()) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer1());


            view.getBoardGrid().add(view.getIvPlayer1(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 1 && !model.getCurrentPlayer().isFinished()) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer2());

            view.getBoardGrid().add(view.getIvPlayer2(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 2 && !model.getCurrentPlayer().isFinished()) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer3());

            view.getBoardGrid().add(view.getIvPlayer3(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 3 && !model.getCurrentPlayer().isFinished()) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer4());

            view.getBoardGrid().add(view.getIvPlayer4(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        }
        //No one can play anymore, game is finished
        /*
        else {
            StringBuilder score = new StringBuilder();
            int i = 1;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End of the game");
            alert.setHeaderText("Everyone has finished");

            for (String s : scoreboard) {
                score.append(i++).append(". ").append(s).append("\n");
            }
            alert.setContentText(score.toString());
            alert.showAndWait();
        }
        */
    }
}
