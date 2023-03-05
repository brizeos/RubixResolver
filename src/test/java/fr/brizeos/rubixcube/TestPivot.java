package fr.brizeos.rubixcube;


import fr.brizeos.rubixcube.bll.CubeManager;
import fr.brizeos.rubixcube.bo.Color;
import fr.brizeos.rubixcube.bo.Face;
import fr.brizeos.rubixcube.bo.Move;
import fr.brizeos.rubixcube.bo.Place;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPivot {

    @Test
    public void testPivot(){
        CubeManager cubeManager = new CubeManager();

        cubeManager.createCube();

        Face[][][] faces = SerializationUtils.clone(cubeManager.getCube().getFaces());

        cubeManager.move(Place.HAUT, Move.PLUS);

        Face face = cubeManager.getCube().getFaces()[0][0][0];

        Assertions.assertEquals(Color.WHITE, face.getColors().get(Place.HAUT), face.getColors().toString());
        Assertions.assertEquals(Color.GREEN, face.getColors().get(Place.GAUCHE), face.getColors().toString());
        Assertions.assertEquals(Color.ORANGE, face.getColors().get(Place.ARRIERE), face.getColors().toString());

        cubeManager.move(Place.HAUT, Move.PLUS);
        cubeManager.move(Place.HAUT, Move.PLUS);
        cubeManager.move(Place.HAUT, Move.PLUS);

        face = cubeManager.getCube().getFaces()[0][0][0];
        Assertions.assertEquals(Color.ORANGE, face.getColors().get(Place.GAUCHE));

        cubeManager.createCube();
        cubeManager.move(Place.HAUT, Move.MOINS);

        face = cubeManager.getCube().getFaces()[0][0][0];
        Assertions.assertEquals(Color.WHITE, face.getColors().get(Place.HAUT), face.getColors().toString());
        Assertions.assertEquals(Color.BLUE, face.getColors().get(Place.GAUCHE), face.getColors().toString());
        Assertions.assertEquals(Color.RED, face.getColors().get(Place.ARRIERE), face.getColors().toString());

    }
}
