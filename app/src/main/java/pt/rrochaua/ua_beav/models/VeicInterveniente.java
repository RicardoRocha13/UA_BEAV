package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 06/07/2017.
 */

public class VeicInterveniente {

    public int fuga, categoriaClasse, tipoServico, anoMatricula, inspeccaoPeriodica;
    public int mercadoriasPerigosas, cargaLotacao, pneus, tacografo, seguro, incendioPosterior;
    public int nPassageiros, condutorPresente;

    public VeicInterveniente(int fuga, int categoriaClasse, int tipoServico, int anoMatricula,
                             int inspeccaoPeriodica, int mercadoriasPerigosas, int cargaLotacao,
                             int pneus, int tacografo, int seguro, int incendioPosterior,
                             int nPassageiros, int condutorPresente){

        this.fuga = fuga;
        this.categoriaClasse = categoriaClasse;
        this.tipoServico = tipoServico;
        this.anoMatricula = anoMatricula;
        this.inspeccaoPeriodica = inspeccaoPeriodica;
        this.mercadoriasPerigosas = mercadoriasPerigosas;
        this.cargaLotacao = cargaLotacao;
        this.pneus = pneus;
        this.tacografo = tacografo;
        this.seguro = seguro;
        this.incendioPosterior = incendioPosterior;
        this.nPassageiros = nPassageiros;
        this.condutorPresente = condutorPresente;

    }
}
