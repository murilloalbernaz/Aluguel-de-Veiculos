/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.dao;

/**
 *
 * @author murillo
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import murillo.albernaz.aluguelveiculos.model.Cadastro;

/**
 *
 * @author manoelcampos
 * @param <T>
 */
public class JpaDAO<T extends Cadastro> implements DAO<T> {

    private final EntityManager em;
    private final Class<T> classe;

    public JpaDAO(EntityManager em, Class<T> classe) {
        this.em = em;
        this.classe = classe;
    }

    @Override
    public T findById(long id) {
        return em.find(classe, id);
    }

    @Override
    public boolean delete(T entity) {
        em.remove(entity);
        return true;
    }

    @Override
    public long save(T entity) {
        /*Se a entidade tem um ID maior que 0 é porque está sendo
        alterada. Se estivesse sendo incluída, não teria um ID ainda.
        Assim, para inclusão usamos persist() e para alteração usamos merge().*/
        if (entity.getId() > 0) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }

        return entity.getId();
    }

    @Override
    public T findByField(String fieldName, Object value) {
        final String jpql = " select o from " + classe.getSimpleName() + " o "
                + " where o." + fieldName + " = :" + fieldName;
        TypedQuery<T> query = em.createQuery(jpql, classe);
        query.setParameter(fieldName, value);
        return query.getSingleResult();
    }

    @Override
    public TypedQuery<T> createQuery(String jpql) {
        return em.createQuery(jpql, classe);
    }

    @Override
    public List<T> findWithParameters(List<Parametro> parametros) {
        String jpql = " select o from " + classe.getSimpleName() + " o, Locadora as a, Cidade as c" + " where ";
        for (Parametro p : parametros) {
            if ("cidade".equals(p.getNomeParametro())) {
                jpql = jpql + "c.id = :cidade ";
            } else {
                if ("modelo".equals(p.getNomeParametro())) {
                    jpql = jpql + "and o.modelo = :modelo ";
                } else {
                    if ("precoInicial".equals(p.getNomeParametro())) {
                        jpql = jpql + "and o.valor >= :precoInicial ";
                    } else {
                        if ("precoFinal".equals(p.getNomeParametro())) {
                            jpql = jpql + "and o.valor <= :precoFinal ";
                        } else {
                            if ("arCondicionado".equals(p.getNomeParametro())) {
                                jpql = jpql + "and o.arCondicionado = :arCondicionado ";
                            } else {
                                if ("automatico".equals(p.getNomeParametro())) {
                                    jpql = jpql + "and o.automatico = :automatico ";
                                } else {
                                    if ("combustivel".equals(p.getNomeParametro())) {
                                        jpql = jpql + "and o.combustivel = :combustivel";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        TypedQuery<T> query = em.createQuery(jpql, classe);
        for (Parametro p : parametros) {
            if ("cidade".equals(p.getNomeParametro())) {
                query.setParameter("cidade", Long.parseLong(p.getValor()));
            } else {
                if ("modelo".equals(p.getNomeParametro())) {
                    query.setParameter("modelo", p.getValor());
                } else {
                    if ("precoInicial".equals(p.getNomeParametro())) {
                        query.setParameter("precoInicial", Double.parseDouble(p.getValor()));
                    } else {
                        if ("precoFinal".equals(p.getNomeParametro())) {
                             query.setParameter("precoFinal", Double.parseDouble(p.getValor()));
                        } else {
                            if ("arCondicionado".equals(p.getNomeParametro())) {
                                query.setParameter("arCondicionado", Boolean.parseBoolean(p.getValor()));
                            } else {
                                if ("automatico".equals(p.getNomeParametro())) {
                                    query.setParameter("automatico", Boolean.parseBoolean(p.getValor()));
                                } else {
                                    if ("combustivel".equals(p.getNomeParametro())) {
                                        query.setParameter("combustivel", p.getValor());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("passou aki"+query.getSingleResult().toString());
        return query.getResultList();
    }
}
