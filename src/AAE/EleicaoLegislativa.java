package AAE;

import DAOs.AssembleiaDeVotoDAO;
import DAOs.ListaDAO;
import DAOs.MapaEleitoralDAO;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by drcon on 28/12/2015.
 */
public class EleicaoLegislativa extends Eleicao {
    

    public MapaEleitoralDAO mapas;

    public EleicaoLegislativa(String nome, java.sql.Date data) {
        super(nome, data, "legislativas");
    }

    public HashMap<MapaEleitoral,  HashMap<Lista, Integer>> gerarEstatisticas()
    {
        Collection<MapaEleitoral> map = mapas.getMapasPorEleicao(this.getNome());
        HashMap<MapaEleitoral, HashMap<Lista, Integer>> toRet = new HashMap<>();
        for(MapaEleitoral mapa : map)
        {
            toRet.put(mapa, mapa.getVotosPorLista());
        }
        return toRet;
    }

    public void atribuirMandatos()
    {
        HashMap<MapaEleitoral, HashMap<Lista, Integer>> toRet = new HashMap<>();

        HashMap<MapaEleitoral, HashMap<Lista, Integer>> res = gerarEstatisticas();

        for(MapaEleitoral mapaEleitoral : res.keySet())
        {

            HashMap<Lista, Integer> m = res.get(mapaEleitoral);
            HashMap<Lista, Integer> toAdd = new HashMap<>();
            int i = 0;
            for(; i < mapaEleitoral.getDeputados(); i++)
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


        new ListaDAO().registarMandatos(toRet);
    }

    public void registarLista(Lista l)
    {
        //TODO:
    }


}
