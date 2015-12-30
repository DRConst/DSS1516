package AAE;

import DAOs.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        Eleicao e;
        if(tipo.equals("presidenciais"))
        {
            e = new EleicaoPresidencial(eleicao, d);
        }else
        {
            e = new EleicaoLegislativa(eleicao, d);
        }
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

    public ArrayList<String> getFreguesias(String c, String e)
    {
        return new AssembleiaDeVotoDAO().getFreguesiasByConcelho(c, e);
    }
    public ArrayList<String> getConcelhos(String e)
    {
        return new AssembleiaDeVotoDAO().getConcelhos(e);
    }

    public void actualizarAssembleia(String e, String concelho, String freguesia,String p, String vp, String s,  ArrayList<String> responsaveis  ) throws AssembleiaInexistenteException {
        Eleicao el = eleicaoDAO.get(e);

        ArrayList<Eleitor> resp = new ArrayList<>();
        Eleitor pres = eleitorDAO.get(p);
        pres.setCargoVP(Eleitor.presidenteType);
        Eleitor vpres = eleitorDAO.get(vp);
        vpres.setCargoVP(Eleitor.vPresidenteType);
        Eleitor sec = eleitorDAO.get(s);
        sec.setCargoVP(Eleitor.secType);


        for(String i : responsaveis)
        {
            Eleitor eleitor = eleitorDAO.get(i);
            eleitor.setCargoVP(Eleitor.escType);
            resp.add(eleitor);
        }
        resp.add(pres);
        resp.add(vpres);
        resp.add(sec);

        AssembleiaDeVoto assembleiaDeVoto = new AssembleiaDeVotoDAO().getByFreg(e, freguesia);

        if (assembleiaDeVoto == null) {
            throw new AssembleiaInexistenteException();
        }

        assembleiaDeVoto.setResponsaveis(resp);

        el.registarAssembleia(assembleiaDeVoto);


    }

    public void adicionarTemplateAssembleia(String codigo, String eleicao, String concelho, String freguesia, String habertura, String hencerramento, String local)
    {
        new AssembleiaDeVotoDAO().putTemplate(codigo,eleicao, concelho, freguesia, habertura, hencerramento, local);
    }

    public void atribuirMandatos(String eleicao)
    {
        EleicaoLegislativa e = (EleicaoLegislativa)eleicaoDAO.get(eleicao);

        e.atribuirMandatos();
    }


    public Double gerarEstatisticasLegislativas(String e,String m, String l)
    {
        EleicaoLegislativa el = (EleicaoLegislativa)eleicaoDAO.get(e);
        HashMap<MapaEleitoral, HashMap<Lista, Integer>> est = (el.gerarEstatisticas());
        MapaEleitoral mapa = mapaEleitoralDAO.get(m);
        Lista lista = listaDAO.get(l);
        return new Double(est.get(mapa).get(l) / eleicaoDAO.getTotalVotos(e));

    }

    public Double gerarEstatisticasPresidenciais(String e, String c)
    {
        EleicaoPresidencial el = (EleicaoPresidencial)eleicaoDAO.get(e);
        Candidato can = new CandidatoDAO().get(c);
        return new Double(el.gerarEstatisticas().get(can) / eleicaoDAO.getTotalVotos(e)) ;

    }

    public ArrayList<String> getListas(String m, String e)
    {
        return mapaEleitoralDAO.getListas(m, e);
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

    public Eleicao getEleicaoDAO(String s) {
        return eleicaoDAO.get(s);
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
