package be.kdg.SnakesAndLadders.view.Start;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Game.GamePresenter;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import be.kdg.SnakesAndLadders.view.Help.HelpPresenter;
import be.kdg.SnakesAndLadders.view.Help.HelpView;
import be.kdg.SnakesAndLadders.view.Setup.SetupPresenter;
import be.kdg.SnakesAndLadders.view.Setup.SetupView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


public class StartPresenter {
    private StartView view;
    private SnakesAndLadders model;
    private Stage primaryStage;

    public StartPresenter(StartView startView, SnakesAndLadders snakesAndLadders, Stage primaryStage) {
        this.view = startView;
        this.model = snakesAndLadders;
        this.primaryStage = primaryStage;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

        //start a new game when new game is pressed and initialise setupview
        view.getBtnNewGame().setOnAction(event -> {

            SetupView setupView = new SetupView();
            GameView gameView = new GameView();
            SetupPresenter setupPresenter = new SetupPresenter(model, gameView, setupView, primaryStage, setupView.getScene(), view.getScene());
            view.getScene().setRoot(setupView);
        });
        //exit button without warning
        view.getBtnExit().setOnAction(event -> System.exit(0));

        view.getBtnLoadGame().setOnAction(event -> {

            ClassLoader classLoader = getClass().getClassLoader();


            //TODO: throws Boardscan nullpointer, game hasnt started yet so none has been initiialised
            model.startGame();

            //TODO: Fix this shitty workaround
            model.getBoardScan().readFile(new File(classLoader.getResource("save_file.txt").getFile()));

            //Start game met gelezen files
            model.setPlayers(model.getBoardScan().getBoard().getSavedPlayers());
            model.setSelectedBackground(model.getBoardScan().getBoard().getBgPath());


            //Switch between scenes from setup to Game
            GameView gameView = new GameView();
            GamePresenter gamePresenter = new GamePresenter(gameView, model, primaryStage);
            view.getScene().setRoot(gameView);
            gameView.getScene().getWindow().setHeight(600);
            gameView.getScene().getWindow().setWidth(1024);

            //model.setCountPlayers(2);
        });

        view.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HelpView helpView = new HelpView();
                HelpPresenter helpPresenter = new HelpPresenter(helpView);
                Stage helpStage = new Stage();
                helpStage.setTitle("Help");
                helpStage.initOwner(view.getScene().getWindow());
                helpStage.initModality(Modality.APPLICATION_MODAL);
                helpStage.setScene(new Scene(helpView));
                helpStage.showAndWait();
            }
        });
    }

    private void updateView() {
    }
}
