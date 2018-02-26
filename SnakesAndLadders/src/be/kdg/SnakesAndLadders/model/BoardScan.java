package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 9/02/2018
 */

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        this.file = file;

        try {
            scanner = new Scanner(file);

            LinkedHashMap<Integer, Integer> snakes = new LinkedHashMap<>();
            LinkedHashMap<Integer, Integer> ladders = new LinkedHashMap<>();
            String bgPath = null;
            int size = 20;


            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {

                } else if (line.contains(".png") || line.contains(".jpg")) {
                    bgPath = line;

                    //Does not read Bord, all others are fine :D
                } else if (line.startsWith("BORD")) {
                    size = Integer.parseInt(line.substring(5));

                } else if (line.startsWith("LADDERS")) {

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = Integer.parseInt(sub.substring(0,sub.indexOf('-')));
                        int stop = Integer.parseInt(sub.substring(sub.indexOf('-') + 1));

                        ladders.put(start, stop);
                    }


                } else if (line.startsWith("SLANGEN")) {

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = Integer.parseInt(sub.substring(0,sub.indexOf('-')));
                        int stop = Integer.parseInt(sub.substring(sub.indexOf('-') + 1));

                        snakes.put(start, stop);
                    }


                } else throw new SnakesAndLaddersException("IllegalFileFormat");

            }
            board = new Board(bgPath, snakes,ladders, size);

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new SnakesAndLaddersException("Game file not found");
        }
    }
    /*
    public void save(File originalFile, File location) {

        String originalText = "";
        File save = new File(location.getAbsolutePath());

        try {
            scanner =  new Scanner(originalFile);

            while (scanner.hasNext()){
                originalText += scanner.nextLine();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(save));
            writer.write(originalText);
            System.out.println(originalText);

            //[0-9]+-[A-Z]-([a-z]|[A-Z])+

            String playersSave = "PLAYERS";

            playersSave += snl.getPlayers().size() + "(";


            for (Player player : snl.getPlayers()) {
                String p = "";
                p += player.getPlayerPos() + "-";
                p += player.getColor().toString().toUpperCase().substring(0,1) + "-";
                p += player.getUsername() + ",";
                playersSave += p;
            }

            playersSave += ")";

            System.out.print(playersSave);
            writer.close();
        } catch (IOException e) {
            throw new SnakesAndLaddersException("FileWriteError");
        }
    }
    */

    public void readSave(File save) {
        this.save = save;

        try {
            scanner = new Scanner(file);

            LinkedHashMap<Integer, Integer> snakes = new LinkedHashMap<>();
            LinkedHashMap<Integer, Integer> ladders = new LinkedHashMap<>();
            String bgPath = null;
            int size = 20;
            ArrayList<Player> players = new ArrayList<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {

                } else if (line.contains(".png") || line.contains(".jpg")) {
                    bgPath = line;

                    //Does not read Bord, all others are fine :D
                } else if (line.startsWith("BORD")) {
                    size = Integer.parseInt(line.substring(5));

                } else if (line.startsWith("LADDERS")) {

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = Integer.parseInt(sub.substring(0,sub.indexOf('-')));
                        int stop = Integer.parseInt(sub.substring(sub.indexOf('-') + 1));

                        ladders.put(start, stop);
                    }


                } else if (line.startsWith("SLANGEN")) {

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = Integer.parseInt(sub.substring(0,sub.indexOf('-')));
                        int stop = Integer.parseInt(sub.substring(sub.indexOf('-') + 1));

                        snakes.put(start, stop);
                    }


                } else if (line.startsWith("PLAYERS")){
                    int nPlayers =Integer.parseInt(line.substring(9));

                    Pattern pattern = Pattern.compile("[0-9]+-[A-Z]-([a-z]|[A-Z])+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()){

                        int pos = Integer.parseInt(line.substring(10, line.indexOf('-')));
                        PieceColor playerC = PieceColor.RED;
                        switch (line.substring(line.indexOf('-'), line.lastIndexOf('-'))){
                            case "R": playerC = PieceColor.RED;
                            case "G": playerC = PieceColor.GREEN;
                            case "B": playerC = PieceColor.BLUE;
                            case "Y": playerC = PieceColor.YELLOW;
                        }
                        String name = line.substring(line.lastIndexOf('-'));

                        players.add(new Player(playerC, name, pos));
                    }


                } else throw new SnakesAndLaddersException("IllegalFileFormat");

            }
            board = new Board(bgPath, snakes,ladders, players, size);

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new SnakesAndLaddersException("Game file not found");
        }
    }

    public Board getBoard() {
        return board;
    }
}
