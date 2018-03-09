package be.kdg.SnakesAndLadders;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;
import be.kdg.SnakesAndLadders.view.Start.StartPresenter;
import be.kdg.SnakesAndLadders.view.Start.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SnakesAndLadders model = new SnakesAndLadders();
        StartView startView = new StartView();

        Scene startScene = new Scene(startView);
        //startScene.getStylesheets().add("CSS-files/fullscreen.css");
        StartPresenter startPresenter = new StartPresenter(startView,model,primaryStage);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Snakes and Ladders");
        primaryStage.setResizable(false);

        primaryStage.setHeight(600);
        primaryStage.setWidth(1024);
        primaryStage.getIcons().add(new Image("/icon.png"));


        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
