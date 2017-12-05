package model;

import java.sql.Timestamp;

public class Estabelecimento {
	int id;
	double media;
	String nome, endereco, cidade, Categoria;
	Timestamp dtAvaliacao;

	public Timestamp getDtAvaliacao() {
		return dtAvaliacao;
	}

	public void setDtAvaliacao(Timestamp dtAvaliacao) {
		this.dtAvaliacao = dtAvaliacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String Categoria) {
		this.Categoria = Categoria;
	}

	@Override
	public String toString() {
		return "Estabelecimento [id=" + id + ", idCategoria=" + Categoria + ", nome=" + nome + ", endereco=" + endereco + ", cidade=" + cidade + "]";
	}
}
