package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 2/02/2018
 */

import be.kdg.SnakesAndLadders.model.PieceColor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;


public class SetupView extends GridPane {
    private ImageView ivBackground;
    private ImageView ivContextBackground;

    private Button btnStartGame;
    private Button btnExitGame;
    private ToggleButton tbtnFullScreen;

    private TextField tfP1name;
    private TextField tfP2name;
    private TextField tfP3name;
    private TextField tfP4name;

    private Label lblPlayer1;
    private Label lblPlayer2;
    private Label lblPlayer3;
    private Label lblPlayer4;
    private Label lblSelectPlayers;
    private Label lblSelectDifficulty;

    private ComboBox<String> colorPickerP1;
    private ComboBox<PieceColor> colorPickerP2;
    private ComboBox<PieceColor> colorPickerP3;
    private ComboBox<PieceColor> colorPickerP4;

    private ColumnConstraints column1;
    private ColumnConstraints column2;

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

        lblPlayer1 = new Label("Player 1: ");
        lblPlayer2 = new Label("Player 2: ");
        lblPlayer3 = new Label("Player 3: ");
        lblPlayer4 = new Label("Player 4: ");
        lblSelectPlayers = new Label("Select number of players");
        lblSelectDifficulty = new Label("Select Difficulty");

        //colorPickerP1 = new ComboBox<String>(PieceColor.values().toString()); //TODO: initialising ComboBoxes with correct values


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
        //column1.setPercentWidth(65);
        //column2.setPercentWidth(35);



    }

    private void layoutNodes() {
        //Gridpane view for main image
        //add(ivBackground,0,1);
    }


}
