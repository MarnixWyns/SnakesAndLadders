package be.kdg.SnakesAndLadders.view.Game;

import be.kdg.SnakesAndLadders.model.Feedback;
import be.kdg.SnakesAndLadders.model.Player;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.DialogThrower;
import be.kdg.SnakesAndLadders.view.Start.StartPresenter;
import be.kdg.SnakesAndLadders.view.Start.StartView;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class GamePresenter {
    private GameView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private ArrayList<Player> finishedPlayers;
    private int dice;
    private DialogThrower dialogThrower;


    public GamePresenter(GameView view, SnakesAndLadders snakesAndLadders, Stage primarystage) {
        this.view = view;
        this.model = snakesAndLadders;
        finishedPlayers = new ArrayList<>();
        this.primaryStage = primarystage;
        dialogThrower = new DialogThrower();

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {
        //change background accordingly.
        if (model.isBackgroundChanged()) {
            view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image(model.getSelectedBackground()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
        } else {
            model.setSelectedBackground("BackgroundImages/normal.jpg");
            view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image(model.getSelectedBackground()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
        }

        view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());

        //Roll dice on button press
        view.getBtnRollDice().setOnAction(event -> {

            SequentialTransition stMain = new SequentialTransition();
            int startpos = model.getCurrentPlayer().getPlayerPos();

            dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));

            if (startpos + dice > 100) {
                stMain.getChildren().add(animateRebound(startpos));

                model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());
                if (100 - (startpos + dice - 100) != model.getCurrentPlayer().getPlayerPos()) {
                    System.out.println((startpos + dice - 100));
                    stMain.getChildren().add(animateSnakesLaddersRebound(100 - (startpos + dice - 100), (startpos + dice - 100)));
                }


            } else {
                stMain.getChildren().add(animateMovement(startpos));

                model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());

                if (startpos + dice != model.getPlayerPos(model.getCurrentPlayer())) {
                    stMain.getChildren().add(animateSnakesLadders(startpos));
                }
            }

            stMain.play();

            //disable the throw button while animation is running and enable it again after the animation has finished.
            view.getBtnRollDice().setDisable(true);

            stMain.setOnFinished(event1 -> {
                view.getBtnRollDice().setDisable(false);
                checkPos();

                model.nextPlayer();
                view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());

                updateView();
            });

            //feedback
            view.getLblFeedbackName().setText("Feedback on " + model.getCurrentPlayer().getUsername() +"\'s throw:");

            if(startpos + dice == 100){
                view.getLblFeedback().setText(Feedback.NINE.toString());
            }else if(startpos + dice > model.getPlayerPos(model.getCurrentPlayer())){
                view.getLblFeedback().setText(Feedback.SEVEN.toString());
            } else if(startpos + dice < model.getPlayerPos(model.getCurrentPlayer())) {
                view.getLblFeedback().setText(Feedback.EIGHT.toString());
            } else {
                switch (dice){
                    case 1: view.getLblFeedback().setText(Feedback.ONE.toString()); break;
                    case 2: view.getLblFeedback().setText(Feedback.TWO.toString()); break;
                    case 3: view.getLblFeedback().setText(Feedback.THREE.toString()); break;
                    case 4: view.getLblFeedback().setText(Feedback.FOUR.toString()); break;
                    case 5: view.getLblFeedback().setText(Feedback.FIVE.toString()); break;
                    case 6: view.getLblFeedback().setText(Feedback.SIX.toString()); break;
                }
            }
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
            model.setCurrentPlayer(0);

            if (alert.getResult() == cancel) {
                event.consume();
            } else if (alert.getResult() == save) {
                model.getBoardScan().save(model.getDifficultyFile(), model.getPlayers());
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

        try {
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
        } catch (NullPointerException e) {
            dialogThrower.throwAlert(Alert.AlertType.ERROR, "No save file", "No save file has been found");
        }

        view.getBtnHelp().setOnAction(event -> dialogThrower.throwHelpDialog());
    }

    private void checkPos() {
        if (model.getCurrentPlayer().getPlayerPos() == 100) {
            dialogThrower.throwAlert(Alert.AlertType.INFORMATION, "A player has finished", "", model.getCurrentPlayer().getUsername() + " has finished!");
            if (!finishedPlayers.contains(model.getCurrentPlayer())) {
                finishedPlayers.add(model.getCurrentPlayer());
            }

            if (finishedPlayers.size() + 1 == model.getPlayers().size()) {

                for (Player player : model.getPlayers()) {
                    if (!finishedPlayers.contains(player)){
                        finishedPlayers.add(player);
                    }
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("End Of Game");
                alert.setHeaderText("Ranking:");

                StringBuilder st = new StringBuilder("Ranking: \n");
                int number = 1;
                for (Player s : finishedPlayers) {
                    st.append(number++).append(". ").append(s.getUsername()).append("\n");
                }
                alert.setContentText(st.toString());

                alert.show();
                view.getBtnRollDice().setDisable(true);
            }
        }
    }

    private void updateView() {

        //AI movement
        if (model.getCurrentPlayer().getUsername().equals("Computer")) {
            view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());
            dice = model.throwDice();
            view.getIvDice().setImage(new Image(view.getDIEURL() + dice + ".png"));

            SequentialTransition stMain = new SequentialTransition();
            int startpos = model.getCurrentPlayer().getPlayerPos();

            model.getCurrentPlayer().addToPlayerPos(dice, model.getBoardScan().getBoard());
            if (startpos + dice > 100) {

                stMain.getChildren().add(animateRebound(startpos));
                stMain.getChildren().add(animateSnakesLadders(startpos));

                stMain.play();
                view.getBtnRollDice().setDisable(true);
                stMain.setOnFinished(event -> view.getLblplayerName().setText(model.getCurrentPlayer().getUsername()));

            } else {
                stMain.getChildren().add(animateMovement(startpos));
                stMain.getChildren().add(animateSnakesLadders(startpos));

                stMain.play();
                view.getBtnRollDice().setDisable(true);

                view.getLblFeedback().setText("AI Row: " + model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) + " Column: " + model.translateToColumn(model.getPlayerPos(model.getCurrentPlayer()))
                        + " Pos: " + model.getCurrentPlayer().getPlayerPos());

                stMain.setOnFinished(event -> {
                    model.nextPlayer();
                    view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());
                    view.getBtnRollDice().setDisable(false);
                });
            }


        }

        while (model.getPlayerPos(model.getCurrentPlayer()) == 100) {
            model.nextPlayer();

            view.getLblplayerName().setText(model.getCurrentPlayer().getUsername());
            updateView();
        }

    }

    private ImageView getCurrentIV() {
        int index = model.getPlayers().indexOf(model.getCurrentPlayer());
        ImageView iv = null;

        switch (index) {
            case 0:
                iv = view.getIvPlayer1();
                break;
            case 1:
                iv = view.getIvPlayer2();
                break;
            case 2:
                iv = view.getIvPlayer3();
                break;
            case 3:
                iv = view.getIvPlayer4();
                break;
        }
        return iv;
    }

    private SequentialTransition animateRebound(int startpos) {
        SequentialTransition tr = new SequentialTransition();

        int moves = 0;
        //Whilst haven't hit 100 yet
        for (int i = startpos; i < 100; i++) {
            TranslateTransition tt = new TranslateTransition();
            tt.setByX(-view.getBoardGrid().getWidth() / 10);
            tt.setDuration(Duration.millis(250));

            tt.setNode(getCurrentIV());

            tr.getChildren().add(tt);

            moves++;
        }

        if (startpos + dice != model.getPlayerPos(model.getCurrentPlayer())) {
            TranslateTransition ttSL = new TranslateTransition();
            ttSL.setNode(getCurrentIV());
        }

        //Rebound after 100
        for (int i = dice - moves; i > 0; i--) {
            TranslateTransition tt = new TranslateTransition();
            tt.setByX(view.getBoardGrid().getWidth() / 10);
            tt.setDuration(Duration.millis(250));

            tt.setNode(getCurrentIV());

            tr.getChildren().add(tt);
        }

        tr.play();

        return tr;
    }

    private SequentialTransition animateMovement(int startpos) {
        SequentialTransition st = new SequentialTransition();

        for (int i = startpos; i < startpos + dice; i++) {
            TranslateTransition tt = new TranslateTransition();
            tt.setNode(getCurrentIV());


            if (i % 10 == 0) {
                tt.setByY(-view.getBoardGrid().getWidth() / 10);
            } else if ((i / 10) % 2 == 0) {
                tt.setByX(view.getBoardGrid().getWidth() / 10);
            } else if ((i / 10) % 2 == 1) {
                tt.setByX(-view.getBoardGrid().getWidth() / 10);
            }

            tt.setDuration(Duration.millis(250));

            st.getChildren().add(tt);
        }

        return st;
    }

    private TranslateTransition animateSnakesLadders(int startpos) {
        TranslateTransition ttSL = new TranslateTransition();
        ttSL.setNode(getCurrentIV());

        int difRows = model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) - model.translateToRow(startpos + dice);

        int start = model.translateToColumn(startpos + dice);
        int stop = model.translateToColumn(model.getCurrentPlayer().getPlayerPos());

        int difColumns = stop - start;

        ttSL.setByY(difRows * (view.getBoardGrid().getHeight() / 10));
        ttSL.setByX(difColumns * (view.getBoardGrid().getWidth() / 10));
        ttSL.setDuration(Duration.millis(800));

        return ttSL;
    }

    private TranslateTransition animateSnakesLaddersRebound(int startpos, int rebound){
        TranslateTransition ttSL = new TranslateTransition();
        ttSL.setNode(getCurrentIV());

        int difRows = model.translateToRow(model.getPlayerPos(model.getCurrentPlayer())) - model.translateToRow(startpos + dice);

        int start = model.translateToColumn(startpos + dice - (startpos + dice - rebound));
        int stop = model.translateToColumn(model.getCurrentPlayer().getPlayerPos());

        int difColumns = stop - start;

        ttSL.setByY((difRows * (view.getBoardGrid().getHeight() / 10)) - (view.getBoardGrid().getHeight() / 10));
        ttSL.setByX((difColumns * (view.getBoardGrid().getWidth() / 10)) - (view.getBoardGrid().getHeight() / 10));
        ttSL.setDuration(Duration.millis(800));

        return ttSL;
    }


}


