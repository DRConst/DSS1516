import java.util.GregorianCalendar;

/**
 * Created by drcon on 15/12/2015.
 */
public class AAE {

    private EleicaoDAO eleicaoDAO;
    private CandidaturaDAO candidaturaDAO;

    public void adicionarCandidato(String eleicao, GregorianCalendar data_bi, int bi, String arquivo, String filicao, String nome, String profissao, int idade, String morada, String nacionalidade, GregorianCalendar data)
    {
        Candidato c = new Candidato(data_bi, bi, filicao, arquivo, nome, profissao, idade, morada, nacionalidade);
        Eleicao  e = eleicaoDAO.getEleicao(eleicao);
        Candidatura can = new Candidatura(candidaturaDAO.getAvailableId(), data, c, e);
        boolean valid = can.validarCandidatura();
        candidaturaDAO.addCandidatura(can);

    }

    public static void main(String[] args) {

    }
}
