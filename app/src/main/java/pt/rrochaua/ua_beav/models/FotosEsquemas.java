package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;

/**
 * Created by filip on 07/07/2017.
 */

public class FotosEsquemas {

    public String esquemas;
    public ArrayList<String> fotos = new ArrayList<String>();

    public FotosEsquemas(String esquemas, ArrayList<String> fotos){

        this.esquemas = esquemas;
        this.fotos = fotos;

    }
}
