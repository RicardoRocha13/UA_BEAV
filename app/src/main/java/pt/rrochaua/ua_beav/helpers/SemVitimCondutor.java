package pt.rrochaua.ua_beav.helpers;

import java.util.Date;

/**
 * Created by filip on 05/07/2017.
 */

public class SemVitimCondutor extends SemVitim {

    public int genero;
    public  Date idade;

    public SemVitimCondutor(int veiculo, int condutorPresente, int genero, Date idade) {
        super(veiculo, condutorPresente);
        this.genero = genero;
        this.idade = idade;

    }
}
