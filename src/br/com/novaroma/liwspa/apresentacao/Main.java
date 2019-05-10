package br.com.novaroma.liwspa.apresentacao;

import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import br.com.novaroma.liwspa.entidades.Funcionario;
import br.com.novaroma.liwspa.interfaces.IControlador;
import br.com.novaroma.liwspa.negocio.ControladorFuncionario;

public class Main {
	public static void main(String[] args) {

		IControlador<Funcionario> controladorFuncionario = new ControladorFuncionario();

		try {

			int finalizarPrograma;
			do {
				Funcionario funcionario = null;
				do {
					JOptionPane.showMessageDialog(null, "Bem-vindo ao Liwspa!");
					JOptionPane.showMessageDialog(null, "Vamos fazer o Login!");

					String nomeFuncionario = JOptionPane.showInputDialog("Digite o nome:");

					JPanel panelSenha = new JPanel();
					JLabel labelDigiteSenha = new JLabel("Digite a senha:");
					JPasswordField jpasswordFieldSenha = new JPasswordField(20);
					panelSenha.add(labelDigiteSenha);
					panelSenha.add(jpasswordFieldSenha);
					String[] joptionDialogOpcoes = new String[] { "OK", "Cancelar" };
					int opcao = JOptionPane.showOptionDialog(null, panelSenha, "LIWSPA", JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, joptionDialogOpcoes, joptionDialogOpcoes[0]);

					String senha = null;
					if (opcao == 0) {
						char[] password = jpasswordFieldSenha.getPassword();
						senha = new String(password);
					}
					funcionario = ((ControladorFuncionario) controladorFuncionario).verificarLogin(nomeFuncionario,
							senha);
				} while (funcionario == null);

				int opcF;
				do {

					opcF = Integer.parseInt(JOptionPane.showInputDialog("Menu" + "\n1 - Funcionario" + "\n2 - Cliente"
							+ "\n3 - Tratamento" + "\n4 - Agendamento" + "\n5 - Logout"));

					if (opcF == 1) {
						ApresentacaoFuncionario.exibirMenuFuncionario();
					} else if (opcF == 2) {
						ApresentacaoCliente.exibirMenuCliente();
					} else if (opcF == 3) {
						ApresentacaoTratamento.exibirMenuTratamento();
					} else if (opcF == 4) {
						ApresentacaoAgendamento.exibirMenuAgendamento();
					} else if (opcF < 1 || opcF > 5) {
						JOptionPane.showMessageDialog(null, "Erro, digite novamente!!!");
					}

				} while (opcF != 5);

				finalizarPrograma = Integer.parseInt(JOptionPane
						.showInputDialog("Digite 0 (zero) para sair do programa ou qualquer numero para continuar..."));

			} while (finalizarPrograma != 0);

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}