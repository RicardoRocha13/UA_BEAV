package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by filip on 07/07/2017.
 */

public class CondInterveniente1Teste extends CondInterveniente1 {

    public float valorAlcool;

    public CondInterveniente1Teste(int idVeiculo, int genero, Date idade, int licencaCarta,
                                   int testeAlcool, ArrayList<Integer> outrosFactores, int tempoCondução,
                                   float valorAlcool) {

        super(idVeiculo, genero, idade, licencaCarta, testeAlcool, outrosFactores, tempoCondução);

        this.valorAlcool = valorAlcool;
    }
}