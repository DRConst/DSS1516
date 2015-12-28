package AAE;

import java.util.HashMap;

/**
 * Created by drcon on 23/12/2015.
 */
public class MapaEleitoral {
    int deputados, eleitores;
    String circulo;

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


    public MapaEleitoral(String circulo, int deputados, int eleitores) {
        this.deputados = deputados;
        this.eleitores = eleitores;
        this.circulo = circulo;
    }

    public MapaEleitoral() {
        this.deputados = 0;
        this.eleitores = 0;
        this.distrito = "NA";
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

    public String getCirculo() {
        return circulo;
    }

    public void setCirculo(String circulo) {
        this.circulo = circulo;
    }

    public HashMap<Lista, Integer> getVotosPorLista() {
        return votosPorLista;
    }

    public void setVotosPorLista(HashMap<Lista, Integer> votosPorLista) {
        this.votosPorLista = votosPorLista;
    }
}
