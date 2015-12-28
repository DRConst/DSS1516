package AAE;


import DAOs.AssembleiaDeVotoDAO;
import DAOs.CandidatoDAO;
import DAOs.CandidaturaDAO;
import DAOs.EleitorDAO;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by drcon on 23/12/2015.
 */
public class Eleicao {
    public String Nome;
    public String Tipo;
    public GregorianCalendar data;
    public EleitorDAO votantes;



    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    public EleitorDAO getVotantes() {
        return votantes;
    }

    public void setVotantes(EleitorDAO votantes) {
        this.votantes = votantes;
    }
    
    

    
}
