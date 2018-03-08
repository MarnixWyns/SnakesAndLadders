package be.kdg.SnakesAndLadders.view.Game;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class GameView extends BorderPane {

    private ImageView ivDice;

    //Background
    private Image backgroundGame;

    private Border border;

    private ImageView ivRed;
    private ImageView ivBlue;
    private ImageView ivGreen;
    private ImageView ivYellow;
    private ImageView ivBlack;

    private ImageView ivPlayer1;
    private ImageView ivPlayer2;
    private ImageView ivPlayer3;
    private ImageView ivPlayer4;

    private Button btnRollDice;
    private Button btnExit;
    private Button btnHome;
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
    private VBox gameButtons;
    private HBox fullAndExit;


    //create grids using Gridpane
    private GridPane boardGrid;
    private GridPane currentPlayerDice;
    private GridPane otherButtons;
    private GridPane pawnPane;
    private GridPane boardBackground;
    private GridPane newPawnPane;
    private GridPane pawnPane1;
    private GridPane pawnPane2;
    private GridPane pawnPane3;
    private GridPane pawnPane4;
    private BorderPane pawnBorderPane;

    //background variables creation
    BackgroundSize backgroundSize1;
    BackgroundSize backgroundBoard;
    Background backGround1;



    public GameView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        ivDice = new ImageView(new Image("DieImages/die6.png"));

        border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

        btnRollDice = new Button("Roll the dice!");
        btnExit = new Button("Exit");
        btnHome = new Button("Home");
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
        boardBackground = new GridPane();
        newPawnPane = new GridPane();
        pawnPane1 = new GridPane();
        pawnPane2 = new GridPane();
        pawnPane3 = new GridPane();
        pawnPane4 = new GridPane();

        lblplayerName = new Label("Player");
        lblturnMessage = new Label("It's your turn!!");
        lblFeedback = new Label("Roll the damn dice!");

        //VBoxes initialiseren
        playerMessage = new VBox();
        dicePosition = new VBox();
        gridFeedback = new VBox();
        gameButtons = new VBox();
        fullAndExit = new HBox();

        //achtergrond veranderen
        backgroundGame = new Image("/BackgroundImages/background.png");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false, false, true, false);
        backGround1 = new Background(new BackgroundImage(backgroundGame,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize1));
        setBackground(backGround1);

        backgroundBoard = new BackgroundSize(400,400, false, false, true, false);


        //pawns initialisation

        ivRed = new ImageView(new Image("PawnImages/red.png"));
        ivBlue = new ImageView(new Image("PawnImages/blue.png"));
        ivGreen = new ImageView(new Image("PawnImages/green.png"));
        ivYellow = new ImageView(new Image("PawnImages/yellow.png"));
        ivBlack = new ImageView(new Image("PawnImages/black.png"));

        ivPlayer1 = ivYellow;
        ivPlayer2 = ivGreen;
        ivPlayer3 = ivBlue;
        ivPlayer4 = ivRed;

        setBorder(border);

    }

    private void layoutNodes() {

        //boardgrid layout
        boardGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);
        boardGrid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10);
        boardGrid.setGridLinesVisible(true);
        boardGrid.setBackground(new Background(new BackgroundImage(new Image("BackgroundImages/normal.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundBoard)));
        boardBackground.add(boardGrid,0,0);
        boardBackground.setPadding(new Insets(30, 0, 30, 90));


        //changing pawns to acceptable size
        ivRed.setFitHeight(15);
        ivRed.setFitWidth(15);
        ivBlue.setFitHeight(15);
        ivBlue.setFitWidth(15);
        ivGreen.setFitHeight(15);
        ivGreen.setFitWidth(15);
        ivYellow.setFitHeight(15);
        ivYellow.setFitWidth(15);
        ivBlack.setFitHeight(15);
        ivBlack.setFitWidth(15);

        //Putting pawns in the game at start position

        pawnPane.add(ivPlayer1,0,0);
        pawnPane.add(ivPlayer2,1,0);
        pawnPane.add(ivPlayer3,0,1);
        pawnPane.add(ivPlayer4, 1,1);
        pawnPane.setHgap(3);
        pawnPane.setVgap(3);
        boardGrid.add(pawnPane,0,9);
        pawnPane.setPadding(new Insets(3,0,0,3));
        boardGrid.getStyleClass().add("boardgrid1");


        //VBox layout
        gridFeedback.setSpacing(10);
        lblFeedback.setPadding(new Insets(10,50,0,150));
        lblFeedback.setFont(new Font(30));
        gridFeedback.getChildren().addAll(boardBackground,lblFeedback);
        setLeft(gridFeedback);


        /*
        //TODO: place playername in center of label
        playerMessage.setSpacing(10);
        playerMessage.setPadding(new Insets(50,0,0,50));
        playerMessage.getChildren().addAll(lblplayerName,lblturnMessage);
        lblplayerName.setPadding(new Insets(0,0,0,0));
        lblplayerName.setFont(new Font(50));
        lblturnMessage.setFont(new Font(25));
        lblturnMessage.setPadding(new Insets(0,0,0,25));
        playerMessage.setPrefWidth(400);
        */
        //TODO: Ruben set alignment of VBox to center if fullscreen is pressed, yeah Ruben, do this
        gameButtons.setSpacing(10);
        gameButtons.setPadding(new Insets(50,0,0,50));
        gameButtons.getChildren().addAll(lblplayerName, lblturnMessage, ivDice, btnRollDice);
        lblplayerName.setFont(new Font(50));
        lblturnMessage.setFont(new Font(25));
        ivDice.setFitHeight(200);
        ivDice.setFitWidth(200);
        lblturnMessage.setPadding(new Insets(0,0,40,0));
        btnRollDice.setPrefWidth(150);
        btnRollDice.setPrefHeight(50);
        btnRollDice.setFont(new Font(20));
        setRight(gameButtons);
        setMargin(gameButtons, new Insets(0,90,0,0));
        gameButtons.setAlignment(Pos.CENTER);
        gameButtons.setPrefWidth(300);

        fullAndExit.setSpacing(5);
        fullAndExit.getChildren().addAll(tbtnFullscreen,btnHome, btnExit);
        setBottom(fullAndExit);
        setMargin(fullAndExit, new Insets(15));
        fullAndExit.setAlignment(Pos.BOTTOM_RIGHT);



        /*
        dicePosition.setSpacing(10);
        dicePosition.setPadding(new Insets(20,100,20,75));
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
        */

    }

    private class Animation extends Group {
        GameView gameView = new GameView();
        private ImageView Ivplayer1;
        private ImageView Ivplayer2;
        private ImageView Ivplayer3;
        private ImageView Ivplayer4;

        public Animation(){
            this.initialiseNodes();
            this.layoutNodes();
            this.animate();
        }

        private void initialiseNodes() {
            this.Ivplayer1 = gameView.getIvPlayer1();
            this.Ivplayer2 = gameView.getIvPlayer2();
            this.Ivplayer3 = gameView.getIvPlayer3();
            this.Ivplayer4 = gameView.getIvPlayer4();

        }

        private void layoutNodes() {
            this.getChildren().add(Ivplayer1);
            this.getChildren().add(Ivplayer2);
            this.getChildren().add(Ivplayer3);
            this.getChildren().add(Ivplayer4);
        }

        private void animate() {

        }
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

    Button getBtnExit() {
        return btnExit;
    }

    ToggleButton getTbtnFullscreen() {
        return tbtnFullscreen;
    }

    Label getLblplayerName(){
        return lblplayerName;
    }

    public ImageView getIvPlayer1() {
        return ivPlayer1;
    }

    public ImageView getIvPlayer2() {
        return ivPlayer2;
    }

    public ImageView getIvPlayer3() {
        return ivPlayer3;
    }

    public ImageView getIvPlayer4() {
        return ivPlayer4;
    }

    public GridPane getPawnPane() {
        return pawnPane;
    }

    public GridPane getBoardGrid() {
        return boardGrid;
    }

    public Label getLblFeedback() {
        return lblFeedback;
    }

    public void setPawnPane(GridPane pawnPane) {
        this.pawnPane = pawnPane;
    }

    public VBox getGameButtons() {
        return gameButtons;
    }

    public GridPane getBoardBackground() {
        return boardBackground;
    }

    public Button getBtnHome() {
        return btnHome;
    }

    public GridPane getNewPawnPane() {
        return newPawnPane;
    }

    public GridPane getPawnPane1() {
        return pawnPane1;
    }

    public GridPane getPawnPane2() {
        return pawnPane2;
    }

    public GridPane getPawnPane3() {
        return pawnPane3;
    }

    public GridPane getPawnPane4() {
        return pawnPane4;
    }

    public ImageView getIvBlack() {
        return ivBlack;
    }

}
