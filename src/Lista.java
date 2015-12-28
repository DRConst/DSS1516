import java.util.ArrayList;

/**
 * Created by drcon on 23/12/2015.
 */
public class Lista {
    private String nome;
    private ArrayList<Eleitor> deputados;
    private ArrayList<Eleitor> delegados;

    public Lista(String nome, ArrayList<Eleitor> deputados, ArrayList<Eleitor> delegados) {
        this.nome = nome;
        this.deputados = deputados;
        this.delegados = delegados;
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
    
}
