package be.kdg.SnakesAndLadders.view;/*
 * Marnix Wyns
 * 27/02/2018
 */

import javafx.scene.control.Alert;

public class DialogThrower {
    private Alert alert;

    public DialogThrower() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Default");
    }

    public void throwAlert(Alert.AlertType type, String title, String header, String content){
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void throwAlert(Alert.AlertType type, String title, String header){
        alert.setAlertType(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
