package model;

import java.sql.Date;

public class Usuario {
	String nome, email, apelido, senha, cpf;
	Date dtNasc, dtCriacao;
	int  id, statusUsuario;

	public Usuario(int id, String nome, String cpf, String email, String apelido, String senha, Date dtNasc, Date dtCriacao, int statusUsuario) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.apelido = apelido;
		this.senha = senha;
		this.dtNasc = dtNasc;
		this.dtCriacao = dtCriacao;
		this.statusUsuario = statusUsuario;
	}

	public Usuario() {}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public int getStatusUsuario() {
		return statusUsuario;
	}

	public void setStatusUsuario(int statusUsuario) {
		this.statusUsuario = statusUsuario;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", email=" + email + ", apelido=" + apelido + ", senha=" + senha + ", cpf="+ cpf + ", dtNasc=" + dtNasc + ", dtCriacao=" + dtCriacao + ", id=" + id + ", statusUsuario=" + statusUsuario + "]";
	}
}