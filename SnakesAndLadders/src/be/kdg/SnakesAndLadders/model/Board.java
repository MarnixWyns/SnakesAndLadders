package be.kdg.SnakesAndLadders.model;

import java.util.*;

/**
 * The Board class is the class that is used to store the current game, containing players,
 * the background image path and two ArrayLists. The arraylists contain the begin and end positions of
 * the Snakes and Ladders, using the start as a key and the end as the corresponding value.
 *
 * @author Marnix Wyns
 * @version 1.0
 */
public class Board {

    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private String imgBackground;
    private ArrayList<Player> players;

    /**
     *
     * This is the constructor used if there is no player data utilized, you are starting a completely new game.
     *
     * @param imgBackground of the imgBackground image of the current level
     * @param snakes hashmap containing Key<Integer> and Value<Integer>, the key is used as the head of the snake,
     *               the value contains the tail of the snake.
     * @param ladders hashmap containing Key<Integer> and Value<Integer>, the key is used as the bottom of a ladder,
     *                the value contains the top of the ladder.
     */
    public Board(String imgBackground, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders){
        this.imgBackground = imgBackground;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    /**
     *
     * This constructor is utilised if a saved game is restarted, it also has the parameter containing player data.
     *
     * @param imgBackground path of the background image of the current level
     * @param snakes Hashmap containing Key<Integer> and Value<Integer>, the key is used as the head of the snake,
     *               the value contains the tail of the snake.
     * @param ladders Hashmap containing Key<Integer> and Value<Integer>, the key is used as the bottom of a ladder,
     *                the value contains the top of the ladder.
     * @param players Arraylist<Player> containing save data from 1 to 4 players to be used when continuing a saved game.
     */
    public Board(String imgBackground, HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, ArrayList<Player> players) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.imgBackground = imgBackground;
        this.players = players;
    }

    /**
     *
     * The snake and ladder start and begin positions are stored in 2 different hashmaps, using their start position as the key and
     * end position as value. This creates a way to quickly check if the position the player is currently standing on is the beginning
     * of a ladder or snake. If this is the case, the end position is returned.
     *
     * @param pos method uses the current players position as a parameter for use as a key
     * @return int Returns a position for the player. If there was no match for the position as a key, there is no
     * snake on the current player's position. If there is a value assigned to the current key, return that for moving the player.
     */
    public int checkPos(int pos){

        if (snakes.containsKey(pos)){
            return snakes.get(pos);
        }

        if (ladders.containsKey(pos)){
            return ladders.get(pos);
        }

        return pos;
    }

    public ArrayList<Player> getSavedPlayers() {
        return players;
    }

    /**
     *
     * This methods returns a string containing the name of the current difficulty's path, this used for the Setup and GamePresenter
     * classes in conjunction with the resources/boardlayouts path to obtain the image.
     *
     * @return String containing the background name of the selected difficulty
     */
    public String getBgPath() {
        return imgBackground;
    }
}
