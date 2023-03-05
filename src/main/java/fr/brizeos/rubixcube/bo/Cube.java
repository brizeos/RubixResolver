package fr.brizeos.rubixcube.bo;

import lombok.Data;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cube implements Serializable {

    List<String> result = new ArrayList<>();
    private Face[][][] faces = new Face[3][3][3];

    public Cube() {
        init();
    }

    private void init() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    if (!(x == 1 && y == 1 && z == 1)) {
                        this.faces[z][y][x] = new Face(x, y, z);
                    }
                }
            }
        }
    }

    public Face[][] getFace(Place place) {

        Face[][] face2D = new Face[3][3];
        int xMax = 2, yMax = 2, zMax = 2;
        int xMin = 0, yMin = 0, zMin = 0;

        switch (place) {
            case HAUT -> yMax = 0;
            case BAS -> yMin = 2;
            case GAUCHE -> xMax = 0;
            case DROITE -> xMin = 2;
            case ARRIERE -> zMax = 0;
            case FACE -> zMin = 2;
        }

        int yI = 0;
        int xI = 0;


        // Z Y0 X0  == FACE
        if (Place.FACE == place) {
            for (int z = zMin; z <= zMax; z++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int x = xMin; x <= xMax; x++) {
//                        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
                        face2D[yI][xI] = faces[z][y][x];
                        xI++;
                    }
                    yI++;
                    xI = 0;
                }
            }
        }
        // Z Y0 X3  == ARRIERE
        else if(place == Place.ARRIERE){
            for (int z = zMin; z <= zMax; z++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int x = xMax; x >= xMin; x--) {
//                        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
                        face2D[yI][xI] = faces[z][y][x];
                        xI++;
                    }
                    yI++;
                    xI = 0;
                }
            }
        }
        // X X0 Y0 == GAUCHE
        // X Z3 Y0 == DROITE
        else if (Place.GAUCHE==place) {
            for (int x = xMin; x <= xMax; x++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int z = zMin; z <= zMax; z++) {
//                        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
                        face2D[yI][xI] = faces[z][y][x];
                        xI++;
                    }
                    yI++;
                    xI = 0;
                }
            }

        }else if(place == Place.DROITE){
            for (int x = xMin; x <= xMax; x++) {
                for (int y = yMin; y <= yMax; y++) {
                    for (int z = zMax; z >= zMin; z--) {
//                        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
                        face2D[yI][xI] = faces[z][y][x];
                        xI++;
                    }
                    yI++;
                    xI = 0;
                }
            }
        }
        // Y Z0 X0  == HAUT
        // Y Z3 X0  == BAS
        else {
            boolean isBas = Place.BAS == place;
            for (int y = yMin; y <= yMax; y++) {
                for (int z = (isBas ? zMax : zMin); (isBas ? z >= zMin : z <= zMax); z += (isBas ? -1 : 1)) {
                    for (int x = xMin; x <= xMax; x++) {
//                        System.out.println("x = " + x + ", y = " + y + ", z = " + z);
                        face2D[yI][xI] = faces[z][y][x];
                        xI++;
                    }
                    yI++;
                    xI = 0;
                }
            }
        }

        return face2D;
    }


}
