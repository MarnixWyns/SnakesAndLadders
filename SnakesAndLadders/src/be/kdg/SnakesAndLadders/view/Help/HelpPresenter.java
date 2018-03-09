package be.kdg.SnakesAndLadders.view.Help;

import be.kdg.SnakesAndLadders.model.Help;
import javafx.scene.control.Alert;

import java.io.IOException;

/**
 * Ruben Vanloo
 * 9/03/2018.
 */
public class HelpPresenter {
    public HelpPresenter(HelpView view) {
        try {
            view.getTaHelp().setText(new Help().getHelp());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
