package br.com.gerenciamento.model;

public class Tarefa {
	
	private Integer id_projeto;
	private Integer membro_id;
	private String andamento;
	private String nome;
	
	public Tarefa(Integer id_projeto, String andamento, String nome) {
		this.id_projeto = id_projeto;
		this.andamento = andamento;
		this.nome = nome;
	}
	
	public Tarefa(Integer id_projeto, Integer membro_id, String andamento, String nome) {
		this.id_projeto = id_projeto;
		this.membro_id = membro_id;
		this.andamento = andamento;
		this.nome = nome;
	}

	public Integer getId_projeto() {
		return id_projeto;
	}

	public Integer getMembro_id() {
		return membro_id;
	}

	public String getAndamento() {
		return andamento;
	}

	public String getNome() {
		return nome;
	}
	
}
