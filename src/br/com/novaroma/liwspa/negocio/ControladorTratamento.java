package br.com.novaroma.liwspa.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.novaroma.liwspa.dados.Dao;
import br.com.novaroma.liwspa.entidades.Tratamento;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.interfaces.IDao;

public class ControladorTratamento implements IControlador<Tratamento> {

	private IDao<Tratamento> dao;

	public ControladorTratamento() {
		dao = new Dao<>(Tratamento.class);
	}

	public List<Tratamento> listar()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		return dao.ler();
	}

	public ArrayList<Tratamento> listarPorCodigo(int codigo)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Tratamento> tratamentos = dao.ler();
		ArrayList<Tratamento> t = new ArrayList<>();

		for (Tratamento tAtual : tratamentos) {
			if (tAtual.getCodigo() == codigo) {
				t.add(tAtual);
			}
		}
		return t;
	}

	@Override
	public boolean adicionar(Tratamento novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Tratamento> clientes = dao.ler();
		clientes.add(novoItem);
		dao.salvar(clientes);
		return true;
	}

	@Override
	public Tratamento pegar(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Tratamento> tratamentos = dao.ler();

		int id = (int) identificador;

		for (Tratamento tratamento : tratamentos) {
			if (tratamento.getId() == id) {
				return tratamento;
			}
		}

		return null;
	}

	@Override
	public boolean remover(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Tratamento> clientes = dao.ler();

		Iterator<Tratamento> iterator = clientes.iterator();

		Tratamento tratamento = null;
		while (iterator.hasNext()) {
			tratamento = iterator.next();
			if (tratamento.getNome().equals(String.valueOf(identificador))) {
				iterator.remove();
				dao.salvar(clientes);
			}
		}

		return true;
	}

	@Override
	public boolean atualizar(Object identificador, Tratamento novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		remover(identificador);
		adicionar(novoItem);
		return true;
	}

	@Override
	public Integer quantidade()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Tratamento> clientes = dao.ler();
		return clientes.size();
	}
}
