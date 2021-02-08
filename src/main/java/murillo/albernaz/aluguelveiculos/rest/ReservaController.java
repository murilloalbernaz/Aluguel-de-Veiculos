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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import murillo.albernaz.aluguelveiculos.model.Reserva;
import murillo.albernaz.aluguelveiculos.dao.DAO;

/**
 *
 * @author murillo
 */
@Path("/reserva")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
public class ReservaController {
    @Inject
    private DAO<Reserva> dao;
    
    @POST
    public long insert(Reserva reserva) {
        return dao.save(reserva);
    }
    
    @PUT
    public boolean update(Reserva reserva) {
        return dao.save(reserva) > 0;
    }
    
    @DELETE
    @Path("{id}")
    public boolean delete(@PathParam("id") long id) {
        Reserva reserva = dao.findById(id);
        if(reserva == null){
            //Se o objeto não for encontrado no BD, retorna código HTTP 404: página não encontrada.
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return dao.delete(reserva);
    }
}
