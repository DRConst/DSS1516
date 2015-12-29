package AAE;


import DAOs.AssembleiaDeVotoDAO;
import DAOs.EleitorDAO;

import java.util.Date;

/**
 * Created by drcon on 23/12/2015.
 */
public class Eleicao {
    public String Nome;
    public Date data;
    public EleitorDAO votantes;
    String tipo;

    public Eleicao(String nome, Date data, String tipo) {
        Nome = nome;
        this.data = data;
        this.tipo = tipo;
    }

    public void registarAssembleia(AssembleiaDeVoto assembleiaDeVoto)
    {
        new AssembleiaDeVotoDAO().put(assembleiaDeVoto.getCodigo(), assembleiaDeVoto);
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public EleitorDAO getVotantes() {
        return votantes;
    }

    public void setVotantes(EleitorDAO votantes) {
        this.votantes = votantes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
