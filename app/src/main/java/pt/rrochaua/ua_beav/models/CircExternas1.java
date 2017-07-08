package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 08/07/2017.
 */

public class CircExternas1 {

    public int tipoVia, nVias, viaTransito, tracadoViaPlanta,tracadoViaPerfil;
    public int tracadoViaBerma, situacaoAcidente, intersecVias, acidenteObrasArte;
    public int faixaRodagem, limiteVelocGeral, limiteVelocLocal;

    public CircExternas1(int tipoVia, int nVias, int viaTransito, int tracadoViaPlanta,
                         int tracadoViaPerfil, int tracadoViaBerma, int situacaoAcidente,
                         int intersecVias, int acidenteObrasArte, int faixaRodagem,
                         int limiteVelocGeral, int limiteVelocLocal){

        this.tipoVia = tipoVia;
        this.nVias = nVias;
        this.viaTransito = viaTransito;
        this.tracadoViaPlanta = tracadoViaPlanta;
        this.tracadoViaPerfil = tracadoViaPerfil;
        this.tracadoViaBerma = tracadoViaBerma;
        this.situacaoAcidente = situacaoAcidente;
        this.intersecVias = intersecVias;
        this.acidenteObrasArte = acidenteObrasArte;
        this.faixaRodagem = faixaRodagem;
        this.limiteVelocGeral = limiteVelocGeral;
        this.limiteVelocLocal = limiteVelocLocal;

    }
}
