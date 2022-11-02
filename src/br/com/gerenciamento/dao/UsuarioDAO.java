package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.gerenciamento.model.Usuario;

public class UsuarioDAO {

	private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public Usuario encontrarUsuarioEmailSenha(String email, String senha) throws SQLException {
		
		String sql = "SELECT id, nome_usuario, email FROM USUARIO WHERE email = ? AND senha = ?";
		Usuario usuario = null;
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, email);
			pstm.setString(2, senha);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));
				}
			}
		}
		return usuario;
	}
	
	public String encontrarUsuarioEmail(String email) throws SQLException {
		
		String sql = "SELECT email FROM USUARIO WHERE email = ?";
		String emailTeste = null;
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, email);

			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					emailTeste = rst.getString(1);
				}
			}
		}
		return emailTeste;
	}
	
	public void criarNovoUsuario(String nome, String email, String senha) throws SQLException {
		
		String sql = "INSERT INTO USUARIO (nome_usuario, email, senha) VALUES (?, ?, ?);";
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, nome);
			pstm.setString(2, email);
			pstm.setString(3, senha);
			pstm.execute();
		}
	}

	public List<Usuario> retornarDadosUsuarios(String stringUsuario) throws SQLException {
		
		String sql = "SELECT id, nome_usuario, email FROM USUARIO WHERE nome_usuario LIKE ? OR email LIKE ?;";
		List<Usuario> usuarios =  new ArrayList<>();
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, stringUsuario);
			pstm.setString(2, stringUsuario);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					Usuario usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));
					usuarios.add(usuario);
				}
			}
		}
		return usuarios;
	}

	public List<Usuario> pegarTodosOsMembros(String idProjeto) throws SQLException {
		String sql = "SELECT id, nome_usuario, email FROM membros_projeto LEFT JOIN usuario ON(membros_id = usuario.id) WHERE membros_projeto.id_projeto = ?;";
		List<Usuario> usuarios =  new ArrayList<>();
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, idProjeto);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					Usuario usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3));
					usuarios.add(usuario);
				}
			}
		}
		return usuarios;
	}
}
