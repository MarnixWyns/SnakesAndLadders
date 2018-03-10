package be.kdg.SnakesAndLadders.view;

import be.kdg.SnakesAndLadders.view.Help.HelpPresenter;
import be.kdg.SnakesAndLadders.view.Help.HelpView;
import be.kdg.SnakesAndLadders.view.Start.StartView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Ruben Vanloo
 * 10/03/2018.
 */
public class HelpThrower {
    private StartView startView = new StartView();

    public void throwHelp(StartView view){
        this.startView = view;
        HelpView helpView = new HelpView();
        HelpPresenter helpPresenter = new HelpPresenter(helpView);
        Stage helpStage = new Stage();
        helpStage.setTitle("Help");
        helpStage.initOwner(view.getScene().getWindow());
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.setScene(new Scene(helpView));
        helpStage.showAndWait();
    }
}
