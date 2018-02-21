package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 9/02/2018
 */

import javafx.scene.control.Alert;
import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BoardScan {
    private File file;
    private File save;
    private Scanner scanner;
    private ArrayList<Player> players;

    private SnakesAndLadders snl;

    private Board board;

    public BoardScan() {
        board = new Board();
        snl = new SnakesAndLadders();
    }

    public void readFile(File file) {
        //TODO: Only when working, remove SOUT's
        this.file = file;

        try {
            System.out.println("ReadFile method started ");
            scanner = new Scanner(file);

            System.out.println("Succesfully read file");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    scanner.nextLine();
                    System.out.println("Comment");
                    //TODO: Read next Line
                } else if (line.startsWith("BORD")) {
                    System.out.println("Board");
                    scanner.nextLine();
                    //TODO: Read next line, no variable board sizes implemented
                } else if (line.contains("SLANGEN")) {
                    System.out.println("Snakes");
                    HashMap<Integer, Integer> snakes = new HashMap<>();

                    //TODO: put in Board Map

                    board.setSnakes(snakes);

                } else if (line.contains("LADDERS")) {
                    System.out.print("Ladders");

                    HashMap<Integer, Integer> ladders = new HashMap<>();

                    //TODO: Put in Board Map

                    board.setLadders(ladders);
                } else System.out.println("Error");

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
            for (Player player : snl.getPlayers()){
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
                    //You're in a good file,
                } else throw new SnakesAndLaddersException("IllegalSaveFileHeader");
                if (line.startsWith("P")){
                    //TODO: String to enum, no idea what this thing is actually doing
                    int index = Integer.parseInt(scanner.next().substring(1,2));
                    String name = scanner.next();
                    String color = scanner.next();
                    int startPos = scanner.nextInt();

                    if (scanner.next().equals("$")) scanner.nextLine();

                    players.add(new Player(name, color, startPos));
                }
                else throw new SnakesAndLaddersException("IllegalSaveFormat");
            }

            snl.setPlayers(players);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Board getBoard() {
        return board;
    }
}
