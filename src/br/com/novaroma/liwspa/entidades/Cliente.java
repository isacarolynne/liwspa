package br.com.novaroma.liwspa.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@SuppressWarnings("all")
public class Cliente extends Pessoa implements Serializable {

	private Collection<Agendamento> agendamentos;
	private String sexo;

	int idFuncionario;
	Date data;

	public Cliente() {
		super();
		this.agendamentos = new ArrayList<>();

	}

	public Cliente(String nome, String cpf, String telefone, int idade, String sexo) {
		super(nome, cpf, telefone, idade);
		this.agendamentos = new ArrayList<>();
		this.sexo = sexo;

	}

	public Collection<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(Collection<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}
