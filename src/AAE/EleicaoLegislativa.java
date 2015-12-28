package AAE;

import DAOs.EleicaoDAO;
import DAOs.MapaEleitoralDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by drcon on 28/12/2015.
 */
public class EleicaoLegislativa extends Eleicao {
    
    public AssembleiaDeVoto assembleias;
    public MapaEleitoralDAO mapas;
   
    MapaEleitoralDAO mapaEleitoralDAO;
    public HashMap<MapaEleitoral, ListaVotos> gerarEstatisticas()
    {
        Collection<MapaEleitoral> ma = mapas.getMapasPorEleicao();
        HashMap<MapaEleitoral, ListaVotos> toRet = new HashMap<>();
        for(MapaEleitoral mapa : mapas)
        {
            for(Lista l : mapa.getListas())
                toRet.put(mapa, new ListaVotos(l,l.getVotosPorEleicao(this.nome)));
        }
        return toRet;
    }
}
