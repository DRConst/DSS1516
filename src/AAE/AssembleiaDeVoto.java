package AAE;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by drcon on 26/12/2015.
 */
public class AssembleiaDeVoto {
    private String codigo;
    private String eleicao;
    private String concelho;
    private String freguesia;
    private Date Habertura;
    private Date Hencerramento;
    private String Local;
    private Integer nrEleitores;
    private Integer nrVotantes;
    private Integer VotosBrancos;
    private Integer VotosNulos;
    private Integer nrReclamacoes;
    private ArrayList<Eleitor> responsaveis;

    public AssembleiaDeVoto(String codigo, String eleicao, String concelho, String freguesia, Date habertura, Date hencerramento, String local, Integer nrEleitores, Integer nrVotantes, Integer votosBrancos, Integer votosNulos, Integer nrReclamacoes, ArrayList<Eleitor> responsaveis) {
        this.codigo = codigo;
        this.eleicao = eleicao;
        this.concelho = concelho;
        this.freguesia = freguesia;
        Habertura = habertura;
        Hencerramento = hencerramento;
        Local = local;
        this.nrEleitores = nrEleitores;
        this.nrVotantes = nrVotantes;
        VotosBrancos = votosBrancos;
        VotosNulos = votosNulos;
        this.nrReclamacoes = nrReclamacoes;
        this.responsaveis = responsaveis;
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

    public Date getHabertura() {
        return Habertura;
    }

    public void setHabertura(Date Habertura) {
        this.Habertura = Habertura;
    }

    public Date getHencerramento() {
        return Hencerramento;
    }

    public void setHencerramento(Date Hencerramento) {
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


    public ArrayList<Eleitor> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(ArrayList<Eleitor> responsaveis) {
        this.responsaveis = responsaveis;
    }
}
