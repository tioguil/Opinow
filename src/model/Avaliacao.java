package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Avaliacao {
	int id, idUsuario, nota, id_estabelecimento;
	String avaliacao, nome_estabelecimento, categoria_estabelecimento, tipo_categoria;
	Estabelecimento estabelecimento; 
	ArrayList<CategoriaAvaliacao> categoria;
	Timestamp dtAvaliacao;
	Usuario usuario;

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public int getIdEstabelecimento() {
		return id_estabelecimento;
	}

	public void setIdEstabelecimento(int id_estabelecimento) {
		this.id_estabelecimento = id_estabelecimento;
	}

	public String getCategoria_estabelecimento() {
		return categoria_estabelecimento;
	}

	public void setCategoria_estabelecimento(String categoria_estabelecimento) {
		this.categoria_estabelecimento = categoria_estabelecimento;
	}

	public String getNome_estabelecimento() {
		return nome_estabelecimento;
	}

	public void setNome_estabelecimento(String nome_estabelecimento) {
		this.nome_estabelecimento = nome_estabelecimento;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getTipo_categoria() {
		return tipo_categoria;
	}

	public void setTipo_categoria(String tipo_categoria) {
		this.tipo_categoria = tipo_categoria;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<CategoriaAvaliacao> getCategoria() {
		return categoria;
	}

	public void setCategoria(ArrayList<CategoriaAvaliacao> categoria) {
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDtAvaliacao() {
		return dtAvaliacao;
	}

	public void setDtAvaliacao(Timestamp dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}

	public String getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
}