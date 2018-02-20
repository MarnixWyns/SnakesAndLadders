package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 9/02/2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardScan extends SnakesAndLadders {
    private File file;
    private File save;
    private Scanner scanner;
    private ArrayList<Player> players;

    private Board board;

    public BoardScan() {
        board = new Board();
    }

    public void readFile(File file) {
        this.file = file;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    //TODO: Read next Line
                } else if (line.startsWith("#")) {
                    //TODO: Read next line, no variable board sizes implemented
                } else if (line.substring(0, 6).equals("SLANGEN")) {
                    ArrayList<Integer> snakeHeadPos = new ArrayList<>();
                    ArrayList<Integer> snakeTailPos = new ArrayList<>();

                    //TODO: put in Board arraylists

                    board.setSnakeHeadPos(snakeHeadPos);
                    board.setSnakeTailPos(snakeTailPos);
                } else if (line.substring(0, 6).equals("LADDERS")) {
                    ArrayList<Integer> ladderBottomPos = new ArrayList<>();
                    ArrayList<Integer> ladderTopPos = new ArrayList<>();

                    //TODO: Scan function

                    board.setLadderBottomPos(ladderBottomPos);
                    board.setLadderTopPos(ladderTopPos);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new SnakesAndLaddersException("Game file not found");
        }
    }

    public void save() {
        try {
            int index = 1;
            PrintWriter writer = new PrintWriter("saveFile.txt", "UTF-8");

            //Header tag
            writer.println("SAVE");

            //$ = end of line indicator
            //Format: P1:Squirrel:YELLOW:69$
            for (Player player : super.getPlayers()){
                writer.println("P" + index + ":" + player.getUsername() + ":" + player.getColor() + ":" + player.getPlayerPos() + "$");
            }

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            throw new SnakesAndLaddersException("FileWriteError");
        }
    }

    public void readSave(File save) {
        this.save = save;

        try {
            scanner =  new Scanner(save);
            scanner.useDelimiter(":");

            while (scanner.hasNext()){
                String line =  scanner.nextLine();
                if (line.equals("SAVE")){
                    //You're in a good file, TODO: Add exception if SAVE not found on first line
                } else throw new SnakesAndLaddersException("IllegalSaveFile");
                if (line.startsWith("P")){
                    //TODO: String to enum, no idea what this thing is actually doing
                    String index = scanner.next();
                    String name = scanner.next();
                    String color = scanner.next();
                    int startPos = scanner.nextInt();

                    players.add(new Player(name, color, startPos));
                }
                else throw new SnakesAndLaddersException("IllegalSaveFormat");
            }

            super.setPlayers(players);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Board getBoard() {
        return board;
    }
}
