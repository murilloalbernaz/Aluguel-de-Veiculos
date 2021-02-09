/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import murillo.albernaz.aluguelveiculos.dao.UsuarioDao;
import murillo.albernaz.aluguelveiculos.model.Usuario;
import murillo.albernaz.aluguelveiculos.utils.Md5;

/**
 *
 * @author murillo
 */
@Path("/usuario")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioController {
    @Inject
    private UsuarioDao Usuariodao;
    @Inject
    private Md5 md5;
    
    @POST
    public long insert(Usuario usuario){
        String senhaMd5 = md5.hasMd5(usuario.getSenha());
        if (senhaMd5 != null)
            usuario.setSenha(senhaMd5);
        return Usuariodao.save(usuario);
    }
    
    @PUT
    public boolean update(Usuario usuario){
        String senhaMd5 = md5.hasMd5(usuario.getSenha());
        if (senhaMd5 != null)
            usuario.setSenha(senhaMd5);
        return Usuariodao.save(usuario) > 0;
    }
    
    @GET
    @Path("/pesquisaUsuario/{id}")
    public Usuario findUser(@PathParam("id") long id){
        return Usuariodao.findById(id);
    }
    
    @DELETE
    @Path("{id}")
    public boolean delete(@PathParam("id") long id){
        Usuario usuario = Usuariodao.findById(id);
        if(usuario == null){
            //Se o objeto não for encontrado no BD, retorna código HTTP 404: página não encontrada.
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Usuariodao.delete(usuario);
    }
}
