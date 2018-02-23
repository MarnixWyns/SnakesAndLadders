package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 9/02/2018
 */

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoardScan {
    private File file;
    private File save;
    private Scanner scanner;
    private ArrayList<Player> players;

    private SnakesAndLadders snl;

    private Board board;

    public BoardScan() {
        snl = new SnakesAndLadders();
    }

    public void readFile(File file) {
        //TODO: Only when working, remove SOUT's
        this.file = file;

        try {
            scanner = new Scanner(file);

            System.out.println("Succesfully read file");

            HashMap<Integer, Integer> snakes = new HashMap<>();
            HashMap<Integer, Integer> ladders = new HashMap<>();
            String bgPath = null;
            int size = 20;


            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    System.out.println("Comment");
                    //TODO: Read next Line

                } else if (line.contains(".png") || line.contains(".jpg")) {
                    System.out.println("Achtergrond");
                    System.out.println(line);

                    bgPath = line;

                    //Does not read Bord, all others are fine :D
                } else if (line.startsWith("BORD")) {
                    System.out.println("Board");
                    size = Integer.parseInt(line.substring(5));
                    System.out.print(size);
                    //TODO: Read next line, no variable board sizes implemented

                } else if (line.startsWith("LADDERS")) {
                    System.out.println("Ladders");
                    System.out.println(line);

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        //System.out.print(matcher.start() + matcher.end() - 1);
                        System.out.println(line.substring(matcher.start(), matcher.end()));
                    }


                    //TODO: Put in Board Map

                } else if (line.startsWith("SLANGEN")) {
                    System.out.println("Snakes");
                    System.out.println(line);

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        //System.out.print(matcher.start() + matcher.end() - 1);
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = 0;
                        int stop = 0;
                        boolean hasStart = false;
                        Pattern subPat = Pattern.compile("[0-9]+");
                        Matcher subMatch = pattern.matcher(sub);

                        System.out.println(sub);

                        /*
                        while (subMatch.find()) {
                            if (hasStart) {
                                start = Integer.parseInt(sub.substring(subMatch.start(), subMatch.end()));
                                System.out.print(start);
                                hasStart = true;
                            } else {
                                stop = Integer.parseInt(sub.substring(subMatch.start(), subMatch.end()));
                                System.out.print(stop);
                            }

                        }
                        */

                        snakes.put(start, stop);
                    }

                } else System.out.print("Error");

            }
            //board = new Board(new Image(bgPath), snakes,ladders, size);

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
            for (Player player : snl.getPlayers()) {
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
            scanner = new Scanner(save);
            scanner.useDelimiter(":");

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.equals("SAVE")) {
                    //You're in a good file,
                } else throw new SnakesAndLaddersException("IllegalSaveFileHeader");
                if (line.startsWith("P")) {
                    //TODO: String to enum, no idea what this thing is actually doing
                    int index = Integer.parseInt(scanner.next().substring(1, 2));
                    String name = scanner.next();
                    String color = scanner.next();
                    int startPos = scanner.nextInt();

                    if (scanner.next().equals("$")) scanner.nextLine();

                    players.add(new Player(name, color, startPos));
                } else throw new SnakesAndLaddersException("IllegalSaveFormat");
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
