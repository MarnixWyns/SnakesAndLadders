package be.kdg.SnakesAndLadders.view.Help;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

/**
 * Ruben Vanloo
 * 9/03/2018.
 */
public class HelpView extends BorderPane {
    private TextArea taHelp = new TextArea();

    public HelpView() {
        setCenter(taHelp);
        setPrefHeight(300);
        setPrefWidth(300);
        taHelp.setEditable(false);
    }

    public TextArea getTaHelp() {
        return taHelp;
    }
}
