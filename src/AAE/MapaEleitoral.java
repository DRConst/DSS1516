package AAE;

import java.util.HashMap;

/**
 * Created by drcon on 23/12/2015.
 */
public class MapaEleitoral {
    int deputados, eleitores;
    String distrito;



    String eleicao;
    Integer id;

    HashMap<Lista, Integer> votosPorLista;


    public MapaEleitoral(Integer id,String distrito, int deputados, int eleitores, String eleicao) {
        this.id = id;
        this.deputados = deputados;
        this.eleitores = eleitores;
        this.distrito = distrito;
        this.eleicao = eleicao;
    }


    public int getDeputados() {
        return deputados;
    }

    public void setDeputados(int deputados) {
        this.deputados = deputados;
    }

    public int getEleitores() {
        return eleitores;
    }

    public void setEleitores(int eleitores) {
        this.eleitores = eleitores;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public HashMap<Lista, Integer> getVotosPorLista() {
        return votosPorLista;
    }

    public void setVotosPorLista(HashMap<Lista, Integer> votosPorLista) {
        this.votosPorLista = votosPorLista;
    }

    public String getEleicao() {
        return eleicao;
    }

    public void setEleicao(String eleicao) {
        this.eleicao = eleicao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
