package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 06/07/2017.
 */

public class VeicIntervinienteMercPerigosa extends VeicInterveniente {

    public int certificadoADR, materiaObjetoPerigoso;

    public VeicIntervinienteMercPerigosa(int fuga, int categoriaClasse, int tipoServico,
                                         int anoMatricula, int inspeccaoPeriodica, int mercadoriasPerigosas,
                                         int cargaLotacao, int pneus, int tacografo, int seguro,
                                         int incendioPosterior, int nPassageiros, int condutorPresente,
                                         int certificadoADR, int materiaObjetoPerigoso) {

        super(fuga, categoriaClasse, tipoServico, anoMatricula, inspeccaoPeriodica,
                mercadoriasPerigosas, cargaLotacao, pneus, tacografo, seguro, incendioPosterior,
                nPassageiros, condutorPresente);

        this.certificadoADR = certificadoADR;
        this.materiaObjetoPerigoso = materiaObjetoPerigoso;

    }
}
