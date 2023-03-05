package fr.brizeos.rubixcube.bll;

import fr.brizeos.rubixcube.bo.*;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Mover {
    private static final Map<String, String> translationPlus = new HashMap<>();
    private static final Map<String, String> translationMoins = new HashMap<>();


    static {
        translationPlus.put("02", "00");
        translationPlus.put("12", "01");
        translationPlus.put("22", "02");
        translationPlus.put("01", "10");
        translationPlus.put("11", "11");
        translationPlus.put("21", "12");
        translationPlus.put("00", "20");
        translationPlus.put("10", "21");
        translationPlus.put("20", "22");

        translationMoins.put("02", "22");
        translationMoins.put("12", "21");
        translationMoins.put("22", "20");
        translationMoins.put("01", "12");
        translationMoins.put("11", "11");
        translationMoins.put("21", "10");
        translationMoins.put("00", "02");
        translationMoins.put("10", "01");
        translationMoins.put("20", "00");
    }

    static void move(Cube cube, Place place, Move move) {
        Face[][] faces = pivotFaces(cube.getFace(place), (move == Move.PLUS ? translationPlus : translationMoins));
        pivotColors(faces, place, move);
        putFace(cube, faces, place);
    }

    static void move(Cube cube, String moveStr) throws Exception {
        Place place = Place.convert(moveStr.substring(0,1));
        Move move = moveStr.substring(1).equals("+") ? Move.PLUS : Move.MOINS;
        move(cube, place, move);
    }
    private static void putFace(Cube cube, Face[][] faces, Place place) {
        Face[][] oldFaces = SerializationUtils.clone(cube.getFace(place));
        int idx = 0, idy = 0;

        for (Face[] line : faces) {
            for (Face face : line) {
                face.setX(oldFaces[idy][idx].getX());
                face.setY(oldFaces[idy][idx].getY());
                face.setZ(oldFaces[idy][idx].getZ());

                cube.getFaces()[face.getZ()][face.getY()][face.getX()] = face;
                idx++;
            }
            idy++;
            idx = 0;
        }

    }

    private static void pivotColors(Face[][] faces, Place place, Move move) {

        for (Face[] line : faces) {
            for (Face face : line) {
                Place.switchColors(face, place, move);
            }
        }
    }


    private static Face[][] pivotFaces(Face[][] faces, Map<String, String> translation) {
        Face[][] newFaces = new Face[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Face f = faces[i][j];
                String tr = translation.get(String.valueOf(j) + i);
                newFaces[Integer.parseInt(tr.substring(1, 2))][Integer.parseInt(tr.substring(0, 1))] = f;
            }
        }

        return newFaces;
    }

}

