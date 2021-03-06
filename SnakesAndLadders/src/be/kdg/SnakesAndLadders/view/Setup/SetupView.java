package be.kdg.SnakesAndLadders.view.Setup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SetupView extends BorderPane {
    private Image setupBackgroundImage;

    private Button btnStartGame;
    private Button btnExitGame;
    private Button btnHelp;
    private Button btnHome;

    private TextField tfP1name;
    private TextField tfP2name;
    private TextField tfP3name;
    private TextField tfP4name;

    private ImageView ivPlayer1;
    private ImageView ivPlayer2;
    private ImageView ivPlayer3;
    private ImageView ivPlayer4;

    private ImageView ivRed;
    private ImageView ivBlue;
    private ImageView ivGreen;
    private ImageView ivYellow;

    private Image red;
    private Image blue;
    private Image green;
    private Image yellow;

    private Label name;
    private Label lblPlayer1;
    private Label lblPlayer2;
    private Label lblPlayer3;
    private Label lblPlayer4;
    private Label lblSelectPlayers;
    private Label lblSelectDifficulty;
    private Label lblPreviewInfo;
    private Label lblInfoTitle;

    private Label empty;
    private Label color;
    private ObservableList<String> colorOptions;
    private ComboBox colorPickerP1;
    private ComboBox colorPickerP2;
    private ComboBox colorPickerP3;
    private ComboBox colorPickerP4;

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


    //region Number of players
    //ToggleGroup, user can choose only one option at a time (radiobutton)
    private ToggleGroup playersToggle;

    //Items to put in numberOfPlayersMenu as possible options for number of players
    private RadioButton onePlayer;
    private RadioButton twoPlayers;
    private RadioButton threePlayers;
    private RadioButton fourPlayers;
    //endregion

    //region Difficulty
    //ToggleGroup, only one difficulty can be played at a time
    private ToggleGroup difficultyToggle;

    //Items to put in difficultyToggle as possible difficulties
    private RadioButton easyDifficulty;
    private RadioButton normalDifficulty;
    private RadioButton hardDifficulty;
    //endregion

    //create grids using Gridpane
    private GridPane boardGrid;
    private GridPane playerDifficuty;
    private GridPane nameAndColors;
    private GridPane setupMenu;
    private GridPane setupButtons;
    private GridPane startButton;
    private GridPane pawnPane;
    private GridPane boardBackground;

    //create VBox to display player buttons
    private VBox playerButtons;
    private VBox playerNames;
    private VBox playerColors;
    private VBox difficulty;
    private VBox playerNameFields;
    private VBox boardAndPreview;
    private HBox helpAndExit;

    //background variables creation
    private BackgroundSize backgroundSize1;
    private BackgroundSize backgroundBoard;
    private Background backGround1;
    private Background easy;
    private Background normal;
    private Background hard;

    private Border border;


    public SetupView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        btnStartGame = new Button("Start");
        btnExitGame = new Button("Exit");
        btnHelp = new Button("Help");
        btnHome = new Button("Home");


        tfP1name = new TextField();
        tfP2name = new TextField();
        tfP3name = new TextField();
        tfP4name = new TextField();


        name = new Label("Name: (Max 10 Chars)");
        lblPlayer1 = new Label("Player 1: ");
        lblPlayer2 = new Label("Player 2: ");
        lblPlayer3 = new Label("Player 3: ");
        lblPlayer4 = new Label("Player 4: ");
        lblSelectPlayers = new Label("Select number of players");
        lblSelectDifficulty = new Label("Select Difficulty");
        lblPreviewInfo = new Label("Normal difficulty: Snakes and ladders are evenly distributed.");
        lblInfoTitle = new Label("INFORMATION:");

        empty = new Label("");

        color = new Label("Color:");

        //combobox initialisatie met ineens de 4 opties meegegeven
        colorOptions = FXCollections.observableArrayList("Yellow", "Green", "Blue", "Red");
        colorPickerP1 = new ComboBox(colorOptions);
        colorPickerP2 = new ComboBox(colorOptions);
        colorPickerP3 = new ComboBox(colorOptions);
        colorPickerP4 = new ComboBox(colorOptions);


        colorPickerP1.getSelectionModel().select(0);
        colorPickerP2.getSelectionModel().select(1);
        colorPickerP3.getSelectionModel().select(2);
        colorPickerP4.getSelectionModel().select(3);


        //region Init Ammount Players RadioButtonGrouop
        onePlayer = new RadioButton("1 Player");
        twoPlayers = new RadioButton("2 Players");
        threePlayers = new RadioButton("3 Players");
        fourPlayers = new RadioButton("4 Players");

        playersToggle = new ToggleGroup();
        playersToggle.getToggles().addAll(onePlayer, twoPlayers, threePlayers, fourPlayers);
        //endregion


        //region Init Difficulty RadioButtonGroup
        easyDifficulty = new RadioButton("Easy");
        normalDifficulty = new RadioButton("Normal");
        hardDifficulty = new RadioButton("Hard");

        difficultyToggle = new ToggleGroup();
        difficultyToggle.getToggles().addAll(easyDifficulty, normalDifficulty, hardDifficulty);
        //endregion

        //Gridpane initialisation
        boardGrid = new GridPane();
        playerDifficuty = new GridPane();
        nameAndColors = new GridPane();
        setupMenu = new GridPane();
        setupButtons = new GridPane();
        startButton = new GridPane();
        pawnPane = new GridPane();
        boardBackground = new GridPane();


        //VBox initialiseren
        playerButtons = new VBox();
        playerNames = new VBox();
        playerColors = new VBox();
        difficulty = new VBox();
        playerNameFields = new VBox();
        boardAndPreview = new VBox();
        helpAndExit = new HBox();

        //boardgrid initialiseren
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

        //background aanpassen:
        setupBackgroundImage = new Image("BackgroundImages/background.png");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
        backGround1 = new Background(new BackgroundImage(setupBackgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize1));
        setBackground(backGround1);

        backgroundBoard = new BackgroundSize(400, 400, false, false, true, false);

        border = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));

        red = new Image("PawnImages/red.png");
        blue = new Image("PawnImages/blue.png");
        green = new Image("PawnImages/green.png");
        yellow = new Image("PawnImages/yellow.png");


        //pawns initialisation
        ivRed = new ImageView(red);
        ivBlue = new ImageView(blue);
        ivGreen = new ImageView(green);
        ivYellow = new ImageView(yellow);

        ivPlayer1 = ivYellow;
        ivPlayer2 = ivGreen;
        ivPlayer3 = ivBlue;
        ivPlayer4 = ivRed;

        easy = new Background(new BackgroundImage(new Image("BackgroundImages/easy.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundBoard));
        normal = new Background(new BackgroundImage(new Image("BackgroundImages/normal.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundBoard));
        hard = new Background(new BackgroundImage(new Image("BackgroundImages/hard.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundBoard));
    }


    private void layoutNodes() {

        //boardgrid layout
        boardGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);
        boardGrid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10);
        AnchorPane.setBottomAnchor(boardGrid, 0.0);
        AnchorPane.setTopAnchor(boardGrid, 0.0);
        AnchorPane.setLeftAnchor(boardGrid, 0.0);
        AnchorPane.setRightAnchor(boardGrid, 0.0);
        boardGrid.setGridLinesVisible(true);
        boardGrid.setBackground(normal);
        boardBackground.add(boardGrid, 0, 0);
        boardAndPreview.setPadding(new Insets(30, 0, 30, 90));
        boardAndPreview.getChildren().add(boardGrid);
        boardAndPreview.getChildren().add(lblInfoTitle);
        lblInfoTitle.setFont(new Font(30));
        boardAndPreview.getChildren().add(lblPreviewInfo);
        lblPreviewInfo.setFont(new Font(15));
        setLeft(boardAndPreview);

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
        pawnPane.add(ivPlayer1, 0, 0);
        pawnPane.add(ivPlayer2, 1, 0);
        pawnPane.add(ivPlayer3, 0, 1);
        pawnPane.add(ivPlayer4, 1, 1);
        pawnPane.setHgap(3);
        pawnPane.setVgap(3);
        boardGrid.add(pawnPane, 0, 9);
        pawnPane.setPadding(new Insets(3, 0, 0, 3));


        //VBox setup
        playerButtons.setSpacing(10);
        playerButtons.setPadding(new Insets(10));
        playerButtons.getChildren().addAll(lblSelectPlayers, onePlayer, twoPlayers, threePlayers, fourPlayers);

        difficulty.setSpacing(10);
        difficulty.setPadding(new Insets(10));
        difficulty.getChildren().addAll(lblSelectDifficulty, easyDifficulty, normalDifficulty, hardDifficulty);

        playerNames.setSpacing(17);
        playerNames.setPadding(new Insets(10));
        playerNames.getChildren().addAll(empty, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4);

        playerNameFields.setSpacing(10);
        playerNameFields.setPadding(new Insets(10));
        playerNameFields.getChildren().addAll(name, tfP1name, tfP2name, tfP3name, tfP4name);

        playerColors.setSpacing(10);
        playerColors.setPadding(new Insets(10));
        playerColors.getChildren().addAll(color, colorPickerP1, colorPickerP2, colorPickerP3, colorPickerP4);

        //VBox placement in grids
        playerDifficuty.add(playerButtons, 0, 0);
        playerDifficuty.add(difficulty, 1, 0);
        //setRight(playerDifficuty);

        nameAndColors.add(playerNames, 0, 0);
        nameAndColors.add(playerNameFields, 1, 0);
        nameAndColors.add(playerColors, 2, 0);
        //setCenter(nameAndColors);

        //setCenter(setupButtons);
        btnStartGame.setPrefSize(150, 75);
        startButton.add(btnStartGame, 0, 0);
        btnStartGame.setFont(new Font(25));

        //gridpanes samenvoegen in een gridpane om het setupmenu te creëeren
        setupMenu.add(playerDifficuty, 0, 0);
        setupMenu.add(nameAndColors, 0, 1);
        setupMenu.add(startButton, 0, 2);
        startButton.setPadding(new Insets(20, 100, 20, 100));
        nameAndColors.setPadding(new Insets(20, 0, 0, 0));
        playerDifficuty.setPadding(new Insets(10, 0, 0, 0));
        setRight(setupMenu);
        setAlignment(setupMenu, Pos.CENTER);

        //Default Selections of toggleGroups
        fourPlayers.setSelected(true);
        //normalDifficulty.setSelected(true);


        btnExitGame.setPrefSize(60, 10);
        btnHelp.setPrefSize(60, 10);
        btnHome.setPrefSize(60, 10);
        helpAndExit.getChildren().add(btnHome);
        helpAndExit.getChildren().add(btnHelp);
        helpAndExit.getChildren().add(btnExitGame);
        helpAndExit.setSpacing(10);
        setMargin(helpAndExit, new Insets(0, 15, 13, 800));
        setBottom(helpAndExit);

        setBorder(border);
    }

    Button getBtnStartGame() {
        return btnStartGame;
    }

    Button getBtnExitGame() {
        return btnExitGame;
    }

    RadioButton getOnePlayer() {
        return onePlayer;
    }

    RadioButton getTwoPlayers() {
        return twoPlayers;
    }

    RadioButton getThreePlayers() {
        return threePlayers;
    }

    RadioButton getFourPlayers() {
        return fourPlayers;
    }

    TextField getTfP1name() {
        return tfP1name;
    }

    TextField getTfP2name() {
        return tfP2name;
    }

    TextField getTfP3name() {
        return tfP3name;
    }

    TextField getTfP4name() {
        return tfP4name;
    }

    ComboBox getColorPickerP1() {
        return colorPickerP1;
    }

    ComboBox getColorPickerP2() {
        return colorPickerP2;
    }

    ComboBox getColorPickerP3() {
        return colorPickerP3;
    }

    ComboBox getColorPickerP4() {
        return colorPickerP4;
    }

    public ImageView getIvPlayer1() {
        return ivPlayer1;
    }

    ImageView getIvPlayer2() {
        return ivPlayer2;
    }

    ImageView getIvPlayer3() {
        return ivPlayer3;
    }

    ImageView getIvPlayer4() {
        return ivPlayer4;
    }

    RadioButton getEasyDifficulty() {
        return easyDifficulty;
    }

    RadioButton getNormalDifficulty() {
        return normalDifficulty;
    }

    RadioButton getHardDifficulty() {
        return hardDifficulty;
    }

    public Image getRed() {
        return red;
    }

    public Image getBlue() {
        return blue;
    }

    public Image getGreen() {
        return green;
    }

    public Image getYellow() {
        return yellow;
    }

    public GridPane getBoardGrid() {
        return boardGrid;
    }

    public Label getLblPreviewInfo() {
        return lblPreviewInfo;
    }

    public BackgroundSize getBackgroundBoard() {
        return backgroundBoard;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public Button getBtnHome() {
        return btnHome;
    }
}
