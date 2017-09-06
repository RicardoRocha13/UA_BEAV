package pt.rrochaua.ua_beav.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by filip on 07/07/2017.
 */

public class CondInterveniente1 {

    public int idVeiculo, genero, licencaCarta, testeAlcool, tempoCondução;
    public float valorAlcool;
    Date idade;
    public ArrayList<Integer> outrosFactores = new ArrayList<>();

    public CondInterveniente1(int idVeiculo, int genero, Date idade, int licencaCarta,
                              int testeAlcool, float valorAlcool, ArrayList<Integer> outrosFactores, int tempoCondução){

        this.idVeiculo = idVeiculo;
        this.genero = genero;
        this.idade = idade;
        this.licencaCarta = licencaCarta;
        this.testeAlcool = testeAlcool;
        this.valorAlcool = valorAlcool;
        this.outrosFactores = outrosFactores;
        this.tempoCondução = tempoCondução;

    }

}
