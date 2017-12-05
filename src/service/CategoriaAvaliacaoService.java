package service;

import java.sql.SQLException;

import dao.CategoriaAvaliacaoDAO;
import model.CategoriaAvaliacao;

public class CategoriaAvaliacaoService {
	CategoriaAvaliacaoDAO dao = new CategoriaAvaliacaoDAO();

	public CategoriaAvaliacao incluir(CategoriaAvaliacao categoria) throws SQLException {
		return dao.incluir(categoria);
	}
}