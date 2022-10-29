package br.com.gerenciamento.model;

public class MembrosProjeto {
	
	private Integer id_projeto;
	private Integer id_membro;
	
	public MembrosProjeto(Integer id_projeto, Integer id_membro) {
		this.id_projeto = id_projeto;
		this.id_membro = id_membro;
	}

	public Integer getId_projeto() {
		return id_projeto;
	}

	public Integer getId_membro() {
		return id_membro;
	}
	
}
