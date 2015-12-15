import java.util.GregorianCalendar;

public class Candidato {

	private GregorianCalendar data_bi;
	private int bi;
	private String filicao;
	private String arquivo;
	private String nome;
	private String profissao;
	private int idade;
	private String morada;
	private String nacionalidade;

	public Candidato(GregorianCalendar data_bi, int bi, String filicao, String arquivo, String nome, String profissao, int idade, String morada, String nacionalidade) {
		this.data_bi = data_bi;
		this.bi = bi;
		this.filicao = filicao;
		this.arquivo = arquivo;
		this.nome = nome;
		this.profissao = profissao;
		this.idade = idade;
		this.morada = morada;
		this.nacionalidade = nacionalidade;
	}

	public GregorianCalendar getData_bi() {
		return this.data_bi;
	}

	/**
	 * 
	 * @param data_bi
	 */
	public void setData_bi(GregorianCalendar data_bi) {
		this.data_bi = data_bi;
	}

	public int getBi() {
		return this.bi;
	}

	/**
	 * 
	 * @param bi
	 */
	public void setBi(int bi) {
		this.bi = bi;
	}

	public String getFilicao() {
		return this.filicao;
	}

	/**
	 * 
	 * @param filicao
	 */
	public void setFilicao(String filicao) {
		this.filicao = filicao;
	}

	public String getArquivo() {
		return this.arquivo;
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getNome() {
		return this.nome;
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return this.profissao;
	}

	/**
	 * 
	 * @param profissao
	 */
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public int getIdade() {
		return this.idade;
	}

	/**
	 * 
	 * @param idade
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getMorada() {
		return this.morada;
	}

	/**
	 * 
	 * @param morada
	 */
	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getNacionalidade() {
		return this.nacionalidade;
	}

	/**
	 * 
	 * @param nacionalidade
	 */
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

}