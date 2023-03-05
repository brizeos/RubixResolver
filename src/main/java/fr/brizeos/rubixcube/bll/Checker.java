package fr.brizeos.rubixcube.bll;

import fr.brizeos.rubixcube.bo.Color;
import fr.brizeos.rubixcube.bo.Cube;
import fr.brizeos.rubixcube.bo.Place;

import java.util.Map;

public class Checker {

    public static boolean check(Cube cube) {
        Cube copy = new CubeManager().createCube();
        boolean resolved = true;
        for (int z = 0; z < 3; z++) {
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {

                    if (x == 1 && y == 1 && z == 1) continue;

                    Map<Place, Color> colorResolved = cube.getFaces()[z][y][x].getColors();
                    Map<Place, Color> colorCopy = copy.getFaces()[z][y][x].getColors();

                    if (colorResolved.get(Place.GAUCHE) != colorCopy.get(Place.GAUCHE) ||
                            colorResolved.get(Place.DROITE) != colorCopy.get(Place.DROITE) ||
                            colorResolved.get(Place.BAS) != colorCopy.get(Place.BAS) ||
                            colorResolved.get(Place.HAUT) != colorCopy.get(Place.HAUT) ||
                            colorResolved.get(Place.ARRIERE) != colorCopy.get(Place.ARRIERE) ||
                            colorResolved.get(Place.FACE) != colorCopy.get(Place.FACE)) {
                        resolved = false;
                        break;
                    }
                }
            }
        }
        return resolved;

    }
}
