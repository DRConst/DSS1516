package AAE;

import java.util.ArrayList;

/**
 * Created by drcon on 23/12/2015.
 */
public class Lista {
    private String nome;
    private ArrayList<Eleitor> deputados;
    private ArrayList<Eleitor> delegados;
    private int votos;

    public Lista(String nome, ArrayList<Eleitor> deputados, ArrayList<Eleitor> delegados, int votos) {
        this.nome = nome;
        this.deputados = deputados;
        this.delegados = delegados;
        this.votos = votos;
    }
    public Lista(String nome, ArrayList<Eleitor> deputados, ArrayList<Eleitor> delegados) {
        this.nome = nome;
        this.deputados = deputados;
        this.delegados = delegados;
        this.votos = -1;
    }
    /*
    public Lista(String nome, ArrayList<Eleitor> deputados, int votos) {
        this.nome = nome;
        this.deputados = deputados;
        this.delegados = null;
        this.votos = votos;
    }*/


    public boolean temDeputado(Eleitor e)
    {
        return deputados.contains(e);
    }

    public boolean temDelegado(Eleitor e)
    {
        return delegados.contains(e);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Eleitor> getDeputados() {
        return deputados;
    }

    public void setDeputados(ArrayList<Eleitor> deputados) {
        this.deputados = deputados;
    }

    public ArrayList<Eleitor> getDelegados() {
        return delegados;
    }

    public void setDelegados(ArrayList<Eleitor> delegados) {
        this.delegados = delegados;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
