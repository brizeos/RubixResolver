package fr.brizeos.rubixcube.bo;

import lombok.Getter;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Place  implements Serializable {
    HAUT, BAS, GAUCHE, DROITE, FACE, ARRIERE;

    private static final Map<Place, Map<Place, Place>> translation = new HashMap<>();

    static {
        translation.put(HAUT, new HashMap<>());
        translation.get(HAUT).put(GAUCHE, ARRIERE);
        translation.get(HAUT).put(ARRIERE, DROITE);
        translation.get(HAUT).put(DROITE, FACE);
        translation.get(HAUT).put(FACE, GAUCHE);
        translation.get(HAUT).put(HAUT, HAUT);
        translation.get(HAUT).put(BAS, BAS);

        translation.put(BAS, new HashMap<>());
        translation.get(BAS).put(GAUCHE, FACE);
        translation.get(BAS).put(ARRIERE, GAUCHE);
        translation.get(BAS).put(DROITE, ARRIERE);
        translation.get(BAS).put(FACE, DROITE);
        translation.get(BAS).put(BAS, BAS);
        translation.get(BAS).put(HAUT, HAUT);

        translation.put(FACE, new HashMap<>());
        translation.get(FACE).put(GAUCHE, HAUT);
        translation.get(FACE).put(HAUT, DROITE);
        translation.get(FACE).put(DROITE, BAS);
        translation.get(FACE).put(BAS, GAUCHE);
        translation.get(FACE).put(FACE, FACE);
        translation.get(FACE).put(ARRIERE, ARRIERE);

        translation.put(ARRIERE, new HashMap<>());
        translation.get(ARRIERE).put(GAUCHE, BAS);
        translation.get(ARRIERE).put(HAUT, GAUCHE);
        translation.get(ARRIERE).put(DROITE, HAUT);
        translation.get(ARRIERE).put(BAS, DROITE);
        translation.get(ARRIERE).put(ARRIERE, ARRIERE);
        translation.get(ARRIERE).put(FACE, FACE);

        translation.put(GAUCHE, new HashMap<>());
        translation.get(GAUCHE).put(ARRIERE, HAUT);
        translation.get(GAUCHE).put(HAUT, FACE);
        translation.get(GAUCHE).put(FACE, BAS);
        translation.get(GAUCHE).put(BAS, ARRIERE);
        translation.get(GAUCHE).put(GAUCHE, GAUCHE);
        translation.get(GAUCHE).put(DROITE, DROITE);

        translation.put(DROITE, new HashMap<>());
        translation.get(DROITE).put(ARRIERE, BAS);
        translation.get(DROITE).put(HAUT, ARRIERE);
        translation.get(DROITE).put(FACE, HAUT);
        translation.get(DROITE).put(BAS, FACE);
        translation.get(DROITE).put(DROITE, DROITE);
        translation.get(DROITE).put(GAUCHE, GAUCHE);


    }

    public static void next(Face face, Place place) {
        Map<Place, Color> newColors = new HashMap<>();

        face.getColors().forEach((p, c) -> {
            p = translation.get(place).get(p);
            newColors.put(p, c);
        });

        face.setColors(newColors);

    }

    public static void previous(Face face, Place place) {
        Place p = SerializationUtils.clone(place);
        switch (p){
            case HAUT -> p = BAS;
            case BAS -> p = HAUT;
            case ARRIERE -> p = FACE;
            case GAUCHE -> p = DROITE;
            case FACE -> p = ARRIERE;
            case DROITE -> p = GAUCHE;
        }
        next(face, p);
    }

    public static void switchColors(Face face, Place place, Move move) {
        if (move == Move.PLUS) next(face, place);
        else previous(face, place);

    }

    public static Place convert(String str) throws Exception {
        return switch (str) {
            case "H" -> HAUT;
            case "B" -> BAS;
            case "G" -> GAUCHE;
            case "D" -> DROITE;
            case "F" -> FACE;
            case "A" -> ARRIERE;
            default -> throw new Exception("Error decryp^tion.");
        };
    }

    public String getCode() {
        return switch (this) {
            case HAUT -> "H";
            case BAS -> "B";
            case GAUCHE -> "G";
            case DROITE -> "D";
            case FACE -> "F";
            case ARRIERE -> "A";
        };
    }


}
