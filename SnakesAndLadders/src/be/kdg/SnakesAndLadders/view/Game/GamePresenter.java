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

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;

    public GamePresenter(GameView view, SnakesAndLadders snakesAndLadders, Stage primarystage) {
        this.view = view;
        this.model = snakesAndLadders;
        this.primaryStage = primarystage;

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
                view.getGameButtons().setPadding(new Insets(75,50,0,0));
                view.getBoardBackground().setPadding(new Insets(150,75,25,175));
            } else {
                primaryStage.setFullScreen(false);
                view.getGameButtons().setPadding(new Insets(50,0,0,50));
                view.getBoardBackground().setPadding(new Insets(30,0,30,90));
            }
        });

        if(model.getPlayers().size() == 1){
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);
        }else if (model.getPlayers().size() == 2){
            view.getIvPlayer1().setVisible(true);
            view.getIvPlayer2().setVisible(true);
            view.getIvPlayer3().setVisible(false);
            view.getIvPlayer4().setVisible(false);
        }else if (model.getPlayers().size() == 3){
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
    }

    private void updateView() {

        //TODO: Disable a player if position 100 is reached, store playername in arrayList

        view.getLblplayerName().setText(model.getCurrentPlayerName());

        if (model.getCurrentPlayer().getPlayerPos() == 100){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayerName());
            alert.setContentText(model.getCurrentPlayerName() + " has finished");
            alert.show();
        }

        //TODO: Try to compact this

        if (model.getCurrentPlayerId() == 0) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer1());

            if(model.getCurrentPlayer().getPlayerPos() == 2){
                view.getBoardGrid().add(view.getIvPlayer1(),2,6);

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());
            } else if (model.getCurrentPlayer().getPlayerPos() == 7){
                view.getBoardGrid().add(view.getIvPlayer1(), 6,8);

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());
            } else if(model.getCurrentPlayer().getPlayerPos() == 8){
                view.getBoardGrid().add(view.getIvPlayer1(), 9,6);

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());
            }
            else {
                view.getBoardGrid().add(view.getIvPlayer1(),
                        model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                        model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

                view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());
            }

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 1) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer2());

            view.getBoardGrid().add(view.getIvPlayer2(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 2) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer3());

            view.getBoardGrid().add(view.getIvPlayer3(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        } else if (model.getCurrentPlayerId() == 3) {
            view.getBoardGrid().getChildren().remove(view.getIvPlayer4());

            view.getBoardGrid().add(view.getIvPlayer4(),
                    model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer())),
                    model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())));

            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
        }
    }
}
