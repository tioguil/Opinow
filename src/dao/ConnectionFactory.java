package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Obtem conexao com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		String dbLocal = "jdbc:mysql://127.0.0.1:3306/opinow?user=root&password=";
		String dbAzure = "jdbc:mysql://127.0.0.1:49672/opinow?user=azure&password=6#vWHD_$";
		return DriverManager.getConnection(dbAzure);
	}

}
