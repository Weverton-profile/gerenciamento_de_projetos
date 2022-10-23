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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getGerente_id() {
		return gerente_id;
	}

	public void setGerente_id(Integer gerente_id) {
		this.gerente_id = gerente_id;
	}

	public String getAndamento() {
		return andamento;
	}

	public void setAndamento(String andamento) {
		this.andamento = andamento;
	}
}
