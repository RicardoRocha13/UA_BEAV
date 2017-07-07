package pt.rrochaua.ua_beav.helpers;

import java.util.Date;

/**
 * Created by filip on 07/07/2017.
 */

public class CondInterveniente1 {

    public int idVeiculo, genero, licencaCarta, testeAlcool, outrosFactores, tempoCondução;
    Date idade;

    public CondInterveniente1(int idVeiculo, int genero, Date idade, int licencaCarta,
                              int testeAlcool, int outrosFactores, int tempoCondução){

        this.idVeiculo = idVeiculo;
        this.genero = genero;
        this.idade = idade;
        this.licencaCarta = licencaCarta;
        this.testeAlcool = testeAlcool;
        this.outrosFactores = outrosFactores;
        this.tempoCondução = tempoCondução;

    }

}
