package service;

import model.Usuario;

import java.sql.SQLException;

import dao.UsuarioDAO;

public class UsuarioService {
	UsuarioDAO dao = new UsuarioDAO();
	
	public Usuario validarLogin(Usuario usuario) {
		return dao.validaLogin(usuario);
	}
	
	public Usuario validarEmailCpf(Usuario usuario) {
		return dao.validaEmailCpf(usuario);
	}

	public boolean criar(Usuario usuario) throws SQLException {
		return dao.criar(usuario);
	}

	public Usuario carregar(Usuario usuario) {
		return dao.carregar(usuario);
	}

	public void atualizar(Usuario usuario) {
		dao.atualizar(usuario);
	}
}