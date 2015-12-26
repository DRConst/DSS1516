import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by drcon on 15/12/2015.
 */
public class AAE {

    private EleicaoDAO eleicaoDAO;
    private CandidaturaDAO candidaturaDAO;
    private CirculoEleitoralDAO circuloEleitoralDAO;
    private ListaDAO listaDAO;
    private EleitorDAO eleitorDAO;

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

    public void adicionarLista(String nome, ArrayList<Eleitor> deputados, ArrayList<Eleitor> delegados)
    {
        Lista l = new Lista(nome, deputados,delegados);

        try {
            validarLista(l);
        } catch (DeputadoJaPertenceAListaException e) {
            e.printStackTrace();
        } catch (DelegadoJaPertenceAListaException e) {
            e.printStackTrace();
        } catch (NomeDeListaRepetidoException e) {
            e.printStackTrace();
        }

        listaDAO.addLista(l);
    }

    public void validarLista(Lista l) throws DeputadoJaPertenceAListaException, DelegadoJaPertenceAListaException, NomeDeListaRepetidoException {
        ArrayList<Lista> listas = listaDAO.getListas();

        for(Lista lista : listas)
        {
            ArrayList<Eleitor> deputados = l.getDeputados();
            for(Eleitor e : deputados)
            {
                if(lista.temDeputado(e))
                {
                    throw new DeputadoJaPertenceAListaException();
                }
            }

            for(Eleitor e : l.getDelegados())
            {
                if(lista.temDelegado(e))
                {
                    throw new DelegadoJaPertenceAListaException();
                }
            }

            if(lista.getNome().equals(l.getNome()))
            {
                throw new NomeDeListaRepetidoException();
            }
        }
    }

    public void adicionarVotantes(String path) throws FileNotFoundException, IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        String line;
        while(( line = reader.readLine()) != null)
        {
            sb.append(line);
        }
        reader.close();
        eleitorDAO = new EleitorDAO(sb.toString()); //SQL to create the table
        
    }


    public void adicionarAssembleia(String e, String freg, String presNome, int presID,String vPresNome, int vPresID, String secNome, int secID, ArrayList<String> escNomes, ArrayList<Integer> escIDs  )
    {
        Eleicao el = eleicaoDAO.getEleicao(e);

        Eleitor pres = EleitorDAO.getEleitor(presID);

        Eleitor vPres = EleitorDAO.getEleitor(vPresID);

        Eleitor sec = EleitorDAO.getEleitor(secID);

        ArrayList<Eleitor> esc = new ArrayList<>();

        for(Integer i : escIDs)
        {
            Eleitor eleitor = EleitorDAO.getEleitor(i);
            esc.add(eleitor);
        }

        AssembleiaDeVoto assembleiaDeVoto = new AssembleiaDeVoto(freg, pres, vPres, sec, esc);

        el.registarAssembleia(assembleiaDeVoto);


    }


    public static void main(String[] args) {

    }
}
