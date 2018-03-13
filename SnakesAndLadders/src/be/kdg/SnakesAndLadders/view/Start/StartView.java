package be.kdg.SnakesAndLadders.view.Start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;


/**
 * View that functions as a main menu for the gain, containing some fun animations and 2 main buttons, for starting a new game or
 *
 * @author Ruben Vanloo
 */
public class StartView extends BorderPane {
    private Button btnNewGame;
    private ImageView IvSnake;
    private Button btnLoadGame;
    private ImageView IvLeftSnake;
    private ImageView IvRightSnake;
    private ImageView IvRightSnake2;
    private ImageView IvLeftSnake2;
    private Button btnExit;
    private Button btnHelp;
    private Label lblName;
    private Text tAbout;
    private Border border;

    private VBox buttons;
    private VBox text;

    private HBox helpAndExit;
    private HBox newGame;
    private HBox loadGame;

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
        btnHelp = new Button("Help");

        buttons = new VBox();
        text = new VBox();

        helpAndExit = new HBox();
        newGame = new HBox();
        loadGame = new HBox();

        border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

        lblName = new Label("Welcome to Snakes and Ladders!");

        tAbout = new Text("" +
                "This application was created \n" +
                "by Marnix Wyns and Ruben Vanloo.\n" +
                "Both students at KdG, \n" +
                "located in Antwerpen, Belgium.");


        IvSnake = new ImageView(new Image("GIF/Snake.gif"));
        IvRightSnake = new ImageView(new Image("GIF/blueLookingRight.gif"));
        IvLeftSnake = new ImageView(new Image("GIF/blueLookingLeft.gif"));
        IvRightSnake2 = new ImageView(new Image("GIF/blueLookingRight.gif"));
        IvLeftSnake2 = new ImageView(new Image("GIF/blueLookingLeft.gif"));


        //adjust background:
        background = new Image("/BackgroundImages/background.png");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false, false, true, false);
        backGround1 = new Background(new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize1));
        setBackground(backGround1);
    }

    private void layoutNodes() {

        btnExit.setPrefSize(60, 10);
        btnHelp.setPrefSize(60,10);
        helpAndExit.getChildren().add(btnHelp);
        helpAndExit.getChildren().add(btnExit);
        helpAndExit.setSpacing(10);
        setMargin(helpAndExit, new Insets(15, 15, 13, 900));
        setBottom(helpAndExit);


        //add buttons to gridpane and change their layout
        buttons.setSpacing(25);
        btnNewGame.setPrefSize(200,50);
        btnLoadGame.setPrefSize(200,50);
        btnNewGame.setFont(new Font(20));
        btnLoadGame.setFont(new Font(20));

        IvLeftSnake.setFitWidth(60);
        IvLeftSnake.setFitHeight(60);
        IvRightSnake.setFitWidth(60);
        IvRightSnake.setFitHeight(60);
        IvLeftSnake2.setFitWidth(60);
        IvLeftSnake2.setFitHeight(60);
        IvRightSnake2.setFitWidth(60);
        IvRightSnake2.setFitHeight(60);
        loadGame.setSpacing(50);
        loadGame.getChildren().addAll(IvRightSnake2, btnLoadGame, IvLeftSnake2);
        newGame.setSpacing(50);
        newGame.getChildren().addAll(IvRightSnake, btnNewGame, IvLeftSnake);


        buttons.getChildren().addAll(lblName, newGame,loadGame);
        setLeft(buttons);
        buttons.setAlignment(Pos.CENTER);
        setMargin(buttons, new Insets(0,0,0,90));



        lblName.setFont(Font.font("Arial", 30));
        lblName.setPadding(new Insets(0,0,25,0));


        //adding the text and gif to a vbox and align it on the right side of the screen
        text.getChildren().addAll(IvSnake, tAbout);
        text.setSpacing(40);
        text.setPadding(new Insets(60,10,0,10));
        tAbout.setFont(Font.font("Arial", FontPosture.ITALIC,20));
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

    public Button getBtnHelp() {
        return btnHelp;
    }
}
