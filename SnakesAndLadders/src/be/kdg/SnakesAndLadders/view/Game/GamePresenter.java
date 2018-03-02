package be.kdg.SnakesAndLadders.view.Game;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Start.*;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private ArrayList<String> scoreboard;
    private int dice;
    private int teller = 1;


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

            if (teller == 1) {
                view.getPawnPane().getChildren().remove(view.getIvPlayer1());
            } else if (teller == 2) {
                view.getPawnPane().getChildren().remove(view.getIvPlayer2());
            } else if (teller == 3) {
                view.getPawnPane().getChildren().remove(view.getIvPlayer3());
            } else if (teller == 4) {
                view.getPawnPane().getChildren().remove(view.getIvPlayer4());
            }

            dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));

            model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());


            view.getBoardGrid().getChildren().remove(model.getCurrentPlayerImage());


            view.getBoardGrid().add(model.getCurrentPlayerImage(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));


            /*
            if(model.getCurrentPlayerId() == 0){
                gridPane.add(view.getIvPlayer1(),0,0);

            }
            if(model.getCurrentPlayerId() == 1){
                gridPane.add(view.getIvPlayer2(),1,0);
            }
            if(model.getCurrentPlayerId() == 2){
                gridPane.add(view.getIvPlayer3(),0,1);
            }
            if(model.getCurrentPlayerId() == 3){
                gridPane.add(view.getIvPlayer4(),1,1);
            }
            */

            /*
            view.getBoardGrid().getChildren().remove(model.getCurrentPlayerImage());

            view.getBoardGrid().add(model.getCurrentPlayerImage(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
            */

            /* //TODO: See if usefull
            if (model.getCurrentPlayerId() == 0) {
                pos1 = model.getPlayerPos(model.getCurrentPlayer());
            } else if (model.getCurrentPlayerId() == 1) {
                pos2 = model.getPlayerPos(model.getCurrentPlayer());
            } else if (model.getCurrentPlayerId() == 2) {
                pos3 = model.getPlayerPos(model.getCurrentPlayer());
            } else if (model.getCurrentPlayerId() == 3) {
                pos4 = model.getPlayerPos(model.getCurrentPlayer());
            }
            */

            /*
            System.out.println(pos1);
            System.out.println(pos2);
            System.out.println(pos3);
            System.out.println(pos4);
            System.out.println("--");

            if(pos1 == pos2 && pos1 != pos3 && pos1 != pos4){
                view.getBoardGrid().getChildren().remove(view.getPawnPane2());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer1());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer2());
                view.getBoardGrid().add(view.getPawnPane2(),
                        model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                        model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                view.getPawnPane2().add(view.getIvPlayer1(), 0,0);
                view.getPawnPane2().add(view.getIvPlayer2(), 1,0);
            }
            if(pos1 == pos3 && pos1 != pos2 && pos1 != pos4) {
                view.getBoardGrid().getChildren().remove(view.getPawnPane3());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer1());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer3());
                view.getBoardGrid().add(view.getPawnPane3(),
                        model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                        model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                view.getPawnPane3().add(view.getIvPlayer1(), 0,0);
                view.getPawnPane3().add(view.getIvPlayer3(), 1,0);
            }
            if(pos1 == pos4 && pos1 != pos2 && pos1 != pos3) {
                view.getBoardGrid().getChildren().remove(view.getPawnPane4());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer1());
                view.getBoardGrid().getChildren().remove(view.getIvPlayer4());
                view.getBoardGrid().add(view.getPawnPane3(),
                        model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                        model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));
                view.getPawnPane3().add(view.getIvPlayer1(), 0,0);
                view.getPawnPane3().add(view.getIvPlayer4(), 1,0);
            }
            */

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());


            //view.getLblFeedback().setText(model.getFeedback().getRandFeedback());

            teller++;

            model.nextPlayer();


            updateView();
        });

        view.getBtnHome().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you sure you want to return to start screen?\n" +
                    "Your progress won't be saved.");
            alert.setTitle("Return to Start Screen");
            alert.getButtonTypes().clear();
            ButtonType cancel = new ButtonType("Cancel");
            ButtonType yes = new ButtonType("Return");
            ButtonType save = new ButtonType("Save and Return");
            alert.getButtonTypes().addAll(save, cancel, yes);
            alert.showAndWait();

            StartView startView = new StartView();
            StartPresenter startPresenter = new StartPresenter(startView, model, primaryStage);
            model.getPlayers().clear();
            teller = 1;
            model.getPlayerImages().clear();
            model.setCurrentPlayer(0);

            //todo: Marnix, ook hier heb ik de save optie der bij gezet dus die if statement voor die button moet nog ingevuld worden
            if (alert.getResult() == cancel) {
                event.consume();
            } else if (alert.getResult() == save) {
                model.getBoardScan().save(model.getDifficultyFile());
            } else view.getScene().setRoot(startView);

        });


        //exitbutton intelligence
        view.getBtnExit().setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setTitle("Warning!");
            alert.getButtonTypes().clear();
            ButtonType cancel = new ButtonType("Cancel");
            ButtonType yes = new ButtonType("Exit");
            ButtonType save = new ButtonType("Save and Quit");
            alert.getButtonTypes().addAll(save, cancel, yes);
            alert.showAndWait();

            if (alert.getResult() == cancel) {
                event.consume();
            } else if (alert.getResult() == save) {
                //TODO: DifficultyFile is empty??????????????????????????????????????????????????????????
                model.getBoardScan().save(Paths.get("./resources/BoardLayouts/normal.txt").toFile());
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

        //AI movement
        //TODO: Add an animation or something to let the player know the computer haz moved
        if (model.getCurrentPlayerName().equals("Computer")) {
            dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));

            model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());

            view.getBoardGrid().getChildren().remove(view.getIvPlayer2());

            view.getBoardGrid().add(view.getIvPlayer2(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));


            view.getLblFeedback().setText("AI Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        }

        view.getLblplayerName().setText(model.getCurrentPlayerName());

        if (model.getCurrentPlayerId() == 0 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer1Finished(true);

            view.getIvPlayer1().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();

            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 1 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer2Finished(true);

            view.getIvPlayer2().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();


            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 2 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer3Finished(true);

            view.getIvPlayer3().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();


            //model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 3 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayerName());
            model.getCurrentPlayer().setPlayer4Finished(true);

            view.getIvPlayer4().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();

            //model.getPlayers().remove(model.getCurrentPlayer());

        }

        if (model.getPlayers().isEmpty()) {
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

