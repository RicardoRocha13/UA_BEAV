package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 08/07/2017.
 */

public class CircExternas2 {

    public int tipoPiso, estadoConservacao, obstaculosObras, condicoesAderencia;
    public int marcasPavimentos, sinalizacaoLuminosa, sinais;
    public int luminosidade, fatoresAtmosfericos;

    public CircExternas2(int tipoPiso, int estadoConservacao, int obstaculosObras,
                         int condicoesAderencia, int marcasPavimentos, int sinalizacaoLuminosa,
                         int sinais, int luminosidade, int fatoresAtmosfericos){

        this.tipoPiso = tipoPiso;
        this.estadoConservacao = estadoConservacao;
        this.obstaculosObras = obstaculosObras;
        this.condicoesAderencia = condicoesAderencia;
        this.marcasPavimentos = marcasPavimentos;
        this.sinalizacaoLuminosa = sinalizacaoLuminosa;
        this.sinais = sinais;
        this.luminosidade = luminosidade;
        this.fatoresAtmosfericos = fatoresAtmosfericos;

    }


}
