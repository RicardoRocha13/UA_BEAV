package pt.rrochaua.ua_beav.models;

import java.util.Date;

/**
 * Created by filip on 08/07/2017.
 */

public class Form1Model {

    public Date dataHora;
    public int localizacao, tipoAcidente, nPeoesVitimas,  naturezaAcidente, nVeiculos;
    public String local;
    public float coordLat, coordLon;

    public Form1Model(Date dataHora, int localizacao, String local, float coordLat, float coordLon,
                      int tipoAcidente, int nPeoesVitimas, int naturezaAcidente, int nVeiculos){

        this.dataHora = dataHora;
        this.localizacao = localizacao;
        this.local = local;
        this.coordLat = coordLat;
        this.coordLon = coordLon;
        this.tipoAcidente = tipoAcidente;
        this.nPeoesVitimas = nPeoesVitimas;
        this.naturezaAcidente = naturezaAcidente;
        this.nVeiculos = nVeiculos;

    }
}
