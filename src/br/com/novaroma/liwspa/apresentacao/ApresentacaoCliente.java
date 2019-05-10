package br.com.novaroma.liwspa.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.novaroma.liwspa.entidades.Cliente;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.negocio.ControladorCliente;
import br.com.novaroma.liwspa.utils.ValidaCPF;

public class ApresentacaoCliente {

	public static boolean removerCliente(IControlador<Cliente> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		String cpf = JOptionPane.showInputDialog("CPF: ");

		while (!ValidaCPF.isValidCPF(cpf)) {

			cpf = JOptionPane.showInputDialog("CPF: ");

		}

		return controlador.remover(cpf);

	}

	public static boolean editarCliente(IControlador<Cliente> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		String cpf = JOptionPane.showInputDialog("CPF: ");

		while (!ValidaCPF.isValidCPF(cpf)) {

			cpf = JOptionPane.showInputDialog("CPF: ");

		}

		Cliente c2 = controlador.pegar(cpf);

		JOptionPane.showMessageDialog(null, "Vamos editar o Cadastro!");

		c2.setNome(JOptionPane.showInputDialog("Nome: "));

		c2.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Idade: ")));
		c2.setTelefone(JOptionPane.showInputDialog("Telefone: "));

		return controlador.atualizar(cpf, c2);
	}

	public static boolean cadastrarCliente(IControlador<Cliente> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {
		JOptionPane.showMessageDialog(null, "Vamos iniciar o Cadastro!");
		String nome = JOptionPane.showInputDialog("Nome:");

		String cpf;

		do {

			cpf = JOptionPane.showInputDialog("CPF:");

		} while (!ValidaCPF.isValidCPF(cpf));

		Cliente c2 = null;

		int idade = Integer.parseInt(JOptionPane.showInputDialog("Idade:"));
		String telefone = JOptionPane.showInputDialog("Telefone:");
		String sexo = JOptionPane.showInputDialog("Sexo:");

		c2 = new Cliente(nome, cpf, telefone, idade, sexo);

		return controlador.adicionar(c2);
	}

	public static Cliente pegarCliente(IControlador<Cliente> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		String cpf = JOptionPane.showInputDialog("CPF: ");

		while (!ValidaCPF.isValidCPF(cpf)) {

			cpf = JOptionPane.showInputDialog("CPF: ");

		}

		Cliente c2 = controlador.pegar(cpf);

		return c2;
	}

	public static void exibirMenuCliente()
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		IControlador<Cliente> controladorCliente = new ControladorCliente();

		int escolha;
		int opC;
		do {

			opC = Integer.parseInt(JOptionPane.showInputDialog("Menu Cliente " + "\n1 - Cadastrar" + "\n2 - Remover"
					+ "\n3 - Editar" + "\n4 - Listar Clientes" + "\n5 - Voltar"));

			if (opC == 1) {

				boolean isClienteCadastrado;
				do {

					escolha = Integer.parseInt(
							JOptionPane.showInputDialog("Já é cadastrado no Liwspa?" + "\n1 - SIM" + "\n2 - NÃO"));

					if (escolha == 1) {
						String cpfCliente = JOptionPane.showInputDialog("Digite o CPF: ");
						Cliente cliente = controladorCliente.pegar(cpfCliente);

						if (cliente == null) {
							JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
							isClienteCadastrado = false;
						} else {
							JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.getNome());
							isClienteCadastrado = true;
						}

					} else {

						if (ApresentacaoCliente.cadastrarCliente(controladorCliente)) {
							JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
							isClienteCadastrado = true;

						} else {
							JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
							isClienteCadastrado = false;

						}
					}

				} while (!isClienteCadastrado);

			} else if (opC == 2) {

				if (ApresentacaoCliente.removerCliente(controladorCliente)) {
					JOptionPane.showMessageDialog(null, "Cliente removido!");

				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");

				}

			} else if (opC == 3) {

				if (ApresentacaoCliente.editarCliente(controladorCliente)) {
					JOptionPane.showMessageDialog(null, "Cliente Editado!");

				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");

				}

			} else if (opC == 4) {
				System.out.println("Todos os Clientes:\n");
				List<Cliente> clientes = controladorCliente.listar();
				for (Cliente clienteAtual : clientes) {
					System.out.println(clienteAtual.getNome() + "\t" + clienteAtual.getCpf() + "\n");
				}

			} else if (opC < 1 || opC > 5) {
				JOptionPane.showMessageDialog(null, "Erro, digite novamente!!!");
			}
		} while (opC != 5);
	}

}
