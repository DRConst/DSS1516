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

    static HashMap<String, Integer> circulos = new HashMap<String, Integer> (){{
        circulos.put("Aveiro", 16);
        circulos.put("Beja", 3);
        circulos.put("Braga", 19);
        circulos.put("Bragança", 3);
        circulos.put("Castelo Branco", 4);
        circulos.put("Coimbra", 10);
        circulos.put("Évora", 3);
        circulos.put("Faro", 8);
        circulos.put("Guarda", 4);
        circulos.put("Leiria", 10);
        circulos.put("Lisboa", 47);
        circulos.put("Portalegre", 2);
        circulos.put("Porto",39);
        circulos.put("Santarém", 10);
        circulos.put("Setubál", 17);
        circulos.put("Viana do Castelo", 6);
        circulos.put("Vila Real", 5);
        circulos.put("Viseu", 9);
        circulos.put("Madeira", 9);
        circulos.put("Açores", 9);
    }};


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
