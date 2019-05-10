package br.com.novaroma.liwspa.dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;
import br.com.novaroma.liwspa.interfaces.IDao;

@SuppressWarnings("all")
public class Dao<T> implements IDao<T> {

	private final Class<T> CLASSE;
	private final File DIR;
	private final File FILE;

	public Dao(Class<T> CLASSE) {
		this.CLASSE = CLASSE;
		this.DIR = new File("Arquivos");
		this.FILE = new File(this.DIR, this.CLASSE.getSimpleName() + ".txt");
	}

	@Override
	public void salvar(List<T> lista) throws IOException, ExcecaoIntegridadeArquivoInvalida {
		if (verificaIntegridadeArquivo()) {

			FileOutputStream fos = new FileOutputStream(FILE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(lista);
			oos.flush();
			fos.flush();

			fos.close();
		}
	}

	@Override
	public List<T> ler() throws IOException, ClassNotFoundException, ExcecaoIntegridadeArquivoInvalida {

		List<T> lista = null;

		if (verificaIntegridadeArquivo()) {
			if (arquivoVazio()) {
				lista = new ArrayList<>();
			} else {
				FileInputStream fis = new FileInputStream(FILE);
				ObjectInputStream ois = new ObjectInputStream(fis);

				lista = (ArrayList<T>) ois.readObject();

				ois.close();
				fis.close();
			}
		}
		return lista;
	}

	private boolean verificaIntegridadeArquivo() throws ExcecaoIntegridadeArquivoInvalida {
		try {
			if (!DIR.isDirectory())
				DIR.mkdir();

			if (!FILE.exists()) {
				FILE.createNewFile();
			}

			return true;
		} catch (IOException e) {
			throw new ExcecaoIntegridadeArquivoInvalida("Integridade do arquivo é duvidoso.");
		}
	}

	private boolean arquivoVazio() throws IOException {
		FileReader leitor = new FileReader(FILE);
		BufferedReader buffer = new BufferedReader(leitor);

		String linha = buffer.readLine();

		buffer.close();
		leitor.close();

		if (linha != null) {
			if (!linha.trim().equals(""))
				return false;
		}

		return true;
	}
}
