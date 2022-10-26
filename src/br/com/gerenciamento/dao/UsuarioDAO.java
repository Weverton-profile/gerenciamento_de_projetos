package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.gerenciamento.model.Usuario;

public class UsuarioDAO {

	private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public Usuario encontrarUsuarioEmailSenha(String email, String senha) throws SQLException {
		
		String sql = "SELECT id, nome_usuario, email, cargo FROM USUARIO WHERE email = ? AND senha = ?";
		Usuario usuario = null;
		try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			pstm.setString(1, email);
			pstm.setString(2, senha);
			pstm.execute();
			
			try(ResultSet rst = pstm.getResultSet()) {
				while(rst.next()) {
					usuario = new Usuario(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4));
				}
			}
		}
		return usuario;
	}

}
