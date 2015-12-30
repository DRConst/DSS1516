package AAE;

import DAOs.CandidaturaDAO;
import DAOs.EleicaoDAO;
import DAOs.EleitorDAO;
import DAOs.ListaDAO;
import DAOs.MapaEleitoralDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by drcon on 15/12/2015.
 */
public class AAE {

    private EleicaoDAO eleicaoDAO;
    private CandidaturaDAO candidaturaDAO;
    private MapaEleitoralDAO mapaEleitoralDAO;
    private EleitorDAO eleitorDAO;
    private ListaDAO listaDAO;


    public AAE() {
        eleitorDAO = new EleitorDAO();
        eleicaoDAO = new EleicaoDAO();
        candidaturaDAO = new CandidaturaDAO();
        mapaEleitoralDAO = new MapaEleitoralDAO();
        listaDAO = new ListaDAO();
    }

    public void adicionarEleicao(String eleicao, java.sql.Date d, String tipo){
        Eleicao e = new Eleicao(eleicao, d, tipo);
        eleicaoDAO.put(eleicao, e);       
    }
    
    public void adicionarCandidato(String eleicao,Date data_bi, int bi, String arquivo, String filicao, String nome, String profissao, int idade, String morada, String nacionalidade,Date data) throws AssinaturasInsuficientesExceptions, CandidatoEstrangeiroException, CandidaturaTardiaException, CandidatoDemasiadoNovoException {
        
        Candidato c = new Candidato(data_bi, bi, filicao, arquivo, nome, profissao, idade, morada, nacionalidade);
        Candidatura can = new Candidatura(candidaturaDAO.getAvailableId(), data, c, eleicao);
        Eleicao el = eleicaoDAO.get(eleicao);
        boolean valid = can.validarCandidatura(el.getData());
        if(valid) {
            Integer id = can.getID();
            candidaturaDAO.put(id.toString(), can);
        }
    }

    public void adicionarMapa(String distrito, int eleitores, int deputados, String eleicao) throws DistritoInvalidoException {
        if(!MapaEleitoral.circulos.containsKey(distrito))
            throw new DistritoInvalidoException();
        MapaEleitoral c = new MapaEleitoral(mapaEleitoralDAO.getAvailableId(), distrito, eleitores,deputados, eleicao);
        mapaEleitoralDAO.put(c.id, c);
    }

    public void adicionarLista(String eleicao, String nome, String circulo, ArrayList<Integer> deputados, ArrayList<Integer> delegados)
    {


        ArrayList<Eleitor> deps = new ArrayList<>();

        for(Integer i : deputados)
        {
            Eleitor eleitor = eleitorDAO.get(i);
            deps.add(eleitor);
        }

        ArrayList<Eleitor> dels = new ArrayList<>();

        for(Integer i : delegados)
        {
            Eleitor eleitor = eleitorDAO.get(i);
            dels.add(eleitor);
        }

        Lista l = new Lista(nome, deps,dels);

        try {
            validarLista(l);
        } catch (DeputadoJaPertenceAListaException e) {
            e.printStackTrace();
        } catch (DelegadoJaPertenceAListaException e) {
            e.printStackTrace();
        } catch (NomeDeListaRepetidoException e) {
            e.printStackTrace();
        }

        EleicaoLegislativa e = (EleicaoLegislativa)eleicaoDAO.get(eleicao);

        e.registarLista(l);
    }

    public void validarLista(Lista l) throws DeputadoJaPertenceAListaException, DelegadoJaPertenceAListaException, NomeDeListaRepetidoException {
        ArrayList<Lista> listas = new ArrayList<>(listaDAO.values());

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
        eleitorDAO = new EleitorDAO(sb.toString());
        
    }


    public void adicionarAssembleia(String e, String codigo, String eleicao, String concelho, String freguesia, Date habertura, Date hencerramento, String local, Integer nrEleitores,  ArrayList<String> responsaveis  )
    {
        Eleicao el = eleicaoDAO.get(e);

        ArrayList<Eleitor> resp = new ArrayList<>();

        for(String i : responsaveis)
        {
            Eleitor eleitor = eleitorDAO.get(i);
            resp.add(eleitor);
        }

        AssembleiaDeVoto assembleiaDeVoto = new AssembleiaDeVoto(codigo, e, concelho, freguesia, habertura, hencerramento, local, nrEleitores, resp);

        el.registarAssembleia(assembleiaDeVoto);


    }


    public void atribuirMandatos(String eleicao)
    {
        EleicaoLegislativa e = (EleicaoLegislativa)eleicaoDAO.get(eleicao);

        e.atribuirMandatos();
    }


    public void gerarEstatisticas(Eleicao e)
    {

        ((EleicaoLegislativa)e).gerarEstatisticas();

    }

    public void registarEleicao(String nome, boolean isLeg, java.sql.Date date) throws EleicaoJaRegistadaException {
        if(eleicaoDAO.containsKey(nome))
            throw new EleicaoJaRegistadaException();


        if(isLeg)
        {
            EleicaoLegislativa eleicaoLegislativa = new EleicaoLegislativa(nome, date);
            eleicaoDAO.put(nome, eleicaoLegislativa);
        }else
        {
            EleicaoPresidencial eleicaoPresidencial = new EleicaoPresidencial(nome, date);
            eleicaoDAO.put(nome, eleicaoPresidencial);
        }
    }

    public ArrayList<Eleicao> getEleicaoDAO() {
        return new ArrayList<Eleicao>(eleicaoDAO.values()) {};
    }

    public ArrayList<Candidatura> getCandidaturaDAO() {
        return new ArrayList<Candidatura>(candidaturaDAO.values());
    }

    public  ArrayList<MapaEleitoral> getMapaEleitoralDAO() {
        return new ArrayList<MapaEleitoral>(mapaEleitoralDAO.values());
    }

    public  ArrayList<Eleitor> getEleitorDAO() {
        return new ArrayList<Eleitor>(eleitorDAO.values());
    }

    public  ArrayList<Lista> getListaDAO() {
        return new ArrayList<Lista>(listaDAO.values());
    }
    
    

    public static void main(String[] args) {

    }
}
