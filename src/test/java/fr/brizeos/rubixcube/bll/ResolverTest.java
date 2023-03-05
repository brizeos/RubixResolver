package fr.brizeos.rubixcube.bll;

import fr.brizeos.rubixcube.bo.Move;
import fr.brizeos.rubixcube.bo.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResolverTest {

    @Test
    public void TestCheck(){
        CubeManager cubeManager = new CubeManager();
        cubeManager.createCube();
        boolean check = Checker.check(cubeManager.getCube());

        assertTrue(check);

        cubeManager.move(Place.HAUT, Move.PLUS);
        check = Checker.check(cubeManager.getCube());
        assertFalse(check);

    }
}