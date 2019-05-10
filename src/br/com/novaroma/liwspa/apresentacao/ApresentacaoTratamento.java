package br.com.novaroma.liwspa.apresentacao;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.novaroma.liwspa.entidades.Tratamento;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.negocio.ControladorTratamento;

public class ApresentacaoTratamento {

	public static boolean removerTratamento(IControlador<Tratamento> controlador, String id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		return controlador.remover(id);

	}

	public static boolean editarTratamento(IControlador<Tratamento> controlador, String id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		Tratamento c2 = controlador.pegar(id);

		JOptionPane.showMessageDialog(null, "Vamos editar o Tratamento!");

		c2.setNome(JOptionPane.showInputDialog("Nome:"));
		c2.setCusto(Double.parseDouble(JOptionPane.showInputDialog("Custo:")));
		c2.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Codigo:")));
		c2.setId(Integer.parseInt(JOptionPane.showInputDialog("ID:")));

		return controlador.atualizar(id, c2);
	}

	public static boolean cadastrarTratamento(IControlador<Tratamento> controlador)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		Tratamento c2 = new Tratamento();

		c2.setNome(JOptionPane.showInputDialog("Nome:"));
		c2.setCusto(Double.parseDouble(JOptionPane.showInputDialog("Custo:")));
		c2.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Codigo:")));
		c2.setId(Integer.parseInt(JOptionPane.showInputDialog("ID:")));

		return controlador.adicionar(c2);

	}

	public static Tratamento pegarTratamento(IControlador<Tratamento> controlador, int id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		Tratamento c2 = controlador.pegar(id);

		return c2;
	}

	public static void exibirMenuTratamento() throws ClassNotFoundException, IOException, HeadlessException,
	ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		IControlador<Tratamento> controladorTratamento = new ControladorTratamento();

		int opC;
		do {
			List<Tratamento> tratamentos = controladorTratamento.listar();
			if (!tratamentos.isEmpty()) {
				for (Tratamento tratamento : tratamentos) {

					System.out.println("Tratamento: " + tratamento.getNome() + "\tId: " + tratamento.getId());
				}
			} else {
				System.out.println("Não existe tratamentos!");
			}
			opC = Integer.parseInt(JOptionPane.showInputDialog(
					"Menu Tratamento " + "\n1 - Cadastrar" + "\n2 - Remover" + "\n3 - Editar" + "\n4 - Voltar"));

			if (opC == 1) {

				if (ApresentacaoTratamento.cadastrarTratamento(controladorTratamento)) {
					JOptionPane.showMessageDialog(null, "Tratamento cadastrado!");

				} else {
					JOptionPane.showMessageDialog(null, "Tratamento não cadastrado!");

				}

			} else if (opC == 2) {
				String id = JOptionPane.showInputDialog("Digite o id:");

				if (ApresentacaoTratamento.removerTratamento(controladorTratamento, id)) {
					JOptionPane.showMessageDialog(null, "Tratamento removido!");

				} else {
					JOptionPane.showMessageDialog(null, "Tratamento não existe!");

				}

			} else if (opC == 3) {

				String id = JOptionPane.showInputDialog("Digite o id:");
				if (ApresentacaoTratamento.editarTratamento(controladorTratamento, id)) {
					JOptionPane.showMessageDialog(null, "Tratamento Editado!");

				} else {
					JOptionPane.showMessageDialog(null, "Tratamento não existe!");

				}

			} else if (opC < 1 || opC > 4) {

				JOptionPane.showMessageDialog(null, "Erro, digite novamente!!!");

			}

		} while (opC != 4);

	}

}
