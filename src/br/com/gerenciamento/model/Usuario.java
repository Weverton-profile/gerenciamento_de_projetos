package br.com.gerenciamento.model;

public class Usuario {

	private Integer id;
	private String nomeUsuario;
	private String email;
	
	public Usuario(String nomeUsuario, String email) {
		this.nomeUsuario = nomeUsuario;
		this.email = email;
	}
	
	public Usuario(Integer id, String nomeUsuario, String email) {
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmail() {
		return email;
	}

}
