package br.com.novaroma.liwspa.negocio;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import br.com.novaroma.liwspa.dados.Dao;
import br.com.novaroma.liwspa.entidades.Agendamento;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.interfaces.IDao;

public class ControladorAgendamento implements IControlador<Agendamento> {

	private IDao<Agendamento> dao;

	public ControladorAgendamento() {
		dao = new Dao<>(Agendamento.class);
	}

	public static boolean checarDados(Agendamento agendamento) {
		if (agendamento.getData() != null && agendamento.getHora() != null && agendamento.getId() != null
				&& agendamento.getFormaPagamento() != null) {
			return true;
		}
		return false;
	}

	public List<Agendamento> listar()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		return dao.ler();
	}

	@Override
	public boolean adicionar(Agendamento agendamento)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Agendamento> agendamentos = dao.ler();
		if (checarDados(agendamento)) {
			agendamentos.add(agendamento);
			dao.salvar(agendamentos);
			return true;
		}
		return false;
	}

	@Override
	public Agendamento pegar(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Agendamento> agendamentos = dao.ler();

		int id = Integer.parseInt(String.valueOf(identificador));

		for (Agendamento agendamento : agendamentos) {
			if (agendamento.getId() == id) {
				return agendamento;
			}
		}

		return null;
	}

	@Override
	public boolean remover(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {
		List<Agendamento> agendamentos = dao.ler();

		Iterator<Agendamento> iterator = agendamentos.iterator();

		Agendamento agendamento = null;

		int id = Integer.parseInt(String.valueOf(identificador));
		while (iterator.hasNext()) {
			agendamento = iterator.next();
			if (agendamento.getId() == id) {
				iterator.remove();
				dao.salvar(agendamentos);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean atualizar(Object identificador, Agendamento novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {

		if (checarDados(novoItem) && identificador != null) {
			remover(identificador);
			adicionar(novoItem);
			return true;
		}

		return false;
	}

	@Override
	public Integer quantidade()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {
		List<Agendamento> agendamentos = dao.ler();
		return agendamentos.size();
	}

}
