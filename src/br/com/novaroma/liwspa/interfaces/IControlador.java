package br.com.novaroma.liwspa.interfaces;

import java.io.IOException;
import java.util.List;

import br.com.novaroma.liwspa.excecoes.ExcecaoClienteNaoEncontrado;
import br.com.novaroma.liwspa.excecoes.ExcecaoIntegridadeArquivoInvalida;

public interface IControlador<T> {

	List<T> listar()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException;

	boolean adicionar(T novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException;

	T pegar(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException;

	boolean remover(Object identificador)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException;

	boolean atualizar(Object identificador, T novoItem)
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, ClassNotFoundException, IOException;

	Integer quantidade()
			throws ExcecaoClienteNaoEncontrado, ExcecaoIntegridadeArquivoInvalida, IOException, ClassNotFoundException;
}
