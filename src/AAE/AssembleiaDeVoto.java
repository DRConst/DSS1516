package AAE;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by drcon on 26/12/2015.
 */
public class AssembleiaDeVoto {
    private String codigo;
    private String eleicao;
    private String concelho;
    private String freguesia;
    private GregorianCalendar Habertura;
    private GregorianCalendar Hencerramento;
    private String Local;
    private Integer nrEleitores;
    private Integer nrVotantes;
    private Integer VotosBrancos;
    private Integer VotosNulos;
    private Integer nrReclamacoes;
    private Eleitor presidente;
    private Eleitor Vpresidente;
    private Eleitor secretario;
    private ArrayList<Eleitor> escrutinadores;
    private ArrayList<Eleitor> eleitores;
    
    
    public AssembleiaDeVoto(String freg, Eleitor pres, Eleitor vPres, Eleitor sec, ArrayList<Eleitor> esc) {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public GregorianCalendar getHabertura() {
        return Habertura;
    }

    public void setHabertura(GregorianCalendar Habertura) {
        this.Habertura = Habertura;
    }

    public GregorianCalendar getHencerramento() {
        return Hencerramento;
    }

    public void setHencerramento(GregorianCalendar Hencerramento) {
        this.Hencerramento = Hencerramento;
    }

    public String getLocal() {
        return Local;
    }

    public void setLocal(String Local) {
        this.Local = Local;
    }

    public Integer getNrEleitores() {
        return nrEleitores;
    }

    public void setNrEleitores(Integer nrEleitores) {
        this.nrEleitores = nrEleitores;
    }

    public Integer getNrVotantes() {
        return nrVotantes;
    }

    public void setNrVotantes(Integer nrVotantes) {
        this.nrVotantes = nrVotantes;
    }

    public Integer getVotosBrancos() {
        return VotosBrancos;
    }

    public void setVotosBrancos(Integer VotosBrancos) {
        this.VotosBrancos = VotosBrancos;
    }

    public Integer getVotosNulos() {
        return VotosNulos;
    }

    public void setVotosNulos(Integer VotosNulos) {
        this.VotosNulos = VotosNulos;
    }

    public Integer getNrReclamacoes() {
        return nrReclamacoes;
    }

    public void setNrReclamacoes(Integer nrReclamacoes) {
        this.nrReclamacoes = nrReclamacoes;
    }

    public Eleitor getPresidente() {
        return presidente;
    }

    public void setPresidente(Eleitor presidente) {
        this.presidente = presidente;
    }

    public Eleitor getVpresidente() {
        return Vpresidente;
    }

    public void setVpresidente(Eleitor Vpresidente) {
        this.Vpresidente = Vpresidente;
    }

    public Eleitor getSecretario() {
        return secretario;
    }

    public void setSecretario(Eleitor secretario) {
        this.secretario = secretario;
    }

    public ArrayList<Eleitor> getEscrutinadores() {
        return escrutinadores;
    }

    public void setEscrutinadores(ArrayList<Eleitor> escrutinadores) {
        this.escrutinadores = escrutinadores;
    }

    public ArrayList<Eleitor> getEleitores() {
        return eleitores;
    }

    public void setEleitores(ArrayList<Eleitor> eleitores) {
        this.eleitores = eleitores;
    }
    
}
