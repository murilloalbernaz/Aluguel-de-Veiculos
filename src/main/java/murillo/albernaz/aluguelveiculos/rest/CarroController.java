/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.rest;

import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import murillo.albernaz.aluguelveiculos.dao.CarroDao;
import murillo.albernaz.aluguelveiculos.model.Carro;
/**
 *
 * @author murillo
 */
@Path("/carro")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {
    @Inject 
    private CarroDao dao;
    
    @GET
    @Path("pesquisa1/{cidade}/{modelo}/{precoInicial}/{precoFinal}")
    public List<Carro> findby1(@PathParam("cidade") long cidade,@PathParam("modelo") String modelo, @PathParam("precoInicial") double ini, @PathParam("precoFinal") double fim){
        return dao.find1(cidade, modelo, ini, fim);
    }
    
   @GET
    @Path("pesquisa1/{cidade}/{precoInicial}/{precoFinal}/{arCondicionado}/{automatico}")
    public List<Carro> findby2(@PathParam("cidade") long cidade, @PathParam("precoInicial") double ini, @PathParam("precoFinal") double fim,@PathParam("arCondicionado") boolean arCondicionado,@PathParam("automatico") boolean automatico){
        return dao.find2(cidade, ini, fim, arCondicionado, automatico);
    }
    
    @GET
    @Path("pesquisa1/{cidade}/{precoInicial}/{precoFinal}/{arCondicionado}/{automatico}/{combustivel}")
    public List<Carro> findby3(@PathParam("cidade") long cidade, @PathParam("precoInicial") double ini, @PathParam("precoFinal") double fim,@PathParam("arCondicionado") boolean arCondicionado,@PathParam("automatico") boolean automatico, @PathParam("combustivel") String combustivel){
    return dao.find3(cidade, ini, fim, arCondicionado, automatico, combustivel);
    }
    
    @POST
    public long insert(Carro carro) {
        return dao.save(carro);
    }
}
