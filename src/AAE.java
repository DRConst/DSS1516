import java.util.GregorianCalendar;

/**
 * Created by drcon on 15/12/2015.
 */
public class AAE {

    private EleicaoDAO eleicaoDAO;
    private CandidaturaDAO candidaturaDAO;
    private CirculoEleitoralDAO circuloEleitoralDAO;

    public void adicionarCandidato(Eleicao eleicao, GregorianCalendar data_bi, int bi, String arquivo, String filicao, String nome, String profissao, int idade, String morada, String nacionalidade, GregorianCalendar data)
    {
        Candidato c = new Candidato(data_bi, bi, filicao, arquivo, nome, profissao, idade, morada, nacionalidade);
        Candidatura can = new Candidatura(candidaturaDAO.getAvailableId(), data, c, eleicao);
        boolean valid = can.validarCandidatura();
        candidaturaDAO.addCandidatura(can);

    }

    public void adicionarCirclo(String distrito, int eleitores, int deputados) throws DistritoInvalidoException {
        if(!Distritios.distiritos.contains(distrito))
            throw new DistritoInvalidoException();
        CirculoEleitoral c = new CirculoEleitoral(distrito, eleitores,deputados);
        circuloEleitoralDAO.addCirculo(c);
    }

    public static void main(String[] args) {

    }
}
