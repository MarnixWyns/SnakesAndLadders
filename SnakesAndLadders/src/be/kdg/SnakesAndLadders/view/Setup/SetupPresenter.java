package be.kdg.SnakesAndLadders.view.Setup;

import be.kdg.SnakesAndLadders.model.*;
import be.kdg.SnakesAndLadders.view.DialogThrower;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SetupPresenter {
    private SnakesAndLadders model;

    private SetupView view;
    private Stage primaryStage;
    private Scene setupScene;
    private DialogThrower dialogThrower;

    public SetupPresenter(SnakesAndLadders model, GameView gameView, SetupView setupView, Stage primaryStage,
                          Scene setupScene, Scene gameScene) {
        this.model = model;
        this.view = setupView;
        this.primaryStage = primaryStage;
        this.setupScene = setupScene;
        dialogThrower = new DialogThrower();
        this.model.startGame();

        //TODO: This has to be initialised first time, maybe better placement however

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            model.getBoardScan().readFile(new File(classLoader.getResource("BoardLayouts/Normal.txt").getFile()));
            //TODO: obtain background file from Board
        } catch (SnakesAndLaddersException e) {
            dialogThrower.throwAlert(Alert.AlertType.WARNING, "No such game file", "Game file not found");
        }

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        //check and delete if playernames exceed limit
        view.getTfP1name().setOnKeyTyped(event -> {
            if (view.getTfP1name().getLength() >= 10) {
                event.consume();
            }
        });

        view.getTfP2name().setOnKeyTyped(event -> {
            if (view.getTfP2name().getLength() >= 10) {
                event.consume();
            }
        });

        view.getTfP3name().setOnKeyTyped(event -> {
            if (view.getTfP3name().getLength() >= 10) {
                event.consume();
            }
        });

        view.getTfP4name().setOnKeyTyped(event -> {
            if (view.getTfP4name().getLength() >= 10) {
                event.consume();
            }
        });

        //startButton intelligence
        view.getBtnStartGame().setOnAction(event -> {

            if(view.getOnePlayer().isSelected() && view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()){
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "Player name is empty.");
                return;
            }
            if(view.getTwoPlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()))){
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if(view.getThreePlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()) || (view.getTfP3name() == null || view.getTfP3name().getText().trim().isEmpty()))){
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if(view.getFourPlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()) || (view.getTfP3name() == null || view.getTfP3name().getText().trim().isEmpty()) || (view.getTfP4name() == null || view.getTfP4name().getText().trim().isEmpty()))){
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if(!view.getHardDifficulty().isSelected() && !view.getNormalDifficulty().isSelected() && !view.getEasyDifficulty().isSelected()){
                dialogThrower.throwAlert(Alert.AlertType.WARNING,"No difficulty selected", "Please select a difficulty");
                return;
            }


            try {
                ArrayList<Player> players = new ArrayList<>();

                ArrayList<TextField> playerNames = new ArrayList<>();
                playerNames.addAll(Arrays.asList(view.getTfP1name(), view.getTfP2name(), view.getTfP3name(), view.getTfP4name()));

                for (TextField playerName : playerNames) {
                    if (playerName.getText().length() > 10) {
                        dialogThrower.throwAlert(Alert.AlertType.INFORMATION, "Illegal player name", "Player names are limited by 10 characters in length");

                    }
                }

                players.add(new Player(view.getColorPickerP1().getValue(), view.getTfP1name().getText()));
                players.add(new Player(view.getColorPickerP2().getValue(), view.getTfP2name().getText()));
                players.add(new Player(view.getColorPickerP3().getValue(), view.getTfP3name().getText()));
                players.add(new Player(view.getColorPickerP4().getValue(), view.getTfP4name().getText()));



                //Should filter out all players that aren't valid
                for (Player player : players) {
                    if (!player.getUsername().equals("")) {
                        model.addPlayer(player);
                    }
                }

                if (model.getPlayers().size() == 1 || view.getOnePlayer().isSelected()) {
                    model.addPlayer(new Player(PieceColor.YELLOW, "Computer"));
                }

                //Switch between scenes from setup to Game
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow().setHeight(600);
                gameView.getScene().getWindow().setWidth(1024);

                model.setCountPlayers(2);


            } catch (IndexOutOfBoundsException i) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No player Names", "Please give at least 1 player a name");
            }
        });

        view.getEasyDifficulty().setOnAction(event -> {
            ClassLoader classLoader = getClass().getClassLoader();
            try {
                model.setDifficultyFile(new File(classLoader.getResource("BoardLayouts/Easy.txt").getFile()));
                model.getBoardScan().readFile(model.getDifficultyFile());
                try {
                    view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image("Backgroundimages/" + model.getBoardScan().getBgPath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
                } catch (IllegalArgumentException e){
                    dialogThrower.throwAlert(Alert.AlertType.WARNING, "Background error", "No such background image found.");
                    System.exit(1);
                }
                model.setSelectedBackground("BackgroundImages/easy.jpg");
                view.getLblPreviewInfo().setText("Easy difficulty: The amount of snakes and ladders is reduced.");

                //TODO: obtain background file from Board
            } catch (SnakesAndLaddersException e) {
                System.exit(1);
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No such game file", "Game file not found", "The program will now self destruct");
            }
        });

        view.getNormalDifficulty().setOnAction(event -> {
            ClassLoader classLoader = getClass().getClassLoader();
            try {
                model.setDifficultyFile(new File(classLoader.getResource("BoardLayouts/Normal.txt").getFile()));
                model.getBoardScan().readFile(model.getDifficultyFile());
                try{
                    view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image("Backgroundimages/" + model.getBoardScan().getBgPath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
                } catch (IllegalArgumentException e){
                    dialogThrower.throwAlert(Alert.AlertType.WARNING, "Background error", "No such background image found.");
                    System.exit(1);
                }
                model.setSelectedBackground("BackgroundImages/normal.jpg");
                view.getLblPreviewInfo().setText("Normal difficulty: Snakes and ladders are evenly distributed.");

            } catch (SnakesAndLaddersException e) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No such game file", "Game file not found");
                System.exit(1);
            }
        });

        view.getHardDifficulty().setOnAction(event -> {
            ClassLoader classLoader = getClass().getClassLoader();
            try {
                model.setDifficultyFile(new File(classLoader.getResource("BoardLayouts/Hard.txt").getFile()));
                model.getBoardScan().readFile(model.getDifficultyFile());
                try{
                    view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image("Backgroundimages/" + model.getBoardScan().getBgPath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
                }catch (IllegalArgumentException e){
                    dialogThrower.throwAlert(Alert.AlertType.WARNING, "Background error", "No such background image found.");
                    System.exit(1);
                }
                model.setSelectedBackground("BackgroundImages/hard.jpg");
                view.getLblPreviewInfo().setText("Hard difficulty: More snakes than ladders.");

            } catch (SnakesAndLaddersException e) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No such game file", "Game file not found");
                System.exit(1);
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

        //region ToggleGroup Amount of players
        view.getOnePlayer().setOnAction(event -> {
            model.setCountPlayers(2);

            disableFields(true, true, true);

            hidePlayers(false, false, false);
        });

        view.getTwoPlayers().setOnAction(event -> {
            model.setCountPlayers(2);

            disableFields(false, true, true);

            hidePlayers(true, false, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
        });

        view.getThreePlayers().setOnAction(event -> {
            model.setCountPlayers(3);

            disableFields(false, false, true);

            hidePlayers(true, true, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);

        });

        view.getFourPlayers().setOnAction(event -> {
            model.setCountPlayers(4);

            disableFields(false, false, false);

            hidePlayers(true, true, true);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);
            view.getColorPickerP4().getSelectionModel().select(3);

        });
        //endregion

        //initialisatie in het geval de spelers de default waarden van de comboboxen nemen
        model.setColorPlayer1("PawnImages/yellow.png");
        model.setColorPlayer2("PawnImages/green.png");
        model.setColorPlayer3("PawnImages/blue.png");
        model.setColorPlayer4("PawnImages/red.png");

        //connect comboboxes to pawncolors and change accordingly
        view.getColorPickerP1().setOnAction(event -> {
            if (view.getColorPickerP1().getSelectionModel().isSelected(0)) {
                model.setColorPlayer1("PawnImages/yellow.png");
                view.getIvPlayer1().setImage(view.getYellow());
            } else if (view.getColorPickerP1().getSelectionModel().isSelected(1)) {
                model.setColorPlayer1("PawnImages/green.png");
                view.getIvPlayer1().setImage(view.getGreen());
            } else if (view.getColorPickerP1().getSelectionModel().isSelected(2)) {
                model.setColorPlayer1("PawnImages/blue.png");
                view.getIvPlayer1().setImage(view.getBlue());
            } else if (view.getColorPickerP1().getSelectionModel().isSelected(3)) {
                model.setColorPlayer1("PawnImages/red.png");
                view.getIvPlayer1().setImage(view.getRed());
            }

        });

        view.getColorPickerP2().setOnAction(event -> {
            if (view.getColorPickerP2().getSelectionModel().isSelected(0)) {
                model.setColorPlayer2("PawnImages/yellow.png");
                view.getIvPlayer2().setImage(view.getYellow());
            } else if (view.getColorPickerP2().getSelectionModel().isSelected(1)) {
                model.setColorPlayer2("PawnImages/green.png");
                view.getIvPlayer2().setImage(view.getGreen());
            } else if (view.getColorPickerP2().getSelectionModel().isSelected(2)) {
                model.setColorPlayer2("PawnImages/blue.png");
                view.getIvPlayer2().setImage(view.getBlue());
            } else if (view.getColorPickerP2().getSelectionModel().isSelected(3)) {
                model.setColorPlayer2("PawnImages/red.png");
                view.getIvPlayer2().setImage(view.getRed());
            }
        });

        view.getColorPickerP3().setOnAction(event -> {
            if (view.getColorPickerP3().getSelectionModel().isSelected(0)) {
                model.setColorPlayer3("PawnImages/yellow.png");
                view.getIvPlayer3().setImage(view.getYellow());
            } else if (view.getColorPickerP3().getSelectionModel().isSelected(1)) {
                model.setColorPlayer3("PawnImages/green.png");
                view.getIvPlayer3().setImage(view.getGreen());
            } else if (view.getColorPickerP3().getSelectionModel().isSelected(2)) {
                model.setColorPlayer3("PawnImages/blue.png");
                view.getIvPlayer3().setImage(view.getBlue());
            } else if (view.getColorPickerP3().getSelectionModel().isSelected(3)) {
                model.setColorPlayer3("PawnImages/red.png");
                view.getIvPlayer3().setImage(view.getRed());
            }

        });

        view.getColorPickerP4().setOnAction(event -> {
            if (view.getColorPickerP4().getSelectionModel().isSelected(0)) {
                model.setColorPlayer4("PawnImages/yellow.png");
                view.getIvPlayer4().setImage(view.getYellow());
            } else if (view.getColorPickerP4().getSelectionModel().isSelected(1)) {
                model.setColorPlayer4("PawnImages/green.png");
                view.getIvPlayer4().setImage(view.getGreen());
            } else if (view.getColorPickerP4().getSelectionModel().isSelected(2)) {
                model.setColorPlayer4("PawnImages/blue.png");
                view.getIvPlayer4().setImage(view.getBlue());
            } else if (view.getColorPickerP4().getSelectionModel().isSelected(3)) {
                model.setColorPlayer4("PawnImages/red.png");
                view.getIvPlayer4().setImage(view.getRed());
            }
        });

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

    private void disableFields(boolean dis1, boolean dis2, boolean dis3) {
        view.getTfP2name().setDisable(dis1);
        view.getTfP3name().setDisable(dis2);
        view.getTfP4name().setDisable(dis3);

        view.getColorPickerP2().setDisable(dis1);
        view.getColorPickerP3().setDisable(dis2);
        view.getColorPickerP4().setDisable(dis3);
    }

    private void hidePlayers(boolean dis1, boolean dis2, boolean dis3) {
        view.getIvPlayer2().setVisible(dis1);
        view.getIvPlayer3().setVisible(dis2);
        view.getIvPlayer4().setVisible(dis3);
    }
}
