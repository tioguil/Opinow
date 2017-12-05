package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Estabelecimento;

public class EstabelecimentoDAO {

	public Estabelecimento criar(Estabelecimento estabelecimento) {
		String sqlInsert = "INSERT INTO estabelecimento (nome, endereco, cidade, categoria) VALUES (?,?,?,?);";

		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = (PreparedStatement) conn.prepareStatement(sqlInsert);) {
			stm.setString(1, estabelecimento.getNome());
			stm.setString(2, estabelecimento.getEndereco());
			stm.setString(3, estabelecimento.getCidade());
			stm.setString(4, estabelecimento.getCategoria());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";

			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next())
					estabelecimento.setId(rs.getInt(1));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			estabelecimento.setId(-1);
		}
		return estabelecimento;
	}
		
	public Estabelecimento consultar(Estabelecimento estabelecimento) {
		String sqlSelect = "SELECT id, nome, categoria, endereco, cidade FROM estabelecimento WHERE nome like replace(?, ' ', '%');";

		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, "%" + estabelecimento.getNome() + "%");

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setEndereco(rs.getString("endereco"));
					estabelecimento.setCidade(rs.getString("cidade"));
					estabelecimento.setCategoria(rs.getString("categoria"));
				} else {
					estabelecimento.setId(-1);
					estabelecimento.setNome("N&#227;o localizado");
					estabelecimento.setCategoria("N&#227;o localizado");
					estabelecimento.setEndereco("N&#227;o localizado");
					estabelecimento.setCidade("N&#227;o localizado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return estabelecimento;
	}
		
	public Estabelecimento carregar(Estabelecimento estabelecimento) {
		String sqlSelect = "SELECT id, nome, endereco, cidade, categoria FROM estabelecimento WHERE nome = ? AND endereco = ? AND cidade = ? AND categoria = ?;";

		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, estabelecimento.getNome());
			stm.setString(2, estabelecimento.getEndereco());
			stm.setString(3, estabelecimento.getCidade());
			stm.setString(4, estabelecimento.getCategoria());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setEndereco(rs.getString("endereco"));
					estabelecimento.setCidade(rs.getString("cidade"));
					estabelecimento.setCategoria(rs.getString("categoria"));
				} else {
					estabelecimento.setId(-1);
					estabelecimento.setNome("N&#227;o localizado");
					estabelecimento.setCategoria("N&#227;o localizado");
					estabelecimento.setEndereco("N&#227;o localizado");
					estabelecimento.setCidade("N&#227;o localizado");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return estabelecimento;
	}	

	public boolean compararEstabelecimento(Estabelecimento estabelecimento) {
		String sqlSelect = "SELECT nome, endereco, cidade, categoria FROM estabelecimento WHERE nome = ? AND endereco = ? AND cidade = ? AND categoria = ?;";

		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, estabelecimento.getNome());
			stm.setString(2, estabelecimento.getEndereco());
			stm.setString(3, estabelecimento.getCidade());
			stm.setString(4, estabelecimento.getCategoria());

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next())
					return true;
				else
					return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return false;
	}
	
	public ArrayList<Estabelecimento> listarEstabelecimento() {
		ArrayList<Estabelecimento> lista = new ArrayList<>();
		String listaEstabelecimento = "SELECT e.id, e.nome, e.categoria, e.endereco, e.cidade, MAX(a.data_avaliacao) AS 'data_avaliacao', AVG(ca.nota) AS 'media' FROM estabelecimento e JOIN avaliacao a ON e.id = a.estabelecimento_id JOIN categoria_avaliacao ca ON ca.avaliacao_id = a.id GROUP BY e.id ORDER BY AVG(ca.nota) DESC;";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(listaEstabelecimento);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Estabelecimento estabelecimento = new Estabelecimento();
					estabelecimento.setDtAvaliacao(rs.getTimestamp("data_avaliacao"));
					estabelecimento.setNome(rs.getString("e.nome"));
					estabelecimento.setCategoria(rs.getString("e.categoria"));
					estabelecimento.setEndereco(rs.getString("e.endereco"));
					estabelecimento.setCidade(rs.getString("e.cidade"));
					estabelecimento.setId(rs.getInt("e.id"));
					estabelecimento.setMedia(rs.getDouble("media"));
					lista.add(estabelecimento);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public ArrayList<Estabelecimento> tresMelhores() {
		ArrayList<Estabelecimento> lista = new ArrayList<>();
		String listaEstabelecimento = "SELECT estabelecimento.id, estabelecimento.nome, estabelecimento.endereco, estabelecimento.categoria, AVG(categoria_avaliacao.nota) AS 'media' FROM estabelecimento JOIN avaliacao ON estabelecimento.id = estabelecimento_id JOIN usuario ON usuario.id = avaliacao.usuario_id JOIN categoria_avaliacao ON avaliacao.id = categoria_avaliacao.avaliacao_id GROUP BY avaliacao.estabelecimento_id ORDER BY AVG(categoria_avaliacao.nota) DESC LIMIT 3;";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(listaEstabelecimento);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					Estabelecimento estabelecimento = new Estabelecimento();
					estabelecimento.setId(rs.getInt("estabelecimento.id"));
					estabelecimento.setNome(rs.getString("estabelecimento.nome"));
					estabelecimento.setEndereco(rs.getString("estabelecimento.endereco"));
					estabelecimento.setCategoria(rs.getString("categoria"));
					estabelecimento.setMedia(rs.getDouble("media"));
					lista.add(estabelecimento);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public Estabelecimento carregarId(int id) {
		Estabelecimento estabelecimento = new Estabelecimento();
		String selectEstabelecimento = "SELECT e.id, e.nome, e.categoria, e.endereco, e.cidade, e.categoria, a.data_avaliacao FROM estabelecimento e INNER JOIN avaliacao a ON e.id = a.estabelecimento_id WHERE e.id = ? ORDER BY a.data_avaliacao DESC LIMIT 1";
		String selectMedia ="SELECT AVG(categoria_avaliacao.nota) AS 'media' FROM estabelecimento JOIN avaliacao ON estabelecimento.id = estabelecimento_id JOIN usuario ON usuario.id = avaliacao.usuario_id JOIN categoria_avaliacao ON avaliacao.id = categoria_avaliacao.avaliacao_id WHERE estabelecimento.id = ?;";

		try (Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(selectEstabelecimento);) {
			stm.setInt(1, id);

			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					estabelecimento.setId(rs.getInt("id"));
					estabelecimento.setNome(rs.getString("nome"));
					estabelecimento.setEndereco(rs.getString("endereco"));
					estabelecimento.setCidade(rs.getString("cidade"));
					estabelecimento.setCategoria(rs.getString("categoria"));
					estabelecimento.setDtAvaliacao(rs.getTimestamp("data_avaliacao"));
					try(Connection conn2 = ConnectionFactory.obtemConexao(); 
						PreparedStatement stm2 = conn2.prepareStatement(selectMedia);) {
							stm2.setInt(1, id);
							ResultSet rs2 = stm2.executeQuery();
							if(rs2.next()) {
								estabelecimento.setMedia(rs2.getDouble("media"));
								System.out.print(rs2.getDouble("media"));
							}
					} catch(SQLException e) {
						e.printStackTrace();
					}
				} else {
					estabelecimento.setId(-1);
					estabelecimento.setNome("N&#227;o localizado");
					estabelecimento.setCategoria("N&#227;o localizado");
					estabelecimento.setEndereco("N&#227;o localizado");
					estabelecimento.setCidade("N&#227;o localizado");
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} catch (SQLException e3) {
			System.out.print(e3.getStackTrace());
		}
		return estabelecimento;
	}

	public ArrayList<Estabelecimento> categoriaEstabelecimento() {
		ArrayList<Estabelecimento> listaCategoria = new ArrayList<>();
		String categoriaEstabelecimento = "SELECT DISTINCT estabelecimento.categoria FROM estabelecimento ORDER BY categoria;";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(categoriaEstabelecimento);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
						Estabelecimento estabelecimento = new Estabelecimento();
						estabelecimento.setCategoria(rs.getString("categoria"));
						listaCategoria.add(estabelecimento);	
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return listaCategoria;
	}
}