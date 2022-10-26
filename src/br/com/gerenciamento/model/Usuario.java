package br.com.gerenciamento.model;

public class Usuario {

	private Integer id;
	private String nomeUsuario;
	private String email;
	private String cargo;
	
	public Usuario(Integer id, String nomeUsuario, String email, String cargo) {
		super();
		this.id = id;
		this.nomeUsuario = nomeUsuario;
		this.email = email;
		this.cargo = cargo;
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

	public String getCargo() {
		return cargo;
	}
}
