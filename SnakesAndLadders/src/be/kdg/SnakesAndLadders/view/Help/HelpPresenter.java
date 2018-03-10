package be.kdg.SnakesAndLadders.view.Help;

import be.kdg.SnakesAndLadders.model.SnakesAndLadders;

/**
 * Ruben Vanloo
 * 9/03/2018.
 */
public class HelpPresenter {

    public HelpPresenter(HelpView view) {
        SnakesAndLadders snl = new SnakesAndLadders();
        snl.startGame();

        view.getTaHelp().setText(snl.getBoardScan().readHelp());
    }
}
