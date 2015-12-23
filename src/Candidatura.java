import java.lang.annotation.ElementType;
import java.util.GregorianCalendar;

public class Candidatura {

	private int id;
	private GregorianCalendar data;
	private Candidato candidato;
	private Eleicao eleicao;



    public Candidatura(int id, GregorianCalendar data, Candidato candidato, Eleicao eleicao)
    {
        this.id = id;
        this.data = data;
        this.candidato = candidato;
        this.eleicao = eleicao;
    }

    public boolean validarCandidatura() throws CandidaturaTardiaException, CandidatoDemasiadoNovoException, CandidatoEstrangeiroException, AssinaturasInsuficientesExceptions
	{
		GregorianCalendar date = eleicao.getData();
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

		if(eleitorDAO.getNumAssinaturasCandidato(candidato.getBi()) < 7500)
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

	public Eleicao getEleicao() {
		return this.eleicao;
	}

	/**
	 * 
	 * @param eleicao
	 */
	public void setEleicao(Eleicao eleicao) {
		this.eleicao = eleicao;
	}

}