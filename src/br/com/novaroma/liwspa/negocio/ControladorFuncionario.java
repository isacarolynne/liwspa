package br.com.novaroma.liwspa.negocio;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.novaroma.liwspa.dados.Dao;
import br.com.novaroma.liwspa.entidades.Cliente;
import br.com.novaroma.liwspa.entidades.Funcionario;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.interfaces.IDao;

@SuppressWarnings("all")
public class ControladorFuncionario implements IControlador<Funcionario> {
	private IDao<Funcionario> dao;

	public ControladorFuncionario() {
		dao = new Dao<>(Funcionario.class);
	}

	public static boolean checarDados(Funcionario funcionario) {
		if (funcionario.getCpf().trim() != null && funcionario.getNome().trim() != null
				&& funcionario.getTelefone().trim() != null && funcionario.getIdade() >= 18
				&& funcionario.getSalario() != null && funcionario.getEndereco().getBairro().trim() != null
				&& funcionario.getEndereco().getRua().trim() != null && funcionario.getEndereco().getCep() != null
				&& funcionario.getEndereco().getCidade().trim() != null
				&& funcionario.getEndereco().getEstado().trim() != null
				&& funcionario.getEndereco().getNumero() != null) {
			return true;
		}
		return false;
	}

	public static boolean checarCpfDuplicado(String cpf, List<Funcionario> funcionarios) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getCpf().equals(String.valueOf(cpf))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean adicionar(Funcionario novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		boolean verificar = false;
		List<Funcionario> funcionarios = dao.ler();

		if (checarDados(novoItem) && !checarCpfDuplicado(novoItem.getCpf(), funcionarios)) {
			funcionarios.add(novoItem);
			verificar = true;
			dao.salvar(funcionarios);
		}
		return verificar;
	}

	@Override
	public Funcionario pegar(Object identificador) {

		try {
			List<Funcionario> funcionarios = dao.ler();

			for (Funcionario funcionario : funcionarios) {
				if (funcionario.getCpf().equals(String.valueOf(identificador))) {
					return funcionario;
				}
			}
		} catch (IOException | ClassNotFoundException | ExcecaoIntegridadeArquivoInvalida e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public boolean remover(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException {
		List<Funcionario> funcionarios = dao.ler();

		Iterator<Funcionario> iterator = funcionarios.iterator();

		boolean verificacao = false;

		Funcionario funcionario = null;
		while (iterator.hasNext()) {
			funcionario = iterator.next();
			if (funcionario.getCpf().equals(String.valueOf(identificador))) {
				iterator.remove();
				verificacao = true;
				dao.salvar(funcionarios);
			}
		}

		return verificacao;
	}

	@Override
	public boolean atualizar(Object identificador, Funcionario novoItem)
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
		List<Funcionario> funcionarios = dao.ler();
		return funcionarios.size();
	}

	@Override
	public List<Funcionario> listar() throws ClassNotFoundException, IOException {
		return null;
	}

	public int vagaAtendimento(List<Cliente> clientes, Funcionario funcionario, Date data) {
		int contador = 0;
		for (Cliente cliente : clientes) {

			if (funcionario.getId() == cliente.getIdFuncionario() && cliente.getData() == data) {

				contador++;
			}

		}
		return contador;

	}

	private Object getIdFuncionario(int i) {
		return null;
	}

	public Funcionario verificarLogin(String nome, String senha)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException {
		List<Funcionario> funcionarios = dao.ler();

		Funcionario funcionario = null;

		for (Funcionario funcionar : funcionarios) {

			if (funcionar.getNome().equals(nome) && funcionar.getSenha().equals(senha)) {
				funcionario = funcionar;
			}
		}
		return funcionario;

	}

}
