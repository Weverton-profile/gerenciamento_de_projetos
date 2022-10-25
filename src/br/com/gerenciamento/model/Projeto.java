package br.com.gerenciamento.model;

public class Projeto {
	
	private Integer id;
	private String nome;
	private String descricao;
	private Integer gerente_id;
	private String andamento;
	
	public Projeto(Integer id, String nome, String descricao, Integer gerente_id, String andamento) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.gerente_id = gerente_id;
		this.andamento = andamento;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}


	public String getAndamento() {
		return andamento;
	}

	public Integer getGerente_id() {
		return gerente_id;
	}

}
