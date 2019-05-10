package br.com.novaroma.liwspa.entidades;

import java.io.Serializable;

@SuppressWarnings("all")
public class Tratamento implements Serializable {

	private Double custo;
	private int codigo;
	private String nome;
	private int id;

	public Tratamento() {
	}

	public Tratamento(Double custo, String nome, int codigo, int id) {
		this.custo = custo;
		this.nome = nome;
		this.codigo = codigo;
		this.id = id;

	}

	public int getCodigo() {
		return this.codigo;
	}

	public Double getCusto() {
		return custo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
