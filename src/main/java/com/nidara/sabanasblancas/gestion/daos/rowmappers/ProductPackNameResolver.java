package com.nidara.sabanasblancas.gestion.daos.rowmappers;

public class ProductPackNameResolver {

    private static ProductPackNameResolver ourInstance = new ProductPackNameResolver();

    public static ProductPackNameResolver getInstance() {
        return ourInstance;
    }

    private ProductPackNameResolver() {
    }

    public String getNameFromReference(String reference, String defaultValue) {

        if (reference.length()<8) {
            return defaultValue;
        }

        switch (reference.substring(0, 8)) {
            case "PACK-JSP":
                return "Juego de sábanas 50/50";
            case "PACK-JNP":
                return "Juego nórdico 50/50";
            case "PACK-JSA":
                return "Juego de sábanas 100%";
            case "PACK-JNA":
                return "Juego nórdico 100%";
            case "PACK-JSM":
                return "Juego de sábanas percal 50/50";
            case "PACK-JNM":
                return "Juego nórdico percal 50/50";
            case "PACK-JSH":
                return "Juego de sábanas percal 100%";
            case "PACK-JNH":
                return "Juego nórdico percal 100%";
            case "PACK-JSS":
                return "Juego de sábanas satén";
            case "PACK-JNS":
                return "Juego nórdico satén";
            default:
                return defaultValue;
        }

    }
}
