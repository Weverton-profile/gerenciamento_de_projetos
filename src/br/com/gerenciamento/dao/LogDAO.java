package br.com.gerenciamento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.gerenciamento.model.Log;

public class LogDAO {
    
    private Connection con;
    
    public LogDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Log> listarLogs(Integer projeto_id) throws SQLException {
        ArrayList<Log> logs = new ArrayList<>();
        
        String sql = "SELECT * FROM LOG WHERE id_projeto = ?;";
        
        try(PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, projeto_id);
            pstm.execute();
            
            try(ResultSet rst = pstm.getResultSet()) {
                while(rst.next()) {
                    Log log = new Log(rst.getString(2), rst.getString(3));
                    logs.add(log);
                }
            }
        }
        return logs;
    }
  
}