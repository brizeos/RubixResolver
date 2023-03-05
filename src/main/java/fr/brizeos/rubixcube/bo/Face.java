package fr.brizeos.rubixcube.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Face implements Serializable {
    private boolean corner;
    private Map<Place, Color> colors = new HashMap<>();
    private int x, y, z;

    public Face(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.corner = y!=1 && (x % 2 == 0 || z % 2 ==0);

        initColors();
    }

    private void initColors() {
        if(x == 0) colors.put(Place.GAUCHE, Color.ORANGE);
        if(x == 2) colors.put(Place.DROITE, Color.RED);
        if(y == 0) colors.put(Place.HAUT, Color.WHITE);
        if(y == 2) colors.put(Place.BAS, Color.YELLOW);
        if(z == 0) colors.put(Place.ARRIERE, Color.BLUE);
        if(z == 2) colors.put(Place.FACE, Color.GREEN);
    }



}
