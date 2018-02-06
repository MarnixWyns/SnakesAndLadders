package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class GameView extends BorderPane {

    private ImageView ivDice;

    //Background
    private Image backgroundGame;

    private ImageView ivPlayer1;
    private ImageView ivPlayer2;
    private ImageView ivPlayer3;
    private ImageView ivPlayer4;

    private Button btnRollDice;
    private Button btnExit;
    private ToggleButton tbtnFullscreen;

    private final String DIEURL = "/DieImages/die";

    private ColumnConstraints column1;
    private ColumnConstraints column2;
    private ColumnConstraints column3;
    private ColumnConstraints column4;
    private ColumnConstraints column5;
    private ColumnConstraints column6;
    private ColumnConstraints column7;
    private ColumnConstraints column8;
    private ColumnConstraints column9;
    private ColumnConstraints column10;

    private RowConstraints row1;
    private RowConstraints row2;
    private RowConstraints row3;
    private RowConstraints row4;
    private RowConstraints row5;
    private RowConstraints row6;
    private RowConstraints row7;
    private RowConstraints row8;
    private RowConstraints row9;
    private RowConstraints row10;

    private Label lblplayerName;
    private Label lblturnMessage;
    private Label lblFeedback;

    private VBox playerMessage;
    private VBox dicePosition;
    private VBox gridFeedback;


    //create grids using Gridpane
    private GridPane boardGrid;
    private GridPane currentPlayerDice;
    private GridPane otherButtons;
    private GridPane pawnPane;

    //background variables creation
    BackgroundSize backgroundSize1;
    Background backGround1;


    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        ivDice = new ImageView(new Image("DieImages/die6.png")); //TODO: Add image path

        btnRollDice = new Button("Roll the dice!");
        btnExit = new Button("Exit");
        tbtnFullscreen = new ToggleButton("Fullscreen");

        //boardgrid initialiserenµ
        column1 = new ColumnConstraints(40);
        column2 = new ColumnConstraints(40);
        column3 = new ColumnConstraints(40);
        column4 = new ColumnConstraints(40);
        column5 = new ColumnConstraints(40);
        column6 = new ColumnConstraints(40);
        column7 = new ColumnConstraints(40);
        column8 = new ColumnConstraints(40);
        column9 = new ColumnConstraints(40);
        column10 = new ColumnConstraints(40);

        row1 = new RowConstraints(40);
        row2 = new RowConstraints(40);
        row3 = new RowConstraints(40);
        row4 = new RowConstraints(40);
        row5 = new RowConstraints(40);
        row6 = new RowConstraints(40);
        row7 = new RowConstraints(40);
        row8 = new RowConstraints(40);
        row9 = new RowConstraints(40);
        row10 = new RowConstraints(40);

        //Gridpane initialisation
        boardGrid = new GridPane();
        currentPlayerDice = new GridPane();
        otherButtons = new GridPane();
        pawnPane = new GridPane();

        lblplayerName = new Label("Player");
        lblturnMessage = new Label("It's your turn!!");
        lblFeedback = new Label("Roll the damn dice!");

        //VBoxes initialiseren
        playerMessage = new VBox();
        dicePosition = new VBox();
        gridFeedback = new VBox();

        //achtergrond veranderen
        backgroundGame = new Image("/BackgroundImages/background6.jpg");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false, false, true, false);
        backGround1 = new Background(new BackgroundImage(backgroundGame,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize1));
        setBackground(backGround1);

        //pawns initialisation
        ivPlayer1 = new ImageView(new Image("PawnImages/red.png"));
        ivPlayer2 = new ImageView(new Image("PawnImages/blue.png"));
        ivPlayer3 = new ImageView(new Image("PawnImages/green.png"));
        ivPlayer4 = new ImageView(new Image("PawnImages/yellow.png"));

    }

    private void layoutNodes() {

        //boardgrid layout
        boardGrid.getColumnConstraints().addAll(column1,column2,column3,column4,column5,column6,column7,column8,column9,column10);
        boardGrid.getRowConstraints().addAll(row1,row2,row3,row4,row5,row6,row7,row8,row9,row10);
        boardGrid.setGridLinesVisible(true);
        boardGrid.setPadding(new Insets(30,0,30,90));

        //changing pawns to acceptable size
        ivPlayer1.setFitHeight(15);
        ivPlayer1.setFitWidth(15);
        ivPlayer2.setFitHeight(15);
        ivPlayer2.setFitWidth(15);
        ivPlayer3.setFitHeight(15);
        ivPlayer3.setFitWidth(15);
        ivPlayer4.setFitHeight(15);
        ivPlayer4.setFitWidth(15);

        //Putting pawns in the game at start position
        pawnPane.add(ivPlayer1,0,0);
        pawnPane.add(ivPlayer2,1,0);
        pawnPane.add(ivPlayer3,0,1);
        pawnPane.add(ivPlayer4, 1,1);
        pawnPane.setHgap(3);
        pawnPane.setVgap(3);
        boardGrid.add(pawnPane,0,9);
        pawnPane.setPadding(new Insets(3,0,0,3));


        //VBox layout
        gridFeedback.setSpacing(10);
        gridFeedback.getChildren().addAll(boardGrid,lblFeedback);
        lblFeedback.setPadding(new Insets(10,50,0,150));
        lblFeedback.setFont(new Font(30));
        setLeft(gridFeedback);

        playerMessage.setSpacing(10);
        playerMessage.setPadding(new Insets(50,200,0,0));
        playerMessage.getChildren().addAll(lblplayerName,lblturnMessage);
        lblplayerName.setPadding(new Insets(0,0,0,5));
        lblplayerName.setFont(new Font(50));
        lblturnMessage.setFont(new Font(25));

        dicePosition.setSpacing(10);
        dicePosition.setPadding(new Insets(20,100,20,0));
        dicePosition.getChildren().addAll(ivDice, btnRollDice);
        ivDice.setFitHeight(150);
        ivDice.setFitWidth(150);
        btnRollDice.setPrefWidth(150);
        btnRollDice.setPrefHeight(50);
        btnRollDice.setFont(new Font(20));

        //gridpane voor exit en fullscreenbuttons
        btnExit.setPrefSize(40, 10);
        tbtnFullscreen.setPrefSize(75, 10);
        otherButtons.add(btnExit, 2, 2);
        otherButtons.add(tbtnFullscreen, 1, 2);
        setCenter(otherButtons);

        //Vbox placement in gridpane
        currentPlayerDice.add(playerMessage,0,0);
        currentPlayerDice.add(dicePosition,0,1);
        currentPlayerDice.add(otherButtons,0,2);
        setRight(currentPlayerDice);
        otherButtons.setPadding(new Insets(85,0,0,235));

    }

    Button getBtnRollDice() {
        return btnRollDice;
    }

    ImageView getIvDice(){
        return ivDice;
    }

    String getDIEURL(){
        return DIEURL;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public ToggleButton getTbtnFullscreen() {
        return tbtnFullscreen;
    }
}
