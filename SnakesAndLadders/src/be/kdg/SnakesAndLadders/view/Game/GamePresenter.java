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
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private ArrayList<String> scoreboard;
    private int score = 0;
    private int pos1 = 1;
    private int pos2 = 1;
    private int pos3 = 1;
    private int pos4 = 1;



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

            if (model.getCurrentPlayerId() == 0 && !model.getCurrentPlayer().isPlayer1Finished()) {

                view.getBoardGrid().getChildren().remove(view.getIvPlayer1());
                /*
                if (pos1 == pos2) {
                    view.getPawnPane2().add(view.getIvPlayer1(), 0, 0);
                } else if (pos1 == pos3) {
                    view.getPawnPane3().add(view.getIvPlayer1(), 0, 0);
                } else if (pos1 == pos4) {
                    view.getPawnPane4().add(view.getIvPlayer1(), 0, 0);
                } else {
                    view.getBoardGrid().getChildren().remove(view.getPawnPane1());
                    view.getBoardGrid().add(view.getPawnPane1(),
                            model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                            model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                    view.getPawnPane1().add(view.getIvPlayer1(), 1, 1);
                }
                pos1 = model.getPlayerPos(model.getCurrentPlayer());


            view.getBoardGrid().add(view.getNewPawnPane(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
            view.getPawnPane().add(view.getIvPlayer1(),0,0);
            */
            view.getBoardGrid().add(view.getIvPlayer1(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));


                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());


                model.nextPlayer();
            } else if (model.getCurrentPlayerId() == 1 && !model.getCurrentPlayer().isPlayer2Finished()) {

                view.getBoardGrid().getChildren().remove(view.getIvPlayer2());
                /*
                if (pos2 == pos1) {
                    view.getPawnPane1().add(view.getIvPlayer2(), 1, 0);
                } else if (pos2 == pos3) {
                    view.getPawnPane3().add(view.getIvPlayer2(), 1, 0);
                } else if (pos2 == pos4) {
                    view.getPawnPane4().add(view.getIvPlayer2(), 1, 0);
                } else {
                    view.getBoardGrid().getChildren().remove(view.getPawnPane2());
                    view.getBoardGrid().add(view.getPawnPane2(),
                            model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                            model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                    view.getPawnPane2().add(view.getIvPlayer2(), 1, 0);
                }

                pos2 = model.getPlayerPos(model.getCurrentPlayer());
                */
            /*
            view.getBoardGrid().getChildren().remove(view.getNewPawnPane());
            view.getBoardGrid().add(view.getNewPawnPane(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
            view.getNewPawnPane().add(view.getIvPlayer2(),1,0);
            */
            view.getBoardGrid().add(view.getIvPlayer2(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());

                model.nextPlayer();
            } else if (model.getCurrentPlayerId() == 2 && !model.getCurrentPlayer().isPlayer3Finished()) {

                view.getBoardGrid().getChildren().remove(view.getIvPlayer3());
                /*
                if (pos3 == pos1) {
                    view.getPawnPane1().add(view.getIvPlayer3(), 0, 1);
                } else if (pos3 == pos2) {
                    view.getPawnPane2().add(view.getIvPlayer3(), 0, 1);
                } else if (pos3 == pos4) {
                    view.getPawnPane4().add(view.getIvPlayer3(), 0, 1);
                } else {
                    view.getBoardGrid().getChildren().remove(view.getPawnPane3());
                    view.getBoardGrid().add(view.getPawnPane3(),
                            model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                            model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                    view.getPawnPane3().add(view.getIvPlayer3(), 0, 1);
                }

                pos3 = model.getPlayerPos(model.getCurrentPlayer());

            */
            view.getBoardGrid().add(view.getIvPlayer3(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

                model.nextPlayer();
            } else if (model.getCurrentPlayerId() == 3 && !model.getCurrentPlayer().isPlayer4Finished()) {

                view.getBoardGrid().getChildren().remove(view.getIvPlayer4());
                /*
                if (pos4 == pos1) {
                    view.getPawnPane1().add(view.getIvPlayer4(), 1, 1);
                } else if (pos4 == pos2) {
                    view.getPawnPane2().add(view.getIvPlayer4(), 1, 1);
                } else if (pos4 == pos3) {
                    view.getPawnPane3().add(view.getIvPlayer4(), 1, 1);
                } else {
                    view.getBoardGrid().getChildren().remove(view.getPawnPane4());
                    view.getBoardGrid().add(view.getPawnPane4(),
                            model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                            model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                    view.getPawnPane4().add(view.getIvPlayer4(), 1, 1);
                }

                pos4 = model.getPlayerPos(model.getCurrentPlayer());
                */

            view.getBoardGrid().add(view.getIvPlayer4(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());

                model.nextPlayer();
            } else {
                model.nextPlayer();
            }

            updateView();
        });

        view.getBtnHome().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to return to setup screen?\n" +
                    "Your progress won't be saved.");
            alert.setTitle("Return to Home Screen");
            alert.showAndWait();

            SetupView setupView = new SetupView();
            SetupPresenter setupPresenter = new SetupPresenter(model, view, setupView, primaryStage, setupView.getScene(), view.getScene());
            model.getPlayers().clear();
            model.setCurrentPlayer(0);

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

        view.getLblplayerName().setText(model.getCurrentPlayerName());

        if(model.getCurrentPlayerId() == 0 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer1Finished(true);

            view.getIvPlayer1().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();

            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if(model.getCurrentPlayerId() == 1 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer2Finished(true);

            view.getIvPlayer2().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();


            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if(model.getCurrentPlayerId() == 2 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer3Finished(true);

            view.getIvPlayer3().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();


            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if(model.getCurrentPlayerId() == 3 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer4Finished(true);

            view.getIvPlayer4().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();

            //model.getPlayers().remove(model.getCurrentPlayer());

        }

        if(model.getPlayers().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End Of Game");
            alert.setHeaderText(null);
            alert.setContentText("Ranking: \n" +
                    "First place : " + scoreboard.get(0) + "\n" +
                    "Second Place : " + scoreboard.get(1) + "\n" +
                    "Third Place : " + scoreboard.get(2) + "\n" +
                    "Fourth Place : " + scoreboard.get(3));

            alert.show();

        }
        //alternatative method to show instead of alert
        /*
        if(model.getPlayers().isEmpty()){
            Dialog dialog = new Dialog();
            dialog.setTitle("End Of Game");
            dialog.setContentText("Ranking: \n" +
                    "First place : " + scoreboard.get(0) + "\n" +
                    "Second Place : " + scoreboard.get(1) + "\n" +
                    "Third Place : " + scoreboard.get(2) + "\n" +
                    "Fourth Place : " + scoreboard.get(3));
            Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/snakeandladder.png"));
            dialog.show();
        }
        */
    }
}

