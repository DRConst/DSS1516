package AAE;

import java.util.Date;

public class Candidato {

	private Date data_bi;
	private int bi;
	private String filiacao;
	private String arquivo;
	private String nome;
	private String profissao;
	private int idade;
	private String morada;
	private String nacionalidade;

	public Candidato(Date data_bi, int bi, String filiacao, String arquivo, String nome, String profissao, int idade, String morada, String nacionalidade) {
		this.data_bi = data_bi;
		this.bi = bi;
		this.filiacao = filiacao;
		this.arquivo = arquivo;
		this.nome = nome;
		this.profissao = profissao;
		this.idade = idade;
		this.morada = morada;
		this.nacionalidade = nacionalidade;
	}

	public Date getData_bi() {
		return this.data_bi;
	}

	/**
	 * 
	 * @param data_bi
	 */
	public void setData_bi(Date data_bi) {
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

	public String getFiliacao() {
		return this.filiacao;
	}

	/**
	 * 
	 * @param filiacao
	 */
	public void setFiliacao(String filiacao) {
		this.filiacao = filiacao;
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