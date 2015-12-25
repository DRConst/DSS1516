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
}
