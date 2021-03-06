package be.kdg.SnakesAndLadders.view.Start;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.DialogThrower;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import be.kdg.SnakesAndLadders.view.Setup.SetupPresenter;
import be.kdg.SnakesAndLadders.view.Setup.SetupView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

import java.io.File;

/**
 * Start presenter that handles only a few basic tasks, it handles as the start page that forwards the
 * user to the setupView, or lets the user load an existing game from a save file.
 *
 * @author Ruben Vanloo
 * @version 1.0
 */
public class StartPresenter {
    private StartView view;
    private SnakesAndLadders model;
    private Stage primaryStage;
    private DialogThrower dt;

    public StartPresenter(StartView startView, SnakesAndLadders snakesAndLadders, Stage primaryStage) {
        this.view = startView;
        this.model = snakesAndLadders;
        this.primaryStage = primaryStage;

        dt = new DialogThrower();

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        //start a new game when new game is pressed and initialise setupview
        view.getBtnNewGame().setOnAction(event -> {

            SetupView setupView = new SetupView();
            GameView gameView = new GameView();
            SetupPresenter setupPresenter = new SetupPresenter(model, gameView, setupView, primaryStage);
            view.getScene().setRoot(setupView);
            model.setNewGame(true);
        });
        //exit button without warning
        view.getBtnExit().setOnAction(event -> System.exit(0));

        view.getBtnLoadGame().setOnAction(event -> {
            model.startGame();

            model.getBoardScan().readFile(new File("save_file.txt"));

            //Start game met gelezen files
            model.setPlayers(model.getBoardScan().getBoard().getSavedPlayers());
            //model.setSelectedBackground(model.getBoardScan().getBoard().getBgPath());

            //Switch between scenes from setup to Game
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
            gameView.getBoardGrid().setBackground(new Background(new BackgroundImage(new Image("Backgroundimages/" + model.getBoardScan().getBoard().getBgPath()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, gameView.getBackgroundBoard())));
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().setHeight(600);
            gameView.getScene().getWindow().setWidth(1024);

            model.setDifficultyFile(new File("BoardLayouts/" + model.getBoardScan().getBoard().getBgPath().substring(0, model.getBoardScan().getBoard().getBgPath().indexOf('.')) + ".txt"));

            switch (model.getPlayers().size()){
                case 2: {
                    gameView.getIvPlayer1().setImage(new Image(model.getPlayers().get(0).getIvPath()));
                    gameView.getIvPlayer2().setImage(new Image(model.getPlayers().get(1).getIvPath()));
                } break;
                case 3: {
                    gameView.getIvPlayer1().setImage(new Image(model.getPlayers().get(0).getIvPath()));
                    gameView.getIvPlayer2().setImage(new Image(model.getPlayers().get(1).getIvPath()));
                    gameView.getIvPlayer3().setImage(new Image(model.getPlayers().get(2).getIvPath()));
                } break;
                case 4: {
                    gameView.getIvPlayer1().setImage(new Image(model.getPlayers().get(0).getIvPath()));
                    gameView.getIvPlayer2().setImage(new Image(model.getPlayers().get(1).getIvPath()));
                    gameView.getIvPlayer3().setImage(new Image(model.getPlayers().get(2).getIvPath()));
                    gameView.getIvPlayer4().setImage(new Image(model.getPlayers().get(3).getIvPath()));
                }
            }
            gameView.getPawnPane1().add(gameView.getIvPlayer1(), 0, 0);
            gameView.getPawnPane2().add(gameView.getIvPlayer2(), 1, 0);
            gameView.getPawnPane3().add(gameView.getIvPlayer3(), 0, 1);
            gameView.getPawnPane4().add(gameView.getIvPlayer4(), 1, 1);

            gameView.getBoardGrid().add(gameView.getPawnPane1(),
                    model.translateToColumn(model.getCurrentPlayer().getPlayerPos()), model.translateToRow(model.getCurrentPlayer().getPlayerPos()));
            model.nextPlayer();
            gameView.getBoardGrid().add(gameView.getPawnPane2(),
                    model.translateToColumn(model.getCurrentPlayer().getPlayerPos()), model.translateToRow(model.getCurrentPlayer().getPlayerPos()));
            model.nextPlayer();
            gameView.getBoardGrid().add(gameView.getPawnPane3(),
                    model.translateToColumn(model.getCurrentPlayer().getPlayerPos()), model.translateToRow(model.getCurrentPlayer().getPlayerPos()));
            model.nextPlayer();
            gameView.getBoardGrid().add(gameView.getPawnPane4(),
                    model.translateToColumn(model.getCurrentPlayer().getPlayerPos()), model.translateToRow(model.getCurrentPlayer().getPlayerPos()));
            model.nextPlayer();

            gameView.getBtnSave().setDisable(true);

            //model.setCountPlayers(2);
        });
        view.getBtnHelp().setOnAction(event -> dt.throwHelpDialog());
    }


    private void updateView() {
        model.startGame();
        view.getBtnLoadGame().setDisable(!model.getBoardScan().hasSave(new File("save_file.txt")));
    }
}
