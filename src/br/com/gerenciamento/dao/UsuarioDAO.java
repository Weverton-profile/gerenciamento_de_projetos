package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "root");

		PreparedStatement stm = con.prepareStatement("SELECT * FROM PRODUTO");
		stm.execute();

		ResultSet rst = stm.getResultSet();
		while (rst.next()) {
			System.out.println("ID do Produto: " + rst.getInt("ID") + ", Nome: " + rst.getString("nome")
					+ ", Descrição: " + rst.getString("descricao") + "");
		}

		con.close();
	}

}
