package be.kdg.SnakesAndLadders.view.Setup;

import be.kdg.SnakesAndLadders.model.PieceColor;
import be.kdg.SnakesAndLadders.model.Player;
import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.model.SnakesAndLaddersException;
import be.kdg.SnakesAndLadders.view.DialogThrower;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Setup presenter, handles the player creation, board switching and preview and feeds all the entered information into the model classes.
 *
 * @author Marnix Wyns
 * @author Ruben Vanloo
 */
public class SetupPresenter {
    private SnakesAndLadders model;

    private SetupView view;
    private Stage primaryStage;
    private DialogThrower dialogThrower;

    public SetupPresenter(SnakesAndLadders model, GameView gameView, SetupView setupView, Stage primaryStage) {
        this.model = model;
        this.view = setupView;
        this.primaryStage = primaryStage;
        dialogThrower = new DialogThrower();
        this.model.startGame();

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            model.getBoardScan().readFile(new File(classLoader.getResource("BoardLayouts/normal.txt").getFile()));
        } catch (SnakesAndLaddersException e) {
            dialogThrower.throwAlert(Alert.AlertType.WARNING, "No such game file", "Game file not found");
        }

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        view.getBtnHome().setOnAction(event -> dialogThrower.throwHomeAlert(model, primaryStage, view));

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

