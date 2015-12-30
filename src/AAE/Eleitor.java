package AAE;


import java.util.Date;
import java.util.HashMap;

/**
 * Created by drcon on 25/12/2015.
 */
public class Eleitor {
    public static int defaultType = -1;
    public static int presidenteType = 0;
    public static int vPresidenteType = 1;
    public static int escType = 2;
    public static int secType = 3;


    private String nrEleitor;
    private String Nome;
    private Integer Idade;
    private java.sql.Date dataR;
    private String distrito;
    private String concelho;
    private String freguesia;


    public Eleitor(String nrEleitor, String nome, Integer idade, java.sql.Date dataR, String distrito, String concelho, String freguesia, Integer tipo) {
        this.nrEleitor = nrEleitor;
        Nome = nome;
        Idade = idade;
        this.dataR = dataR;
        this.distrito = distrito;
        this.concelho = concelho;
        this.freguesia = freguesia;
        this.tipo = tipo;
    }

    private Integer tipo;

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

    public Date getDataR() {
        return dataR;
    }

    public void setDataR(java.sql.Date dataR) {
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

