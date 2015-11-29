package br.upf.contatos.dal.dao;

import java.util.List;

/**
 *
 * @author Mauricley
 */
public interface Dao<T> {

	/**
	 * Método para inserir uma instância no banco de dados.
	 * @param instancia Instância a ser inserida no banco de dados.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 */ 
	public void persist(T instancia) throws Exception;

	/**
	 * Método para sincronizar uma instância no banco de dados.
	 * @param instancia Instância a ser sincronizada no banco de dados.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 * @return Instância sincronizada com o banco de dados.
	 */ 
	public T merge(T instancia) throws Exception;
	

	/**
	 * Método para excluir uma instância do banco de dados.
	 * @param instancia Instância a ser excluída do banco de dados.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 */ 
	public void remove(T instancia) throws Exception;
	
	/**
	 * Método para excluir uma instância do banco de dados a partir de seu id.
	 * @param id Identificador da instância a ser excluída do banco de dados.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 */ 
	public void remove(Integer id) throws Exception;
	
	/**
	 * Método para obter uma instância pelo código informado.
	 * @param id Código da instância a ser recuperada do banco de dados.
	 * @return Instância respectiva ou null caso o código não for 
	 * encontrado.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 */
	public T getById(Integer id) throws Exception;
	
	/**
	 * Método para obter uma lista de todas as instâncias contidas no 
	 * banco de dados.
	 * @return coleção com as instâncias encontradas. 
	 * Caso nenhuma instância for encontrada a coleção deverá ser vazia, mas inicializada.
	 * @throws Exception Possível exceção gerada ao realizar a operação.
	 */
	public List<T> getList() throws Exception;

	
}