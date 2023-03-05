package fr.brizeos.rubixcube.bll;

import fr.brizeos.rubixcube.bo.Cube;
import fr.brizeos.rubixcube.bo.Move;
import fr.brizeos.rubixcube.bo.Place;
import org.apache.commons.lang3.SerializationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResolverManager {

    public static List<String> resolve(Cube cube) {

        List<Cube> tentatives = new CopyOnWriteArrayList<>();

        tentatives.add(SerializationUtils.clone(cube));

        while (true) {
            for (Cube tentative : tentatives) {
                for (Place place : Place.values()) {
                    for (Move move : Move.values()) {
                        if(tentative.getResult().size() == 0 ||
                                !tentative.getResult().get(tentative.getResult().size()-1)
                                        .equals(place.getCode()+ (move == Move.PLUS ? Move.MOINS.getCode() : Move.PLUS.getCode()) )) {

                            Cube copy = SerializationUtils.clone(tentative);
                            Mover.move(copy, place, move);
                            copy.getResult().add(place.getCode() + move.getCode());

                            if(Checker.check(copy)) {
                                System.out.println("Solution  => " + copy.getResult()  );
                                return copy.getResult();
                            }
                            else {
                                tentatives.remove(tentative);
                                tentatives.add(copy);
                            }
                        }
                    }
                }
            }
        }
    }




}
