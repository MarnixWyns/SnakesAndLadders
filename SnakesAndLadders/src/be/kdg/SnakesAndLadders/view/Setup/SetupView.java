package be.kdg.SnakesAndLadders.view.Setup;/*
 * Marnix Wyns
 * 2/02/2018
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;


public class SetupView extends BorderPane {
    private Image setupBackgroundImage;

    private Button btnStartGame;
    private Button btnExitGame;
    private ToggleButton tbtnFullScreen;

    private TextField tfP1name;
    private TextField tfP2name;
    private TextField tfP3name;
    private TextField tfP4name;

    private ImageView ivPlayer1;
    private ImageView ivPlayer2;
    private ImageView ivPlayer3;
    private ImageView ivPlayer4;

    private Label name;
    private Label lblPlayer1;
    private Label lblPlayer2;
    private Label lblPlayer3;
    private Label lblPlayer4;
    private Label lblSelectPlayers;
    private Label lblSelectDifficulty;

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

    //create VBox to display player buttons
    private VBox playerButtons;
    private VBox playerNames;
    private VBox playerColors;
    private VBox difficulty;
    private VBox playerNameFields;

    //background variables creation
    BackgroundSize backgroundSize1;
    Background backGround1;



    public SetupView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        btnStartGame = new Button("Start");
        btnExitGame = new Button("Exit");
        tbtnFullScreen = new ToggleButton("Fullscreen");


        tfP1name = new TextField();
        tfP2name = new TextField();
        tfP3name = new TextField();
        tfP4name = new TextField();

        name = new Label("Name:");
        lblPlayer1 = new Label("Player 1: ");
        lblPlayer2 = new Label("Player 2: ");
        lblPlayer3 = new Label("Player 3: ");
        lblPlayer4 = new Label("Player 4: ");
        lblSelectPlayers = new Label("Select number of players");
        lblSelectDifficulty = new Label("Select Difficulty");

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


        //VBox initialiseren
        playerButtons = new VBox();
        playerNames = new VBox();
        playerColors = new VBox();
        difficulty = new VBox();
        playerNameFields = new VBox();

        //boardgrid initialiseren
        column1 = new ColumnConstraints(38);
        column2 = new ColumnConstraints(38);
        column3 = new ColumnConstraints(38);
        column4 = new ColumnConstraints(38);
        column5 = new ColumnConstraints(38);
        column6 = new ColumnConstraints(38);
        column7 = new ColumnConstraints(38);
        column8 = new ColumnConstraints(38);
        column9 = new ColumnConstraints(38);
        column10 = new ColumnConstraints(38);

        row1 = new RowConstraints(38);
        row2 = new RowConstraints(38);
        row3 = new RowConstraints(38);
        row4 = new RowConstraints(38);
        row5 = new RowConstraints(38);
        row6 = new RowConstraints(38);
        row7 = new RowConstraints(38);
        row8 = new RowConstraints(38);
        row9 = new RowConstraints(38);
        row10 = new RowConstraints(38);

        //background aanpassen:
        setupBackgroundImage = new Image("BackgroundImages/background.png");
        backgroundSize1 = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false, false, true, false);
        backGround1 = new Background(new BackgroundImage(setupBackgroundImage,
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
        boardGrid.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);
        boardGrid.getRowConstraints().addAll(row1, row2, row3, row4, row5, row6, row7, row8, row9, row10);
        boardGrid.setGridLinesVisible(true);
        boardGrid.setPadding(new Insets(30, 0, 30, 90));
        setLeft(boardGrid);

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


        btnExitGame.setPrefSize(40, 10);
        tbtnFullScreen.setPrefSize(75, 10);
        setupButtons.add(btnExitGame, 2, 2);
        setupButtons.add(tbtnFullScreen, 1, 2);
        //setCenter(setupButtons);

        btnStartGame.setPrefSize(150, 75);
        startButton.add(btnStartGame, 0, 0);
        btnStartGame.setFont(new Font(25));

        //gridpanes samenvoegen in een gridpane om het setupmenu te creëeren
        setupMenu.add(playerDifficuty, 0, 0);
        setupMenu.add(nameAndColors, 0, 1);
        setupMenu.add(startButton, 0, 2);
        setupMenu.add(setupButtons, 0, 3);
        startButton.setPadding(new Insets(20, 100, 20, 100));
        setupButtons.setPadding(new Insets(50, 0, 10, 230));
        nameAndColors.setPadding(new Insets(20, 0, 0, 0));
        playerDifficuty.setPadding(new Insets(10, 0, 0, 0));
        setRight(setupMenu);
        setAlignment(setupMenu, Pos.CENTER);

        //Default Selections of toggleGroups
        easyDifficulty.setSelected(true);
        fourPlayers.setSelected(true);
    }

    Button getBtnStartGame() {
        return btnStartGame;
    }

    Button getBtnExitGame() {
        return btnExitGame;
    }

    ToggleButton getTbtnFullScreen() {
        return tbtnFullScreen;
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

    TextField getTfP1name(){
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

    ComboBox getColorPickerP1(){
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

    ImageView getIvPlayer1() {
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

    public ObservableList<String> getColorOptions() {
        return colorOptions;
    }
}
