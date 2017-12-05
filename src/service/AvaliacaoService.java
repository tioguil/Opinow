package service;

import java.util.ArrayList;

import dao.AvaliacaoDAO;
import model.Avaliacao;

public class AvaliacaoService {
	AvaliacaoDAO dao = new AvaliacaoDAO();

	public Avaliacao incluir(Avaliacao avaliacao) {
		return dao.criar(avaliacao);
	}

	public ArrayList<Avaliacao> listarAvaliacao(int id) {
		return dao.listarAvaliacao(id);
	}

	public ArrayList<Avaliacao> listarAvaliacaoCategoria(int id) {
		return dao.listaAvaliacaoEstabelecimento(id);
	}
}