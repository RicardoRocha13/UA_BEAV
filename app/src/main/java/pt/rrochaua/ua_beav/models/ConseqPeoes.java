package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;

/**
 * Created by filip on 07/07/2017.
 */

public class ConseqPeoes {

    public int genero, idade, posicao, acoes, utilizacaoMaterialRefletor, grauGravidadeLesoes;
    public ArrayList<Integer> condiPsicoFisicas = new ArrayList<Integer>();
    public float taxaAlcolemia;

    public  ConseqPeoes(int genero, int idade, int posicao, int acoes,
                        int utilizacaoMaterialRefletor, int grauGravidadeLesoes,
                        ArrayList<Integer> condiPsicoFisicas, float taxaAlcolemia){

        this.genero = genero;
        this.idade = idade;
        this.posicao = posicao;
        this.acoes = acoes;
        this.utilizacaoMaterialRefletor = utilizacaoMaterialRefletor;
        this.grauGravidadeLesoes = grauGravidadeLesoes;
        this.condiPsicoFisicas = condiPsicoFisicas;
        this.taxaAlcolemia = taxaAlcolemia;

    }
}
