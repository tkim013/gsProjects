package com.project.humansvsgoblins;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameWorld {

    static ArrayList<ArrayList<Land>> gw = new ArrayList<>();
    final static int row = 10;
    final static int col = 10;

    public GameWorld() {

        for (int i = 0; i < row; i++) {
            gw.add(new ArrayList<>());

            for (int j = 0; j < col; j++) {
                gw.get(i).add(new Land());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Land> list : gw) {
            for (Land l : list) {
                sb.append(l.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}