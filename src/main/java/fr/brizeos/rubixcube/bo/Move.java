package fr.brizeos.rubixcube.bo;

import java.io.Serializable;

public enum Move  implements Serializable {

    PLUS, MOINS;

    public String getCode() {
        return switch (this) {
            case PLUS -> "+";
            case MOINS -> "-";
        };
    }


}
