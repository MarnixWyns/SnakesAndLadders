package be.kdg.SnakesAndLadders.view;

import be.kdg.SnakesAndLadders.view.Help.HelpPresenter;
import be.kdg.SnakesAndLadders.view.Help.HelpView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * In order to prevent long, duplicate code blocks for throwing alerts in presenter classes, this class features
 * a few overriding methods for quickly making an alert using only a single line of code.
 *
 * @author Marnix
 * @version 1.0
 */
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

    public void throwHelpDialog(){
        HelpView helpView = new HelpView();
        HelpPresenter helpPresenter = new HelpPresenter(helpView);
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.setScene(new Scene(helpView));
        helpStage.showAndWait();
    }
}
