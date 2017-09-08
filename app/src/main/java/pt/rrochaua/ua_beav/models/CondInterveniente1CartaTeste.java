package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by filip on 07/07/2017.
 */

public class CondInterveniente1CartaTeste extends CondIntervenientes1Carta {

    public float valorAlcool;

    public CondInterveniente1CartaTeste(int idVeiculo, int genero, Date idade, int licencaCarta,
                                        int testeAlcool, ArrayList<Integer> outrosFactores, int tempoCondução,
                                        int paisEmissao, int anoHabilitação, float valorAlcool) {

        super(idVeiculo, genero, idade, licencaCarta, testeAlcool, outrosFactores, tempoCondução,
                paisEmissao, anoHabilitação);

        this.valorAlcool = valorAlcool;
    }
}