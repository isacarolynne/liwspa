package br.com.novaroma.liwspa.apresentacao;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.novaroma.liwspa.entidades.Endereco;
import br.com.novaroma.liwspa.entidades.Funcionario;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.negocio.ControladorFuncionario;
import br.com.novaroma.liwspa.utils.ValidaCPF;


public class ApresentacaoFuncionario {

	public static boolean removerFuncionario(IControlador<Funcionario> controlador, String cpf)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		while (!ValidaCPF.isValidCPF(cpf)) {

			cpf = JOptionPane.showInputDialog("CPF: ");

		}

		return controlador.remover(cpf);

	}

	public static boolean editarFuncionario(IControlador<Funcionario> controlador, String cpf)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {
		Funcionario c2 = controlador.pegar(cpf);

		JOptionPane.showMessageDialog(null, "Vamos editar o Cadastro!");

		c2.setNome(JOptionPane.showInputDialog("Nome: "));
		c2.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Idade: ")));
		c2.setTelefone(JOptionPane.showInputDialog("Telefone: "));
		c2.setSenha(JOptionPane.showInputDialog("Senha: "));
		c2.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Salario: ")));

		c2.getEndereco().setRua(JOptionPane.showInputDialog("Rua: "));
		c2.getEndereco().setCep(JOptionPane.showInputDialog("Cep: "));
		c2.getEndereco().setBairro(JOptionPane.showInputDialog("Bairro: "));
		c2.getEndereco().setCidade(JOptionPane.showInputDialog("Cidade: "));
		c2.getEndereco().setEstado(JOptionPane.showInputDialog("Estado: "));
		c2.getEndereco().setNumero(JOptionPane.showInputDialog("Numero: "));

		return controlador.atualizar(cpf, c2);
	}

	public static boolean cadastrarFuncionario(IControlador<Funcionario> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {
		JOptionPane.showMessageDialog(null, "Vamos iniciar o Cadastro!");

		String cpf;
		do {
			cpf = JOptionPane.showInputDialog("CPF: ");
		} while (!ValidaCPF.isValidCPF(cpf));

		Funcionario c2 = new Funcionario();
		c2.setNome(JOptionPane.showInputDialog("Nome: "));
		c2.setCpf(cpf);

		c2.setIdade(Integer.parseInt(JOptionPane.showInputDialog("Idade: ")));
		c2.setTelefone(JOptionPane.showInputDialog("Telefone: "));
		c2.setSenha(JOptionPane.showInputDialog("Senha: "));
		c2.setSalario(Double.parseDouble(JOptionPane.showInputDialog("Salario: ")));

		Endereco e = new Endereco();
		e.setRua(JOptionPane.showInputDialog("Rua: "));
		e.setNumero(JOptionPane.showInputDialog("Número: "));
		e.setBairro(JOptionPane.showInputDialog("Bairro: "));
		e.setCep(JOptionPane.showInputDialog("Cep: "));
		e.setCidade(JOptionPane.showInputDialog("Cidade: "));
		e.setEstado(JOptionPane.showInputDialog("Estado: "));

		c2.setEndereco(e);
		return controlador.adicionar(c2);
	}

	public static void exibirMenuFuncionario() throws ClassNotFoundException, IOException, HeadlessException,
			ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {
		IControlador<Funcionario> controladorFuncionario = new ControladorFuncionario();

		int opC;
		do {
			opC = Integer.parseInt(JOptionPane.showInputDialog(
					"Menu Funcionario " + "\n1 - Cadastrar" + "\n2 - Remover" + "\n3 - Editar" + "\n4 - Voltar"));

			if (opC == 1) {
				if (ApresentacaoFuncionario.cadastrarFuncionario(controladorFuncionario)) {
					JOptionPane.showMessageDialog(null, "Funcionario cadastrado!");
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não cadastrado!");
				}

			} else if (opC == 2) {
				String cpf = JOptionPane.showInputDialog("CPF:");

				while (!ValidaCPF.isValidCPF(cpf)) {
					cpf = JOptionPane.showInputDialog("CPF:");
				}

				if (ApresentacaoFuncionario.removerFuncionario(controladorFuncionario, cpf)) {
					JOptionPane.showMessageDialog(null, "Funcionario removido!");
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não cadastrado!");
				}

			} else if (opC == 3) {
				String cpf = JOptionPane.showInputDialog("CPF:");

				while (!ValidaCPF.isValidCPF(cpf)) {
					cpf = JOptionPane.showInputDialog("CPF:");
				}

				if (ApresentacaoFuncionario.editarFuncionario(controladorFuncionario, cpf)) {
					JOptionPane.showMessageDialog(null, "Funcionario Editado!");
				} else {
					JOptionPane.showMessageDialog(null, "Funcionario não cadastrado!");
				}
			} else if (opC < 1 || opC > 4) {
				JOptionPane.showMessageDialog(null, "Erro, digite novamente!!!");
			}

		} while (opC != 4);

	}
}
