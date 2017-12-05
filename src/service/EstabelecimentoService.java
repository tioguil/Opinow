package service;

import java.util.ArrayList;

import dao.EstabelecimentoDAO;
import model.Estabelecimento;

public class EstabelecimentoService {

	public Estabelecimento criar(Estabelecimento estabelecimento) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.criar(estabelecimento);
	}

	public Estabelecimento carregar(Estabelecimento estabelecimento) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.carregar(estabelecimento);
	}

	public boolean compararEstabelecimento(Estabelecimento estabelecimento) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.compararEstabelecimento(estabelecimento);
	}

	public ArrayList<Estabelecimento> listarEstabelecimento() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.listarEstabelecimento();
	}

	public ArrayList<Estabelecimento> tresMelhores() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.tresMelhores();
	}

	public Estabelecimento carregarId(int id) {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.carregarId(id);
	}

	public ArrayList<Estabelecimento> categoriaEstabelecimento() {
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		return dao.categoriaEstabelecimento();
	}
}