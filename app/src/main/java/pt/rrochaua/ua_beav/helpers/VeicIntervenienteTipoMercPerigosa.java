package pt.rrochaua.ua_beav.helpers;

/**
 * Created by filip on 07/07/2017.
 */

public class VeicIntervenienteTipoMercPerigosa extends VeicIntervenienteTipo {

    public int certificadoADR, materiaObjetoPerigoso;

    public VeicIntervenienteTipoMercPerigosa(int fuga, int categoriaClasse, int tipoServico,
                                             int anoMatricula, int inspeccaoPeriodica,
                                             int mercadoriasPerigosas, int cargaLotacao, int pneus,
                                             int tacografo, int seguro, int incendioPosterior,
                                             int nPassageiros, int condutorPresente, int tipo,
                                             int certificadoADR, int materiaObjetoPerigoso) {

        super(fuga, categoriaClasse, tipoServico, anoMatricula, inspeccaoPeriodica,
                mercadoriasPerigosas, cargaLotacao, pneus, tacografo, seguro, incendioPosterior,
                nPassageiros, condutorPresente, tipo);

        this.certificadoADR = certificadoADR;
        this.materiaObjetoPerigoso = materiaObjetoPerigoso;
    }
}
