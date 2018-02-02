package be.kdg.SnakesAndLadders.model;/*
 * Marnix Wyns
 * 2/02/2018
 */

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Board {
    private int[][] board = new int[10][10];
    private Scanner scanner;

    private Path easyPath;
    private Path normalPath;
    private Path hardPath;

    public Board(){
        easyPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\easy.txt");
        normalPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\normal.txt");
        hardPath = Paths.get("SnakesAndLadders\\src\\be\\kdg\\SnakesAndLadders\\BoardLayouts\\hard.txt");

    }



}
