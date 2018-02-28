package be.kdg.SnakesAndLadders.view.Start;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Game.GameView;
import be.kdg.SnakesAndLadders.view.Setup.SetupPresenter;
import be.kdg.SnakesAndLadders.view.Setup.SetupView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Ruben Vanloo
 * 28/02/2018.
 */
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
        view.getBtnNewGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                SetupView setupView = new SetupView();
                GameView gameView = new GameView();
                SetupPresenter setupPresenter = new SetupPresenter(model, gameView, setupView, primaryStage, setupView.getScene(), view.getScene());
                view.getScene().setRoot(setupView);
            }
        });
        //exit button without warning
        view.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        //fullscreen
        view.getTbtnFullscreen().setOnAction(event -> {
            if (view.getTbtnFullscreen().isSelected()) {
                primaryStage.setFullScreen(true);

            } else {
                primaryStage.setFullScreen(false);

            }
        });
        //TODO: Marnix do your magic tric and set game load logic here pls
        view.getBtnLoadGame().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }

    private void updateView() {
    }
}
