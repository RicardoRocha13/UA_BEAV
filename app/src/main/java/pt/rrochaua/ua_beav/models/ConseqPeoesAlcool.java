package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;

/**
 * Created by filip on 07/07/2017.
 */

public class ConseqPeoesAlcool extends ConseqPeoes {

    public float taxaAlcolemia;

    public ConseqPeoesAlcool(int genero, int idade, int posicao, int acoes,
                             int utilizacaoMaterialRefletor, int grauGravidadeLesoes,
                             ArrayList<Integer> condiPsicoFisicas, float taxaAlcolemia) {

        super(genero, idade, posicao, acoes, utilizacaoMaterialRefletor, grauGravidadeLesoes,
                condiPsicoFisicas);

        this.taxaAlcolemia = taxaAlcolemia;

    }
}
