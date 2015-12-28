package AAE;

import java.util.HashMap;

/**
 * Created by drcon on 23/12/2015.
 */
public class MapaEleitoral {
    int deputados, eleitores;
    String distrito;

    HashMap<Lista, Integer> votosPorLista;

    public MapaEleitoral(String distrito, int deputados, int eleitores) {
        this.deputados = deputados;
        this.eleitores = eleitores;
        this.distrito = distrito;
    }

    public MapaEleitoral() {
    }
}
