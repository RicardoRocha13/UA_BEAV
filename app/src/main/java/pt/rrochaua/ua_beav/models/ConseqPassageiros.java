package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 07/07/2017.
 */

public class ConseqPassageiros {
    public int idVeiculo, genero, idade, posicaoVeiculo, usoAcessorioSeguranca, grauGraviadeLesoes;

    public ConseqPassageiros(int idVeiculo, int genero, int idade, int posicaoVeiculo,
                             int usoAcessorioSeguranca, int grauGraviadeLesoes){

        this.idVeiculo = idVeiculo;
        this.genero = genero;
        this.idade = idade;
        this.posicaoVeiculo = posicaoVeiculo;
        this.usoAcessorioSeguranca = usoAcessorioSeguranca;
        this.grauGraviadeLesoes = grauGraviadeLesoes;

    }
}
