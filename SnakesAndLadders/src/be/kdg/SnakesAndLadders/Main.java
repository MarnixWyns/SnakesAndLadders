package be.kdg.SnakesAndLadders;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.GameView;
import be.kdg.SnakesAndLadders.view.Presenter;
import be.kdg.SnakesAndLadders.view.SetupView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SnakesAndLadders model = new SnakesAndLadders();
        GameView gameView = new GameView();
        SetupView setupView = new SetupView();

        Presenter presenter = new Presenter(model,gameView,setupView);

        Scene setupScene = new Scene(setupView);
        primaryStage.setScene(setupScene);
        primaryStage.setTitle("Snakes And Ladders");

        primaryStage.setMinHeight(576);
        primaryStage.setMinWidth(1024);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
