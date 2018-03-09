package be.kdg.SnakesAndLadders.view.Game;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.DialogThrower;
import be.kdg.SnakesAndLadders.view.Start.StartPresenter;
import be.kdg.SnakesAndLadders.view.Start.StartView;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private ArrayList<String> scoreboard;
    private int dice;
    private int teller = 1;
    private DialogThrower dialogThrower;


    public GamePresenter(GameView view, SnakesAndLadders snakesAndLadders, Stage primarystage) {
        this.view = view;
        this.model = snakesAndLadders;
        this.primaryStage = primarystage;
        scoreboard = new ArrayList<>();
        dialogThrower = new DialogThrower();

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        //change background accordingly.
        view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image(
                        model.getSelectedBackground()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));


        //Roll dice on button press
        view.getBtnRollDice().setOnAction(event -> {

            //TODO: Snake animation
            //TODO: Doesn't work for more than 1 turn || Done, Ruben Fixed
            //TODO: Move vertically || Done, Marnix fixed
            //TODO: Move left or right according to position || Should be fixed not sure entirely
            SequentialTransition stMain = new SequentialTransition();
            SequentialTransition st = new SequentialTransition();
            int startpos = model.getCurrentPlayer().getPlayerPos();


            if(teller > model.getPlayers().size()){
                st.getChildren().clear();
                teller = 1;
            }


            dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));



            for (int i = startpos; i < startpos + dice; i++) {
                TranslateTransition tt = new TranslateTransition();
                switch (teller){
                    case 1: tt.setNode(view.getIvPlayer1()); break;
                    case 2: tt.setNode(view.getIvPlayer2()); break;
                    case 3: tt.setNode(view.getIvPlayer3()); break;
                    case 4: tt.setNode(view.getIvPlayer4()); break;
                }

                if (i % 10 == 0){
                    tt.setByY(-view.getBoardGrid().getWidth()/10);
                } else if ((i / 10) % 2 == 0) {
                    tt.setByX(view.getBoardGrid().getWidth()/10);
                } else if ((i / 10) % 2 == 1){
                    tt.setByX(-view.getBoardGrid().getWidth()/10);
                }

                tt.setDuration(Duration.millis(300));

                st.getChildren().add(tt);
            }
            stMain.getChildren().add(st);


            model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());


            if (startpos + dice != model.getPlayerPos(model.getCurrentPlayer())) {
                TranslateTransition ttSL = new TranslateTransition();
                switch (teller){
                    case 1: ttSL.setNode(view.getIvPlayer1()); break;
                    case 2: ttSL.setNode(view.getIvPlayer2()); break;
                    case 3: ttSL.setNode(view.getIvPlayer3()); break;
                    case 4: ttSL.setNode(view.getIvPlayer4()); break;
                }

                //TODO: Doesnt work exactly
                int difRows = model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) - model.translateToRow(startpos+ dice);

                int difColumns = 0;

                ttSL.setByY(difRows * (view.getBoardGrid().getHeight()/10));
                ttSL.setByX(difColumns * (view.getBoardGrid().getWidth()/10));
                ttSL.setDuration(Duration.millis(800));

                stMain.getChildren().add(ttSL);
            }

            stMain.play();

            //TODO: RUBEN! Delete before final code deployment
            view.getLblFeedback().setText("Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                    + " Pos: " + model.getCurrentPlayer().getPlayerPos());

            model.nextPlayer();
            teller++;

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
            model.setCurrentPlayer(0);

            //todo: Marnix, ook hier heb ik de save optie der bij gezet dus die if statement voor die button moet nog ingevuld worden
            if (alert.getResult() == cancel) {
                event.consume();
            } else if (alert.getResult() == save) {
                model.getBoardScan().save(model.getDifficultyFile(), model.getPlayers());
                System.out.print("Saved");
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
                model.getBoardScan().save(model.getDifficultyFile(), model.getPlayers());
                System.exit(0);
            } else System.exit(0);
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

        view.getIvPlayer1().setImage(new Image(model.getColorPlayer1()));
        view.getIvPlayer2().setImage(new Image(model.getColorPlayer2()));
        view.getIvPlayer3().setImage(new Image(model.getColorPlayer3()));
        view.getIvPlayer4().setImage(new Image(model.getColorPlayer4()));

    }

    private void updateView() {

        //AI movement
        //TODO: Add an animation or something to let the player know the computer haz moved
        if (model.getCurrentPlayer().getUsername().equals("Computer")) {
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

        view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());


        //TODO: Clean redundant code
        if (model.getCurrentPlayerId() == 0 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayer().getUsername());
            model.getCurrentPlayer().setPlayer1Finished(true);

            view.getIvPlayer1().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayer().getUsername());
            alert.setContentText(model.getCurrentPlayer().getUsername() + " has finished");
            alert.show();

            model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 1 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayer().getUsername());
            model.getCurrentPlayer().setPlayer2Finished(true);

            view.getIvPlayer2().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayer().getUsername());
            alert.setContentText(model.getCurrentPlayer().getUsername() + " has finished");
            alert.show();


            model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 2 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayer().getUsername());
            model.getCurrentPlayer().setPlayer3Finished(true);

            view.getIvPlayer3().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayer().getUsername());
            alert.setContentText(model.getCurrentPlayer().getUsername() + " has finished");
            alert.show();


            model.getPlayers().remove(model.getCurrentPlayer());

        }
        if (model.getCurrentPlayerId() == 3 && model.getCurrentPlayer().getPlayerPos() == 100) {
            scoreboard.add(model.getCurrentPlayer().getUsername());
            model.getCurrentPlayer().setPlayer4Finished(true);

            view.getIvPlayer4().setVisible(false);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(model.getCurrentPlayer().getUsername());
            alert.setContentText(model.getCurrentPlayer().getUsername() + " has finished");
            alert.show();

            model.getPlayers().remove(model.getCurrentPlayer());

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
    }
}

