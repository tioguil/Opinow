package dao;
import model.Avaliacao;
import model.CategoriaAvaliacao;
import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class AvaliacaoDAO {

	public Avaliacao criar(Avaliacao avaliacao) {
		String sqlInsert = "INSERT INTO avaliacao (avaliacao,usuario_id,estabelecimento_id) VALUES (?,?,?);";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1, avaliacao.getAvaliacao());
			stm.setInt(2, avaliacao.getUsuario().getId());
			stm.setInt(3, avaliacao.getEstabelecimento().getId());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
					if (rs.next())
						avaliacao.setId(rs.getInt(1));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avaliacao;
	}

	public ArrayList<Avaliacao> listarAvaliacao(int id) {
		ArrayList<Avaliacao> lista = new ArrayList<>();
		String listaAvaliacao = "SELECT e.id, e.nome, e.categoria, data_avaliacao, avaliacao, ca.nome, ca.nota FROM avaliacao a INNER JOIN estabelecimento e ON e.id = a.estabelecimento_id INNER JOIN categoria_avaliacao ca ON a.id = ca.avaliacao_id WHERE a.usuario_id = ?;";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(listaAvaliacao);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setIdEstabelecimento(rs.getInt("e.id"));
					avaliacao.setDtAvaliacao(rs.getTimestamp("data_avaliacao"));
					avaliacao.setNome_estabelecimento(rs.getString("e.nome"));
					avaliacao.setCategoria_estabelecimento(rs.getString("e.categoria"));
					avaliacao.setTipo_categoria(rs.getString("ca.nome"));
					avaliacao.setNota(rs.getInt("ca.nota"));
					lista.add(avaliacao);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<Avaliacao> listaAvaliacaoEstabelecimento(int idEstabelecimento){
		ArrayList<Avaliacao> arrayAvaliacao = new ArrayList<>();

		String listaCategoria = "SELECT usuario.nome AS 'nomeUsuario', usuario.apelido, avaliacao.id AS 'idavaliacao', avaliacao.avaliacao, categoria_avaliacao.nome AS 'nomeCategoria', categoria_avaliacao.nota, categoria_avaliacao.avaliacao_id AS 'idAvaliacaoCategoria'FROM estabelecimento JOIN avaliacao ON estabelecimento.id = estabelecimento_id JOIN usuario ON usuario.id = avaliacao.usuario_id JOIN categoria_avaliacao ON avaliacao.id = categoria_avaliacao.avaliacao_id WHERE estabelecimento.id = ?;";		
		String listaAvaliacao = "SELECT usuario.nome AS 'nomeUsuario', usuario.apelido, avaliacao.id AS 'idavaliacao', avaliacao.avaliacao, avaliacao.data_avaliacao FROM estabelecimento JOIN avaliacao ON estabelecimento.id = estabelecimento_id JOIN usuario ON usuario.id = avaliacao.usuario_id WHERE estabelecimento.id = ? ORDER BY avaliacao.data_avaliacao DESC;";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(listaAvaliacao);) {
			stm.setInt(1, idEstabelecimento);

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setNome(rs.getString("nomeUsuario"));
					usuario.setApelido(rs.getString("apelido"));
					
					Avaliacao avaliacao = new Avaliacao();
					avaliacao.setUsuario(usuario);
					avaliacao.setId(rs.getInt("idavaliacao"));
					avaliacao.setAvaliacao(rs.getString("avaliacao"));
					avaliacao.setDtAvaliacao(rs.getTimestamp("data_avaliacao"));
					
					ArrayList<CategoriaAvaliacao> arrayCategoria = new ArrayList<>();

					try (PreparedStatement stm2 = conn.prepareStatement(listaCategoria);) {
						stm2.setInt(1, idEstabelecimento);
						ResultSet rs2 = stm2.executeQuery();

						while (rs2.next()) {
							CategoriaAvaliacao categoria = new CategoriaAvaliacao();

							categoria.setIdAvaliacao(rs2.getInt("idAvaliacaoCategoria"));
							categoria.setNota(rs2.getInt("nota"));
							categoria.setNome(rs2.getString("nomeCategoria"));

							if(avaliacao.getId() == categoria.getIdAvaliacao())
								arrayCategoria.add(categoria);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					avaliacao.setCategoria(arrayCategoria);
					arrayAvaliacao.add(avaliacao);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return arrayAvaliacao;
	}
}