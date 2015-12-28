package AAE;


import java.util.GregorianCalendar;

/**
 * Created by drcon on 25/12/2015.
 */
public class Eleitor {
    private String nrEleitor;
    private String Nome;
    private Integer Idade;
    private GregorianCalendar dataR;
    private String distrito;
    private String concelho;
    private String freguesia;
    
    public Eleitor(){
        
    }

    public String getNrEleitor() {
        return nrEleitor;
    }

    public void setNrEleitor(String nrEleitor) {
        this.nrEleitor = nrEleitor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Integer getIdade() {
        return Idade;
    }

    public void setIdade(Integer Idade) {
        this.Idade = Idade;
    }

    public GregorianCalendar getDataR() {
        return dataR;
    }

    public void setDataR(GregorianCalendar dataR) {
        this.dataR = dataR;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }
        
    
}
