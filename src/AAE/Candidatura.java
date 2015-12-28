package AAE;

import DAOs.EleitorDAO;
import java.lang.annotation.ElementType;
import java.util.GregorianCalendar;

public class Candidatura {

	private int id;
	private GregorianCalendar data;
	private Candidato candidato;
	private String eleicao;



    public Candidatura(int id, GregorianCalendar data, Candidato candidato, String eleicao)
    {
        this.id = id;
        this.data = data;
        this.candidato = candidato;
        this.eleicao = eleicao;
    }

    public boolean validarCandidatura(GregorianCalendar data) throws CandidaturaTardiaException, CandidatoDemasiadoNovoException, CandidatoEstrangeiroException, AssinaturasInsuficientesExceptions
	{
		GregorianCalendar date = data;
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

		if(EleitorDAO.getNumAssinaturasCandidato(candidato.getBi()) < 7500)
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

	public GregorianCalendar getData() {
		return this.data;
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(GregorianCalendar data) {
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