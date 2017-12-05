package dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import model.Usuario;

public class UsuarioDAO {

	public Usuario validaLogin(Usuario usuario) {
		String sqlSelect = "SELECT id, nome, apelido, data_nascimento, cpf, senha FROM usuario WHERE email = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setString(1, usuario.getEmail());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setApelido(rs.getString("apelido"));
					usuario.setDtNasc(rs.getDate("data_nascimento"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));					
				} else
					usuario.setId(-1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return usuario;
	}
	
	public Usuario validaEmailCpf(Usuario usuario) {
		String sqlSelect = "SELECT id, nome, apelido, data_nascimento, cpf, senha FROM usuario WHERE email = ? and cpf = ?;";
		
		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getCpf());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setApelido(rs.getString("apelido"));
					usuario.setDtNasc(rs.getDate("data_nascimento"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setSenha(rs.getString("senha"));					
				} else
					usuario.setId(-1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return usuario;
	}

	public boolean criar(Usuario usuario) throws SQLException {
		String sqlInsert = "INSERT INTO usuario (nome, email, apelido, cpf, senha, data_nascimento, data_criacao) VALUES (?, ?, ?, ?, ?, ?, now());";

		try (Connection conn = ConnectionFactory.obtemConexao()){
			conn.setAutoCommit(false);
			try (PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sqlInsert);) {
				stm.setString(1, usuario.getNome());
				stm.setString(2, usuario.getEmail());
				stm.setString(3, usuario.getApelido());
				stm.setString(4, usuario.getCpf());
				stm.setString(5, usuario.getSenha());
				stm.setDate(6, usuario.getDtNasc());
				stm.execute();

				conn.commit();
				conn.setAutoCommit(true);

				return true;
			} catch (SQLException e) {
				conn.rollback();
				conn.setAutoCommit(true);
				throw e;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void atualizar(Usuario usuario) {
		String sqlUpdate = "UPDATE usuario SET nome = ?, email = ?, apelido = ?, senha = ? WHERE id = ?;";

		try (Connection conn = (Connection) ConnectionFactory.obtemConexao();
				PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, usuario.getNome());
			stm.setString(2, usuario.getEmail());
			stm.setString(3, usuario.getApelido());
			stm.setString(4, usuario.getSenha());
			stm.setInt(5, usuario.getId());
	
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Usuario carregar(Usuario usuario) {
		String sqlSelect = "SELECT id, nome, cpf, email, apelido, senha, data_nascimento, data_criacao, status FROM usuario WHERE usuario.id = ?;";

		try (Connection conn = ConnectionFactory.obtemConexao();				
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, usuario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setEmail(rs.getString("email"));
					usuario.setApelido(rs.getString("apelido"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setDtNasc(rs.getDate("data_nascimento"));
					usuario.setDtCriacao(rs.getDate("data_criacao"));
					usuario.setStatusUsuario(rs.getInt("status"));
				} else {
					usuario.setId(-1);
					usuario.setNome("N&#227;o Localizado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return usuario;
	}
}