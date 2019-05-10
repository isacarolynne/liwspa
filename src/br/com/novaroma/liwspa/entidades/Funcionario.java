package br.com.novaroma.liwspa.entidades;

import java.io.Serializable;

@SuppressWarnings("all")
public class Funcionario extends Pessoa implements Serializable {

	private Double salario;
	private Endereco endereco;
	private int id;

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, String telefone, int idade, Double salario, Endereco endereco,
			String senha) {
		super(nome, cpf, telefone, idade);
		this.salario = salario;
		this.endereco = endereco;
		this.senha = senha;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
