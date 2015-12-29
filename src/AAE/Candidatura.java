package AAE;

import DAOs.CandidaturaDAO;

import java.util.Date;
import java.util.GregorianCalendar;

public class Candidatura {

	private int id;
	private Date data;
	private Candidato candidato;
	private String eleicao;



    public Candidatura(int id, Date data, Candidato candidato, String eleicao)
    {
        this.id = id;
        this.data = data;
        this.candidato = candidato;
        this.eleicao = eleicao;
    }

    public boolean validarCandidatura(Date data) throws CandidaturaTardiaException, CandidatoDemasiadoNovoException, CandidatoEstrangeiroException, AssinaturasInsuficientesExceptions
	{
		Date date = data;
		date.roll(GregorianCalendar.MONTH, -1);
		if(date.before(this.data))
		{
			//Candidatura tardia
			throw new CandidaturaTardiaException();
		}

		if(candidato.getIdade() < 35)
		{
			//Candidato demasiado novo
			throw new CandidatoDemasiadoNovoException();
		}

		if(!candidato.getNacionalidade().equals("Portugal"))
		{
			//Candidato é estrangeiro
			throw new CandidatoEstrangeiroException();
		}

		if(new CandidaturaDAO().getNumAssinaturasCandidato(candidato.getBi()) < 7500)
		{
			//Assinaturas insuficientes
			throw new AssinaturasInsuficientesExceptions();
		}

		return true;

	}

	public int getID() {
		// TODO - implement Candidatura.getID
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		// TODO - implement Candidatura.setID
		throw new UnsupportedOperationException();
	}

	public Date getData() {
		return this.data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	public Candidato getCandidato() {
		return this.candidato;
	}

	/**
	 * 
	 * @param candidato
	 */
	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String getEleicao() {
		return this.eleicao;
	}

	/**
	 * 
	 * @param eleicao
	 */
	public void setEleicao(String eleicao) {
		this.eleicao = eleicao;
	}

}