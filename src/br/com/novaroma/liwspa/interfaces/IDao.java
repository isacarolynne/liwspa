package br.com.novaroma.liwspa.interfaces;

import java.io.IOException;
import java.util.List;

import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;

public interface IDao<T> {
	void salvar(List<T> lista) throws IOException, ExcecaoIntegridadeArquivoInvalida;

	List<T> ler() throws IOException, ClassNotFoundException, ExcecaoIntegridadeArquivoInvalida;
}
