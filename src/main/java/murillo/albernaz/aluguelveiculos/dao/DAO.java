/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import murillo.albernaz.aluguelveiculos.model.Cadastro;

/**
 *
 * @author murillo
 * @param <T>
 */
public interface DAO<T extends Cadastro> {
    /**
     * Cria um objeto para executar uma consulta JPQL.
     * @param jpql consulta JPQL para localizar um objeto
     * @return o objeto query criado
     */
    TypedQuery<T> createQuery(String jpql);

    /**
     * Busca um objeto a partir do seu id
     * @param id id do objeto a ser localizado
     * @return o objeto localizado ou null caso não seja encontrado
     */
    T findById(long id);

    /**
     * Busca um objeto a partir de um determinado campo
     * @param fieldName nome do atributo do objeto a ser utilizado para busca
     * @param value valor do atributo a ser utilizado na busca
     * @return o objeto localizado ou null caso não seja encontrado
     */
    T findByField(String fieldName, Object value);

    /**
     * Remove um objeto do banco a partir de uma instância do próprio objeto.
     * @param entity objeto a ser removido
     * @return true se o objeto foi localizado e removido com sucesso, false caso contrário
     */
    boolean delete(T entity);
    
    /**
     * Salva um objeto no banco,
     * que pode ser tanto uma inclusão ou alteração.
     * Se o objeto já existir, será atualizado.
     * Senão, será incluído.
     * @param entity objeto a ser salvo
     * @return id do objeto salvo
     */
    long save(T entity);
    
    /**
     *
     * @param parametros
     * @return
     */
}