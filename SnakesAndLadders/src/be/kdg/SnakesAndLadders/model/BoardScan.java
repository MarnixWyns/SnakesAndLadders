package be.kdg.SnakesAndLadders.model;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The BoardScan class is the IO handling class which is used for reading and writing data from save and other game files and
 * writes data to save files. This class creates the Board objects containing the positions of snakes and ladders that allow the
 * game to be played as desired.
 *
 * @author Marnix
 * @version 2.0
 */
public class BoardScan {

    private Board board;
    private String bgPath;

    /**
     * The constructor creates a new BoardScan object which contains a model class in order to cooperate
     * with the Board classes and presenters.
     */
    public BoardScan() {
        bgPath = null;
    }

    /**
     * This method is used for reading a text file, parses its text content into usable Strings int's and Player objects,
     * and uses this for creating a new Board class for the game to use
     *
     * @param file game requires a difficulty file to read, currently there are 3 supported difficulty files
     *             and the readfile method is also used for reading data from save files
     * @throws SnakesAndLaddersException if the text file is corrupted and does not contain the required lines
     * @throws SnakesAndLaddersException as a replacement for the caught FileNotFoundException
     */
    public void readFile(File file) {

        boolean isSaveFile = false;
        ArrayList<Player> players = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {

            LinkedHashMap<Integer, Integer> snakes = new LinkedHashMap<>();
            LinkedHashMap<Integer, Integer> ladders = new LinkedHashMap<>();

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    //Do Nothing
                } else if (line.contains(".png") || line.contains(".jpg")) {
                    bgPath = line;
                } else if (line.startsWith("BORD")) {
                    //Do nothing, we did not implement variable board size
                } else if (line.startsWith("LADDERS") || line.startsWith("SLANGEN")) {

                    Pattern pattern = Pattern.compile("[0-9]+-[0-9]+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String sub = line.substring(matcher.start(), matcher.end());

                        int start = Integer.parseInt(sub.substring(0, sub.indexOf('-')));
                        int stop = Integer.parseInt(sub.substring(sub.indexOf('-') + 1));

                        if (line.startsWith("LADDERS")) {
                            ladders.put(start, stop);
                        } else if (line.startsWith("SLANGEN")) {
                            snakes.put(start, stop);
                        }

                    }
                } else if (line.startsWith("PLAYERS")) {

                    isSaveFile = true;

                    //int nPlayers = Integer.parseInt(line.substring(8, 9));

                    Pattern pattern = Pattern.compile("[0-9]+-[A-Z]-([A-Z]|[a-z]|[0-9])+");
                    Matcher matcher = pattern.matcher(line);
                    while (matcher.find()) {
                        String part = line.substring(matcher.start(), matcher.end());

                        int pos = Integer.parseInt(part.substring(0, part.indexOf("-")));

                        String name;
                        name = part.substring(part.indexOf('-', 4) + 1);

                        PieceColor playerC;
                        switch (part.substring(part.indexOf('-') + 1, part.lastIndexOf('-'))) {
                            case "R":
                                playerC = PieceColor.RED;
                                break;
                            case "G":
                                playerC = PieceColor.GREEN;
                                break;
                            case "B":
                                playerC = PieceColor.BLUE;
                                break;
                            case "Y":
                                playerC = PieceColor.YELLOW;
                                break;
                            default:
                                throw new SnakesAndLaddersException();
                        }

                        players.add(new Player(playerC, name, pos));

                        System.out.printf("Color: %s Name: %s Position: %d\n", playerC, name, pos);
                    }

                } else throw new SnakesAndLaddersException("IllegalFileFormat");

            }

            if (isSaveFile) {
                board = new Board(bgPath, snakes, ladders, players);
            } else board = new Board(bgPath, snakes, ladders);

        } catch (FileNotFoundException e) {
            throw new SnakesAndLaddersException("Game file not found");
        }
    }

    /**
     * This method is called whenever a player wants to save the current game. There is only support for 1 save file.
     * If a previous save file exists, the old one will be overwritten. The way it writes a save file is by copying the
     * original game file and adding an extra line containing the player save data.
     *
     * @param difficulty save requires an original difficulty file that is required for copying the original game data.
     * @param players    in order for the save file to work an up to date list of players is required which have to be
     *                   passed through by the presenter.
     * @throws SnakesAndLaddersException if original game file can not be found a SnakesAndLaddersException is thrown
     */
    public void save(File difficulty, ArrayList<Player> players) {
        ClassLoader classLoader = getClass().getClassLoader();

        //TODO: Stop throwing nullpointer klote resources directory
        //File newfile = new File(classLoader.getResource("/save_file.txt").getFile());
        File newfile = new File("C:\\Projects\\SnakesAndLadders\\SlangenEnLadders\\SnakesAndLadders\\resources\\save_file.txt");

        try {
            Files.deleteIfExists(newfile.toPath());
        } catch (IOException e) {
            throw new SnakesAndLaddersException("Original game file not found");
        }

        try {
            Files.createFile(newfile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(newfile))); Scanner scanner = new Scanner(difficulty)) {

            //COPY original file
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                pw.write(line + "\n");

            }

            //Write extra line containing player data
            StringBuilder playersave = new StringBuilder();

            int playerid = 0;
            for (Player player : players) {
                StringBuilder iplayer = new StringBuilder();

                //Write playerpos
                iplayer.append(player.getPlayerPos());
                iplayer.append("-");

                //Write color
                switch (player.getColor().toString().toLowerCase()) {
                    case "yellow":
                        iplayer.append("Y");
                        break;
                    case "blue":
                        iplayer.append("B");
                        break;
                    case "red":
                        iplayer.append("R");
                        break;
                    case "green":
                        iplayer.append("G");
                        break;
                }
                iplayer.append("-");

                //Write Username
                iplayer.append(player.getUsername());
                if (playerid != players.size() - 1) {
                    iplayer.append(",");
                }

                playersave.append(iplayer.toString());
                playerid++;
            }


            pw.format("PLAYERS=%d(%s)", players.size(), playersave.toString());


        } catch (IOException e) {
            throw new SnakesAndLaddersException("Original game file not found");
        }
    }

    /**
     * @return String containing the contents of the Help.txt file for display in a textarea
     */
    public String readHelp() {

        StringBuilder help = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/txtFiles/Help.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                help.append(line).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return help.toString();
    }

    /**
     * @return Board returns the board that is created after the readFile() method has been used
     */
    public Board getBoard() {
        return board;
    }
}
