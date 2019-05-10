package br.com.novaroma.liwspa.entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import br.com.novaroma.liwspa.utils.FormaPagamento;

@SuppressWarnings("all")
public class Agendamento implements Serializable {

	private LocalTime hora;
	private LocalDate data;
	private Cliente cliente;
	private FormaPagamento formaPagamento;
	private Collection<Tratamento> tratamentos;
	private Integer id;
	private double custoTotal;

	public Agendamento() {
		this.tratamentos = new ArrayList<>();

	}

	public Agendamento(LocalTime hora, LocalDate data, Cliente cliente, Integer id, FormaPagamento formaPagamento,
			double custoTotal) {
		this.hora = hora;
		this.data = data;
		this.cliente = cliente;
		this.tratamentos = new ArrayList<>();
		this.id = id;
		this.formaPagamento = formaPagamento;
		this.custoTotal = custoTotal;

	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Collection<Tratamento> getTratamentos() {
		return tratamentos;
	}

	public void setTratamentos(Collection<Tratamento> tratamentos) {
		this.tratamentos = tratamentos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public double getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(double custoTotal) {
		this.custoTotal = custoTotal;
	}
}
