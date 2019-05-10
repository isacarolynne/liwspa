package br.com.novaroma.liwspa.apresentacao;

import java.awt.HeadlessException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.novaroma.liwspa.entidades.Agendamento;
import br.com.novaroma.liwspa.entidades.Cliente;
import br.com.novaroma.liwspa.entidades.Tratamento;
import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.negocio.ControladorAgendamento;
import br.com.novaroma.liwspa.negocio.ControladorCliente;
import br.com.novaroma.liwspa.negocio.ControladorTratamento;
import br.com.novaroma.liwspa.utils.FormaPagamento;
import br.com.novaroma.liwspa.utils.ValidaCPF;

public class ApresentacaoAgendamento {

	public static boolean removerAgendamento(IControlador<Agendamento> controlador, String id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		return controlador.remover(id);

	}

	public static boolean editarAgendamento(IControlador<Agendamento> controlador, String id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		Agendamento c2 = controlador.pegar(id);
		if (c2 != null) {

			JOptionPane.showMessageDialog(null, "Vamos editar o Agendamento!");

			String dat = JOptionPane.showInputDialog("Digite a Data, Ex= 22/05/1992");
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data = LocalDate.parse(dat, formato);

			String hor = JOptionPane.showInputDialog("Digite a Hora, Ex= 14:45");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime hora = LocalTime.parse(hor, format);

			if (!data.isBefore(LocalDate.now())) {

				c2.setData(data);
				c2.setHora(hora);

				int escolhaPagamento;
				do {
					escolhaPagamento = Integer
							.parseInt(JOptionPane.showInputDialog("Escolha sua forma de pagamento: \n1 - MASTER"
									+ "\n2 - VISA" + "\n3 - HIPER" + "\n4 - DIHEIRO"));

					if (escolhaPagamento == 1) {
						c2.setFormaPagamento(FormaPagamento.CARTAO_MASTERCARD);
					} else if (escolhaPagamento == 2) {
						c2.setFormaPagamento(FormaPagamento.CARTAO_VISA);
					} else if (escolhaPagamento == 3) {
						c2.setFormaPagamento(FormaPagamento.CARTAO_HIPER);
					} else if (escolhaPagamento == 4) {
						c2.setFormaPagamento(FormaPagamento.DINHEIRO);
					} else {
						JOptionPane.showMessageDialog(null, "Valor invalido!");
					}
				} while (escolhaPagamento < 1 || escolhaPagamento > 4);

				return controlador.atualizar(id, c2);
			}
		}
		return false;
	}

	public static boolean cadastrarAgendamento(IControlador<Agendamento> controlador, String cpf,
			List<Tratamento> tratamentos)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		IControlador<Tratamento> controladorTratamento = new ControladorTratamento();
		IControlador<Cliente> controladorCliente = new ControladorCliente();

		Agendamento c2 = new Agendamento();
		Cliente cc = controladorCliente.pegar(cpf);
		if (cc != null) {
			c2.setCliente(cc);

		} else {
			System.out.println("Cliente não existe!");
		}

		int id;
		do {

			id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID: \nDigite 0 para a próxima etapa: "));
			if (id != 0) {
				Tratamento tratamento = controladorTratamento.pegar(id);
				if (tratamento != null) {
					c2.getTratamentos().add(tratamento);
				} else {
					System.out.println("Tratamento não existe!");
				}
			}
		} while (id != 0);

		double somaTotal = 0;
		for (Tratamento tratamento : c2.getTratamentos()) {

			somaTotal += tratamento.getCusto();

		}
		System.out.println("Custo total: " + somaTotal);
		c2.setCustoTotal(somaTotal);

