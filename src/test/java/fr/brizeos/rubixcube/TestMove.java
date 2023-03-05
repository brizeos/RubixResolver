package fr.brizeos.rubixcube;


import fr.brizeos.rubixcube.bll.Checker;
import fr.brizeos.rubixcube.bll.CubeManager;
import fr.brizeos.rubixcube.bll.ResolverManager;
import fr.brizeos.rubixcube.bo.*;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMove {

    @Test
    public void testResolve() throws Exception {
        CubeManager cubeManager = new CubeManager();
        cubeManager.createCube();

        Cube copy = SerializationUtils.clone(cubeManager.getCube());

        Place[] places = Place.values();
        Move[] moves = Move.values();
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random()*100);
            Place p = places[random % places.length];
            Move m = moves[random % 2];
            System.out.println("MOVE = " + p.getCode() + m.getCode() );
            cubeManager.move(p, m);
        }

        for (String s : ResolverManager.resolve(cubeManager.getCube())) {
            cubeManager.move(s);
        }

        Assertions.assertTrue(Checker.check(cubeManager.getCube()));

    }


    @Test
    public void testResolve2() throws Exception {
        CubeManager cubeManager = new CubeManager();
        cubeManager.createCube();

        Cube copy = SerializationUtils.clone(cubeManager.getCube());

        cubeManager.move(Place.DROITE, Move.PLUS);
        cubeManager.move(Place.DROITE, Move.PLUS);
        cubeManager.move(Place.DROITE, Move.PLUS);
        cubeManager.move(Place.BAS, Move.PLUS);
        cubeManager.move(Place.DROITE, Move.MOINS);
        cubeManager.move(Place.BAS, Move.MOINS);


        for (String s : ResolverManager.resolve(cubeManager.getCube())) {
            cubeManager.move(s);
        }

        Assertions.assertTrue(Checker.check(cubeManager.getCube()));

    }


}
