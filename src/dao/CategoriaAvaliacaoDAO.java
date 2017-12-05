package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CategoriaAvaliacao;

public class CategoriaAvaliacaoDAO {

	public CategoriaAvaliacao incluir(CategoriaAvaliacao categoria) {
		String sqlInsert = "INSERT INTO categoria_avaliacao (nome, nota, avaliacao_id) VALUES (?,?,?);";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, categoria.getNome());
			stm.setInt(2, categoria.getNota());
			stm.setInt(3, categoria.getIdAvaliacao());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next())
					categoria.setId(rs.getInt(1));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoria;
	}
}
