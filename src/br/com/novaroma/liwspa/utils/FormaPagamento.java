package br.com.novaroma.liwspa.utils;

import java.io.Serializable;

public enum FormaPagamento implements Serializable {
	CARTAO_MASTERCARD(1, "MasterCard"), CARTAO_VISA(2, "Visa"), CARTAO_HIPER(3, "Hiper Bom-Preço"), DINHEIRO(4,
			"Dinheiro");

	private Integer id;
	private String nome;

	private FormaPagamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

}
