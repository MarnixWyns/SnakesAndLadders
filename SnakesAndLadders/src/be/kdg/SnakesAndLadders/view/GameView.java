package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import com.sun.prism.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class GameView extends GridPane {
    private ImageView ivBackground;
    private ImageView ivDice;
    private ImageView ivContextBackground;

    private Label lblCurrentPlayer;

    private Button btnRollDice;
    private Button btnExit;
    private ToggleButton tbtnFullscreen;


    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        ivDice = new ImageView(new Image("")); //TODO: Add image path
        ivContextBackground = new ImageView(); //TODO: Make contextbackground flat, neutral color

        btnRollDice = new Button("Roll the dice!");
        btnExit = new Button("Exit");
        tbtnFullscreen = new ToggleButton("Fullscreen");
    }

    private void layoutNodes() {


    }



}
