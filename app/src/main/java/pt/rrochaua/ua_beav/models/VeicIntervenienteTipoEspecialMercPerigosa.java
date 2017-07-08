package pt.rrochaua.ua_beav.models;

/**
 * Created by filip on 07/07/2017.
 */

public class VeicIntervenienteTipoEspecialMercPerigosa extends VeicIntervenienteTipoEspecial {

    public int certificadoADR, materiaObjetoPerigoso;

    public VeicIntervenienteTipoEspecialMercPerigosa(int fuga, int categoriaClasse, int tipoServico,
                                                     int anoMatricula, int inspeccaoPeriodica,
                                                     int mercadoriasPerigosas, int cargaLotacao,
                                                     int pneus, int tacografo, int seguro,
                                                     int incendioPosterior, int nPassageiros,
                                                     int condutorPresente, int tipo, int especial,
                                                     int certificadoADR, int materiaObjetoPerigoso) {

        super(fuga, categoriaClasse, tipoServico, anoMatricula, inspeccaoPeriodica,
                mercadoriasPerigosas, cargaLotacao, pneus, tacografo, seguro, incendioPosterior,
                nPassageiros, condutorPresente, tipo, especial);

        this.certificadoADR = certificadoADR;
        this.materiaObjetoPerigoso = materiaObjetoPerigoso;
    }
}
