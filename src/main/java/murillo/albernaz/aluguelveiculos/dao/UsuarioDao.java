/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.dao;

import javax.persistence.EntityManager;
import murillo.albernaz.aluguelveiculos.model.Usuario;

/**
 *
 * @author murillo
 */
public class UsuarioDao extends JpaDAO<Usuario>{

    public UsuarioDao(EntityManager em) {
        super(em, Usuario.class);
    }
    @Override
    public Usuario findById(long id){
        Usuario user = super.findById(id);
        user.setSenha(null);
        return user;
    }
}
