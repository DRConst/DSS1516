package AAE;

import DAOs.AssembleiaDeVotoDAO;
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
    public HashMap<MapaEleitoral,  HashMap<Lista, Integer>> gerarEstatisticas()
    {
        Collection<MapaEleitoral> map = mapas.getMapasPorEleicao();
        HashMap<MapaEleitoral, HashMap<Lista, Integer>> toRet = new HashMap<>();
        for(MapaEleitoral mapa : map)
        {
            HashMap<Lista, Integer> temp = new HashMap<>();
            for(Lista l : mapa.getListas())
            {
                temp.put(l,l.getVotosPorEleicao(this.nome));
            }
            toRet.put(mapa, temp);
        }
        return toRet;
    }

    public void registarAssembleia(AssembleiaDeVoto assembleiaDeVoto)
    {
        new AssembleiaDeVotoDAO().put(assembleiaDeVoto.getCodigo(), assembleiaDeVoto);
    }

    public HashMap<MapaEleitoral, HashMap<Lista, Integer>> atribuiMandatos()
    {
        HashMap<MapaEleitoral, HashMap<Lista, Integer>> toRet = new HashMap<>();

        HashMap<MapaEleitoral, HashMap<Lista, Integer>> res = gerarEstatisticas();

        for(MapaEleitoral mapaEleitoral : res.keySet())
        {

            HashMap<Lista, Integer> m = res.get(mapaEleitoral);
            HashMap<Lista, Integer> toAdd = new HashMap<>();
            int i = 0;
            for(; i < MapaEleitoral.circulos.get(mapaEleitoral.getCirculo()); i++)
            {
                int maxNum = 0;
                Lista maxList = null;
                for(Lista lista : m.keySet())
                {
                    int votos = m.get(lista);
                    int quot = votos / i + 1;

                    if(quot > maxNum)
                    {
                        maxList = lista;
                        maxNum = quot;
                    }

                }
                int assigned = (toAdd.get(maxList) != null ? toAdd.get(maxList) + 1 : 1);

                toAdd.put(maxList, assigned);
            }

            toRet.put(mapaEleitoral, toAdd);
        }


        return toRet;
    }


}