		String dat = JOptionPane.showInputDialog("Digite a Data, Ex= 22/05/1992");
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dat, formato);

		String hor = JOptionPane.showInputDialog("Digite a Hora, Ex= 14:45");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.parse(hor, format);

		if (!data.isBefore(LocalDate.now())) {

			c2.setData(data);
			c2.setHora(hora);

			int escolhaPagamento;
			do {
				escolhaPagamento = Integer
						.parseInt(JOptionPane.showInputDialog("Escolha sua forma de pagamento: \n1 - MASTER"
								+ "\n2 - VISA" + "\n3 - HIPER" + "\n4 - DIHEIRO"));

				if (escolhaPagamento == 1) {
					c2.setFormaPagamento(FormaPagamento.CARTAO_MASTERCARD);
				} else if (escolhaPagamento == 2) {
					c2.setFormaPagamento(FormaPagamento.CARTAO_VISA);
				} else if (escolhaPagamento == 3) {
					c2.setFormaPagamento(FormaPagamento.CARTAO_HIPER);
				} else if (escolhaPagamento == 4) {
					c2.setFormaPagamento(FormaPagamento.DINHEIRO);
				} else {
					JOptionPane.showMessageDialog(null, "Valor invalido!");
				}
			} while (escolhaPagamento < 1 || escolhaPagamento > 4);

			c2.setId(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID para o agendamento:")));

			return controlador.adicionar(c2);
		} else {

			System.out.println("Data Inválida!");
			return false;
		}

	}

	public static Agendamento pegarAgendamento(IControlador<Agendamento> controlador, String id)
			throws ClassNotFoundException, IOException, ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {

		Agendamento c2 = controlador.pegar(id);

		return c2;
	}

	public static void exibirMenuAgendamento() throws ClassNotFoundException, IOException, HeadlessException,
			ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida {
		IControlador<Agendamento> controladorAgendamento = new ControladorAgendamento();

		int opC;
		do {
			List<Agendamento> agendamentos = controladorAgendamento.listar();
			if (!agendamentos.isEmpty()) {
				for (Agendamento agendamento : agendamentos) {

					System.out.println("Nome: " + agendamento.getCliente().getNome() + "\tData: "
							+ agendamento.getData() + "\tHora: " + agendamento.getHora() + "\tForma de pagamento: "
							+ agendamento.getFormaPagamento());
				}
			} else {
				System.out.println("Não existe agendamento!");
			}

			opC = Integer.parseInt(JOptionPane.showInputDialog(
					"Menu Agendamento " + "\n1 - Cadastrar" + "\n2 - Remover" + "\n3 - Editar" + "\n4 - Voltar"));

			if (opC == 1) {

				String cpf = JOptionPane.showInputDialog("CPF: ");

				while (!ValidaCPF.isValidCPF(cpf)) {

					cpf = JOptionPane.showInputDialog("CPF: ");

				}
				IControlador<Tratamento> controladorTratamento = new ControladorTratamento();
				List<Tratamento> tratamentos = controladorTratamento.listar();

				for (Tratamento tratamento : tratamentos) {
					System.out.println("Tratamento: " + tratamento.getNome() + "\tId: " + tratamento.getId());
				}
				if (ApresentacaoAgendamento.cadastrarAgendamento(controladorAgendamento, cpf, tratamentos)) {
					JOptionPane.showMessageDialog(null, "Agendamento cadastrado!");

				} else {
					JOptionPane.showMessageDialog(null, "Agendamento não cadastrado!");

				}

			} else if (opC == 2) {
				String id = JOptionPane.showInputDialog("Digite o ID do agendamento:");

				if (ApresentacaoAgendamento.removerAgendamento(controladorAgendamento, id)) {
					JOptionPane.showMessageDialog(null, "Agendamento removido!");

				} else {
					JOptionPane.showMessageDialog(null, "Agendamento não existe!");

				}

			} else if (opC == 3) {

				String id = JOptionPane.showInputDialog("Digite o ID do agendamento:");
				if (ApresentacaoAgendamento.editarAgendamento(controladorAgendamento, id)) {
					JOptionPane.showMessageDialog(null, "Agendamento Editado!");

				} else {
					JOptionPane.showMessageDialog(null, "Agendamento não existe!");

				}

			} else if (opC < 1 || opC > 4) {

				JOptionPane.showMessageDialog(null, "Erro, digite novamente!!!");

			}

		} while (opC != 4);

	}
}
