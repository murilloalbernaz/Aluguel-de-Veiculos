/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import murillo.albernaz.aluguelveiculos.model.Carro;

/**
 *
 * @author murillo
 */
public class CarroDao extends JpaDAO<Carro>{

    public CarroDao(EntityManager em) {
        super(em, Carro.class);
    }

    
    
    public List<Carro> find1(long cidade, String modelo, double precoInicial, double precoFinal ){
        String jpql = " select o from Carro o, Locadora as a, Cidade as c where c.id = :cidade and o.modelo = :modelo and o.valor >= :precoInicial and o.valor <= :precoFinal";
        TypedQuery<Carro> query = em.createQuery(jpql, Carro.class);
        query.setParameter("cidade", cidade);
        query.setParameter("modelo", modelo);
        query.setParameter("precoInicial", precoInicial);
        query.setParameter("precoFinal", precoFinal);
        return query.getResultList();
    };
    
    public List<Carro> find2(long cidade, double precoInicial, double precoFinal, boolean arCondicionado, boolean automatico){
        String jpql = " select o from Carro o, Locadora as a, Cidade as c where c.id = :cidade and o.valor >= :precoInicial and o.valor <= :precoFinal and o.arCondicionado = :arCondicionado and o.automatico = :automatico";
        TypedQuery<Carro> query = em.createQuery(jpql, Carro.class);
        query.setParameter("cidade", cidade);
        query.setParameter("precoInicial", precoInicial);
        query.setParameter("precoFinal", precoFinal);
        query.setParameter("arCondicionado",arCondicionado);
        query.setParameter("automatico",automatico);
        return query.getResultList();
    }
    
    public List<Carro> find3(long cidade, double precoInicial, double precoFinal, boolean arCondicionado, boolean automatico,String combustivel ){
        String jpql = " select o from Carro o, Locadora as a, Cidade as c where c.id = :cidade and o.valor >= :precoInicial and o.valor <= :precoFinal and o.arCondicionado = :arCondicionado and o.automatico = :automatico and o.combustivel = :combustivel";
        TypedQuery<Carro> query = em.createQuery(jpql, Carro.class);
        query.setParameter("cidade", cidade);
        query.setParameter("precoInicial", precoInicial);
        query.setParameter("precoFinal", precoFinal);
        query.setParameter("arCondicionado",arCondicionado);
        query.setParameter("automatico",automatico);
        query.setParameter("combustivel", combustivel);
        return query.getResultList();
    }
   
}
