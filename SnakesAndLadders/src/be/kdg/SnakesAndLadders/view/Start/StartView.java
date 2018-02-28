package be.kdg.SnakesAndLadders.view.Start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Ruben Vanloo
 * 28/02/2018.
 */
public class StartView extends BorderPane {
    private Button btnNewGame;
    private Button btnLoadGame;
    private Button btnExit;
    private ToggleButton tbtnFullscreen;
    private Label lblName;
    private Label lblIntro;
    private Label lblAbout;
    private Text tIntro;
    private Text tAbout;
    private Border border;

    private VBox buttons;
    private VBox text;

    private HBox fullAndExit;

    private Background backGround1;
    private BackgroundSize backgroundSize1;
    private Image background;



    public StartView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        btnNewGame = new Button("New Game");
        btnLoadGame = new Button("Load Game");
        btnExit = new Button("Exit");
        tbtnFullscreen = new ToggleButton("Fullscreen");

        buttons = new VBox();
        text = new VBox();

        fullAndExit = new HBox();

        border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

        lblName = new Label("Welcome to Snakes and Ladders!");
        lblIntro = new Label("Introduction:");
        lblAbout = new Label("About us:");

        tAbout = new Text("" +
                "This application was created \n" +
                "by Marnix Wyns and Ruben Vanloo.\n" +
                "Both students at KdG, \n" +
                "located in Antwerpen, Belgium.");
        tIntro = new Text("" +
                "Snakes and Ladders is a fun \n" +
                "game for young and old! \n" +
                "The rules are simple:\n" +
                "Step on the head of a snake\n" +
                "and you fall down to it's tail!\n" +
                "But no worries, step on the \n" +
                "beginning of a ladder and \n" +
                "it will lift you up to where\n" +
                "it ends!");

        //adjust background:
        background = new Image("/BackgroundImages/background.png");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false, false, true, false);
        backGround1 = new Background(new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize1));
        setBackground(backGround1);
    }

    private void layoutNodes() {

        //fullscreen and exit placement in startscreen
        fullAndExit.setSpacing(5);
        fullAndExit.getChildren().addAll(tbtnFullscreen, btnExit);
        setBottom(fullAndExit);
        setMargin(fullAndExit, new Insets(15));
        fullAndExit.setAlignment(Pos.BOTTOM_RIGHT);

        //add buttons to gridpane and change their layout
        buttons.setSpacing(25);
        btnNewGame.setPrefSize(200,50);
        btnLoadGame.setPrefSize(200,50);
        btnNewGame.setFont(new Font(20));
        btnLoadGame.setFont(new Font(20));
        buttons.getChildren().addAll(lblName, btnNewGame,btnLoadGame);
        setLeft(buttons);
        buttons.setAlignment(Pos.CENTER);
        setMargin(buttons, new Insets(0,0,0,90));

        lblName.setFont(Font.font("Arial", 30));
        lblName.setPadding(new Insets(0,0,25,0));


        //adding the text and labels to a vbox and align it on the right side of the screen
        text.getChildren().addAll(lblIntro, tIntro, lblAbout, tAbout);
        text.setSpacing(20);
        text.setPadding(new Insets(50,10,0,10));
        tIntro.setFont(Font.font("Arial", FontPosture.ITALIC, 20));
        tAbout.setFont(Font.font("Arial", FontPosture.ITALIC,20));
        lblIntro.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        lblAbout.setFont(Font.font("Arial", FontWeight.BOLD,  25));
        setRight(text);

        setBorder(border);
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Button getBtnLoadGame() {
        return btnLoadGame;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public ToggleButton getTbtnFullscreen() {
        return tbtnFullscreen;
    }
}
