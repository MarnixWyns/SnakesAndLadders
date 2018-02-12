package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 9/02/2018
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardScan {
    private File file;
    private Scanner scanner;

    private Board board;

    public BoardScan() {
        board = new Board();
    }

    private void readFile(File file){
        this.file = file;

        try {
            scanner = new Scanner(file);

            while (scanner.hasNext()){
                String line = scanner.nextLine();

                if (line.startsWith("#")){
                    //TODO: Read next Line
                }
                else if(line.startsWith("#")){
                    //TODO: Read next line, no variable board sizes implemented
                }
                else if(line.substring(0,6).equals("SLANGEN")){
                    ArrayList<Integer> snakeHeadPos = new ArrayList<>();
                    ArrayList<Integer> snakeTailPos = new ArrayList<>();

                    String text = line;

                    //Function to see where ( starts for opening
                    int noSnakes = 0;
                    int sPos = 0;
                    int ePos;
                    char oB = '(';
                    char cB = ')';

                    //Obtains start pos of snakes coords
                    while (oB != line.charAt(sPos)){
                        sPos++;

                    }

                    ePos = sPos++;

                    for (int i = 0; i < noSnakes * 2; i++) {
                        while (!line.substring(sPos,ePos).contains("-")){

                        }

                    }

                    //TODO: put in Board arraylists

                    board.setSnakeHeadPos(snakeHeadPos);
                    board.setSnakeTailPos(snakeTailPos);
                }
                else if (line.substring(0,6).equals("LADDERS")){
                    //TODO Put in board arraylists
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            throw new SnakesAndLaddersException("Game file not found");
        }


    }

    public Board getBoard() {
        return board;
    }
}
