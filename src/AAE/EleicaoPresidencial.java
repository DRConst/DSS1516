package AAE;

import DAOs.CandidatoDAO;
import DAOs.EleicaoDAO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by drcon on 28/12/2015.
 */
public class EleicaoPresidencial {


    public HashMap<Candidato, Integer> gerarEstatisticas()
    {
        HashMap<Candidato, Integer> toRet = new HashMap<>();
        ArrayList<Candidato> candidatos = new EleicaoDAO.getCandidatos(this.nome);

        for(Candidato candidato : candidatos)
        {

            toRet.put(candidato, new CandidatoDAO().getVotos(candidato.getBi(),this.nome));
        }
    }
}