            if (view.getOnePlayer().isSelected() && view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "Player name is empty.");
                return;
            }
            if (view.getTwoPlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()))) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if (view.getThreePlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()) || (view.getTfP3name() == null || view.getTfP3name().getText().trim().isEmpty()))) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if (view.getFourPlayers().isSelected() && ((view.getTfP1name() == null || view.getTfP1name().getText().trim().isEmpty()) || (view.getTfP2name() == null || view.getTfP2name().getText().trim().isEmpty()) || (view.getTfP3name() == null || view.getTfP3name().getText().trim().isEmpty()) || (view.getTfP4name() == null || view.getTfP4name().getText().trim().isEmpty()))) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Player name empty!", "One of the player names is empty.");
                return;
            }
            if (!view.getHardDifficulty().isSelected() && !view.getNormalDifficulty().isSelected() && !view.getEasyDifficulty().isSelected()) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No difficulty selected", "Please select a difficulty");
                return;
            }


            try {
                ArrayList<Player> players = new ArrayList<>();

                ArrayList<TextField> playerNames = new ArrayList<>(Arrays.asList(view.getTfP1name(), view.getTfP2name(), view.getTfP3name(), view.getTfP4name()));

                for (TextField playerName : playerNames) {
                    if (playerName.getText().length() > 10) {
                        dialogThrower.throwAlert(Alert.AlertType.INFORMATION, "Illegal player name", "Player names are limited by 10 characters in length");

                    }
                }

                players.add(new Player(view.getColorPickerP1().getValue(), view.getTfP1name().getText()));
                model.getColorChoice().add(0,view.getColorPickerP1().getValue());
                players.add(new Player(view.getColorPickerP2().getValue(), view.getTfP2name().getText()));
                model.getColorChoice().add(1,view.getColorPickerP2().getValue());
                players.add(new Player(view.getColorPickerP3().getValue(), view.getTfP3name().getText()));
                model.getColorChoice().add(2,view.getColorPickerP3().getValue());
                players.add(new Player(view.getColorPickerP4().getValue(), view.getTfP4name().getText()));
                model.getColorChoice().add(3,view.getColorPickerP4().getValue());


                //Should filter out all players that aren't valid
                for (Player player : players) {
                    if (!player.getUsername().equals("")) {
                        model.addPlayer(player);
                    }
                }

                if (model.getPlayers().size() == 1 || view.getOnePlayer().isSelected()) {
                    model.addPlayer(new Player(PieceColor.BLACK, "Computer"));
                }

                //Switch between scenes from setup to Game
                GameView gameView = new GameView();
                GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
                view.getScene().setRoot(gameView);
                gameView.getScene().getWindow().setHeight(600);
                gameView.getScene().getWindow().setWidth(1024);

            } catch (IndexOutOfBoundsException i) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "No player Names", "Please give at least 1 player a name");
            }
        });

        view.getEasyDifficulty().setOnAction(event -> {
            setDifficulty("easy");
            view.getLblPreviewInfo().setText("Easy difficulty: The amount of snakes and ladders is reduced.");
        });

        view.getNormalDifficulty().setOnAction(event -> {
            setDifficulty("normal");
            view.getLblPreviewInfo().setText("Normal difficulty: Snakes and ladders are evenly distributed.");
        });

        view.getHardDifficulty().setOnAction(event -> {
            setDifficulty("hard");
            view.getLblPreviewInfo().setText("Hard difficulty: More snakes than ladders.");
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
            disableFields(true, true, true);

            hidePlayers(false, false, false);
        });

        view.getTwoPlayers().setOnAction(event -> {
            disableFields(false, true, true);

            hidePlayers(true, false, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
        });

        view.getThreePlayers().setOnAction(event -> {
            disableFields(false, false, true);

            hidePlayers(true, true, false);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);

        });

        view.getFourPlayers().setOnAction(event -> {
            disableFields(false, false, false);

            hidePlayers(true, true, true);

            view.getColorPickerP1().getSelectionModel().select(0);
            view.getColorPickerP2().getSelectionModel().select(1);
            view.getColorPickerP3().getSelectionModel().select(2);
            view.getColorPickerP4().getSelectionModel().select(3);

        });
        //endregion


        //connect comboboxes to pawncolors and change accordingly
        view.getColorPickerP1().setOnAction(event -> setPlayerColor(view.getColorPickerP1(), view.getIvPlayer1()));

        view.getColorPickerP2().setOnAction(event -> setPlayerColor(view.getColorPickerP2(), view.getIvPlayer2()));

        view.getColorPickerP3().setOnAction(event -> setPlayerColor(view.getColorPickerP3(), view.getIvPlayer3()));

        view.getColorPickerP4().setOnAction(event -> setPlayerColor(view.getColorPickerP4(), view.getIvPlayer4()));
        view.getBtnHelp().setOnAction(event -> dialogThrower.throwHelpDialog());
    }

    private void updateView() {

    }

    /**
     * Method that is called when a RadioButton is selected, the game file according to the selected radiobutton is read and
     * the path is stored in the model class. After this the background is set according to the image path read in the game file.
     *
     * @param difficulty string that is given with lowercase difficulty.
     */
    private void setDifficulty(String difficulty) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            model.setDifficultyFile(new File(classLoader.getResource("BoardLayouts/" + difficulty + ".txt").getFile()));
            model.getBoardScan().readFile(model.getDifficultyFile());
            try {
                view.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image("Backgroundimages/" + model.getBoardScan().getBoard().getBgPath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, view.getBackgroundBoard())));
            } catch (IllegalArgumentException e) {
                dialogThrower.throwAlert(Alert.AlertType.WARNING, "Background error", "No such background image found.");
                System.exit(1);
            }
            model.setSelectedBackground("BackgroundImages/" + difficulty + ".jpg");


        } catch (SnakesAndLaddersException e) {
            dialogThrower.throwAlert(Alert.AlertType.WARNING, "No game file", "Game file not found", "The game will now close.");
            System.exit(1);
        }
        model.setBackgroundChanged(true);
    }

    /**
     * method to remove duplicate code. Is given a combobox to read and depending on contents, saves player color
     * and sets the image in the game preview
     *
     * @param cb Takes a combobox to read and apply the selected color for
     */
    private void setPlayerColor(ComboBox cb, ImageView iv) {
        if (cb.getSelectionModel().isSelected(0)) {
            iv.setImage(view.getYellow());
        } else if (cb.getSelectionModel().isSelected(1)) {
            iv.setImage(view.getGreen());
        } else if (cb.getSelectionModel().isSelected(2)) {
            iv.setImage(view.getBlue());
        } else if (cb.getSelectionModel().isSelected(3)) {
            iv.setImage(view.getRed());
        }
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
