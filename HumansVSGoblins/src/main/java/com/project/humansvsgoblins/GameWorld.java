package com.project.humansvsgoblins;

import java.util.ArrayList;

public class GameWorld {

    private static ArrayList<ArrayList<Land>> landList = new ArrayList<>();
    final static int row = 10;
    final static int col = 10;

    public GameWorld() {

        for (int i = 0; i < col; i++) {
            landList.add(new ArrayList<>());

            for (int j = 0; j < row; j++) {
                landList.get(i).add(new Land());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ArrayList<Land> list : landList) {
            for (Land l : list) {
                if (l.getHasCreature() == null) {
                    sb.append(l);
                } else {
                    sb.append(l.getHasCreature());
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static ArrayList<ArrayList<Land>> getLandList() {
        return landList;
    }

    public static void setLandList(ArrayList<ArrayList<Land>> list) {
        GameWorld.landList = list;
    }
}