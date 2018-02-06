package be.kdg.SnakesAndLadders.model;

import java.nio.file.Path;

public enum Difficulty {
    EASY("BoardLayouts/Easy.txt"), NORMAL("BoardLayouts/Easy.txt"), HARD("BoardLayouts/Easy.txt");

    Difficulty(String path){

    }

    public String getPath(Difficulty difficulty){
        return difficulty.name();
    }


}
