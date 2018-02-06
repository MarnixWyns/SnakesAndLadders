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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SnakesAndLadders model = new SnakesAndLadders();
        GameView gameView = new GameView();
        SetupView setupView = new SetupView();

        Scene gameScene = new Scene(gameView);
        Stage secondaryStage = new Stage();
        Scene setupScene = new Scene(setupView);

        Presenter presenter = new Presenter(model,gameView,setupView, primaryStage, gameScene, setupScene);

        secondaryStage.setScene(gameScene);
        secondaryStage.setTitle("GameView");
        secondaryStage.setResizable(false);

        primaryStage.setScene(setupScene);
        primaryStage.setTitle("SetupView");
        primaryStage.setResizable(false);

        primaryStage.setHeight(600);
        primaryStage.setWidth(1024);
        secondaryStage.setHeight(600);
        secondaryStage.setWidth(1024);

        secondaryStage.getIcons().add(new Image("/snakeandladder.png"));
        primaryStage.getIcons().add(new Image("/snakeandladder.png"));

        secondaryStage.show();
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
