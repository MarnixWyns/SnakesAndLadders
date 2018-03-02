package be.kdg.SnakesAndLadders.view.Start;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import be.kdg.SnakesAndLadders.view.Setup.SetupPresenter;
import be.kdg.SnakesAndLadders.view.Setup.SetupView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;


public class StartPresenter {
    private StartView view;
    private SnakesAndLadders model;
    private Stage primaryStage;

    public StartPresenter(StartView startView, SnakesAndLadders snakesAndLadders, Stage primaryStage){
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
        //fullscreen
        view.getTbtnFullscreen().setOnAction(event -> {
            if (view.getTbtnFullscreen().isSelected()) {
                primaryStage.setFullScreen(true);

            } else {
                primaryStage.setFullScreen(false);

            }
        });
        view.getBtnLoadGame().setOnAction(event -> {

                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(Paths.get(".").toFile());
                fc.setTitle("Select your savefile");
                File saveFile = fc.showOpenDialog(primaryStage);

                if (saveFile != null){ //Sees is no file is selected
                    //TODO: If file is selected, throws nullpointer :D
                    model.getBoardScan().readFile(saveFile);
                }

                //Start game met gelezen files
        });
    }

    private void updateView() {
    }
}
