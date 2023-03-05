package fr.brizeos.rubixcube.bll;

import fr.brizeos.rubixcube.bo.Cube;
import fr.brizeos.rubixcube.bo.Move;
import fr.brizeos.rubixcube.bo.Place;
import lombok.Getter;

import java.util.List;

public class CubeManager {

    @Getter
    private Cube cube;

    public Cube createCube(){
        this.cube = new Cube();
        return this.cube;
    }

    public void move(Place place, Move move){
        Mover.move(cube, place, move);
    }

    public void move(String move) throws Exception {
        Mover.move(cube, move);
    }

    public List<String> getSolution() {
        return ResolverManager.resolve(cube);
    }
}
