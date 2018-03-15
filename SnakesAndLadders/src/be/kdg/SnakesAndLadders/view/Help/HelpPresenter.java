package be.kdg.SnakesAndLadders.view.Help;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;

/**
 * Presenter class for the help view window, calls a BoardScan method to read help file and displays it in text area
 *
 * @author Ruben Vanloo
 * @version 1.0
 */
public class HelpPresenter {

    public HelpPresenter(HelpView view) {
        SnakesAndLadders snl = new SnakesAndLadders();
        snl.startGame();

        view.getTaHelp().setText(snl.getBoardScan().readHelp());
    }
}
