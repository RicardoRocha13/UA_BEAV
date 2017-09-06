package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by filip on 07/07/2017.
 */

public class CondIntervenientes1Carta extends CondInterveniente1 {

    public int paisEmissao, anoHabilitação;

    public CondIntervenientes1Carta(int idVeiculo, int genero, Date idade, int licencaCarta,
                                    int testeAlcool, float valorAlcool, ArrayList<Integer> outrosFactores, int tempoCondução,
                                    int paisEmissao, int anoHabilitação) {

        super(idVeiculo, genero, idade, licencaCarta, testeAlcool, valorAlcool, outrosFactores, tempoCondução);

        this.paisEmissao = paisEmissao;
        this.anoHabilitação = anoHabilitação;

    }
}
