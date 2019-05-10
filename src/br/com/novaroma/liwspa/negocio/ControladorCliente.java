package br.com.novaroma.liwspa.negocio;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import br.com.novaroma.liwspa.dados.Dao;
import br.com.novaroma.liwspa.entidades.Cliente;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.interfaces.IDao;

public class ControladorCliente implements IControlador<Cliente> {

	private IDao<Cliente> dao;

	public ControladorCliente() {
		dao = new Dao<>(Cliente.class);
	}

	public static boolean checarDados(Cliente cliente) {
		if (cliente.getCpf().trim() != null && cliente.getNome().trim() != null
				&& cliente.getSexo().trim().equals("feminino") && cliente.getTelefone().trim() != null
				&& cliente.getIdade() >= 18) {
			return true;
		}
		return false;
	}

	public static boolean checarCpfDuplicado(String cpf, List<Cliente> clientes) {
		for (Cliente cliente : clientes) {
			if (cliente.getCpf().equals(String.valueOf(cpf))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean adicionar(Cliente novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		boolean verificar = false;
		List<Cliente> clientes = dao.ler();

		if (checarDados(novoItem) && !checarCpfDuplicado(novoItem.getCpf(), clientes)) {
			clientes.add(novoItem);
			verificar = true;
			dao.salvar(clientes);
		}
		return verificar;
	}

	@Override
	public Cliente pegar(Object identificador) {

		try {
			List<Cliente> clientes = dao.ler();

			for (Cliente cliente : clientes) {
				if (cliente.getCpf().equals(String.valueOf(identificador))) {
					return cliente;
				}
			}
		} catch (IOException | ClassNotFoundException | ExcecaoIntegridadeArquivoInvalida e) {
		}

		return null;
	}

	@Override
	public boolean remover(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {
		List<Cliente> clientes = dao.ler();

		Iterator<Cliente> iterator = clientes.iterator();

		boolean verificacao = false;

		Cliente cliente = null;
		while (iterator.hasNext()) {
			cliente = iterator.next();
			if (cliente.getCpf().equals(String.valueOf(identificador))) {
				iterator.remove();
				verificacao = true;
				dao.salvar(clientes);
			}
		}
		if (verificacao == false) {
			throw new ExcecaoClienteNaoEncontrado("Cliente não encontrado.");
		}

		return verificacao;
	}

	@Override
	public boolean atualizar(Object identificador, Cliente novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {

		if (remover(identificador)) {
			adicionar(novoItem);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Integer quantidade()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {
		List<Cliente> clientes = dao.ler();
		return clientes.size();
	}

	@Override
	public List<Cliente> listar()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		return dao.ler();
	}
}
