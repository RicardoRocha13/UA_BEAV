package pt.rrochaua.ua_beav.helpers;

/**
 * Created by filip on 06/07/2017.
 */

public class VeicIntervenienteTipo extends VeicInterveniente {

    public int tipo;

    public VeicIntervenienteTipo(int fuga, int categoriaClasse, int tipoServico, int anoMatricula,
                                 int inspeccaoPeriodica, int mercadoriasPerigosas, int cargaLotacao,
                                 int pneus, int tacografo, int seguro, int incendioPosterior,
                                 int nPassageiros, int condutorPresente, int tipo) {

        super(fuga, categoriaClasse, tipoServico, anoMatricula, inspeccaoPeriodica,
                mercadoriasPerigosas, cargaLotacao, pneus, tacografo, seguro, incendioPosterior,
                nPassageiros, condutorPresente);

        this.tipo = tipo;
    }
}
