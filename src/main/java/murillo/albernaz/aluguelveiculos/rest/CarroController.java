/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package murillo.albernaz.aluguelveiculos.rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import murillo.albernaz.aluguelveiculos.dao.DAO;
import murillo.albernaz.aluguelveiculos.model.Carro;
import murillo.albernaz.aluguelveiculos.dao.Parametro;
/**
 *
 * @author murillo
 */
@Path("/carro")
@Transactional
@Consumes(MediaType.APPLICATION_JSON)
public class CarroController {
    @Inject 
    private DAO<Carro> dao;
    
    @GET
    @Path("pesquisa1/{cidade}/{modelo}/{precoInicial}/{precoFinal}")
    public List<Carro> findby1(@PathParam("cidade") String cidade,@PathParam("modelo") String modelo, @PathParam("precoInicial") String ini, @PathParam("precoFinal") String fim){
        List<Parametro> p = new ArrayList<>();
        p.add(new Parametro("cidade", cidade));
        p.add(new Parametro("modelo", modelo));
        p.add(new Parametro("precoInicial", ini));
        p.add(new Parametro("precoFinal", fim));
        return dao.findWithParameters(p);
    }
    
    @GET
    @Path("pesquisa1/{cidade}/{precoInicial}/{precoFinal}/{arCondicionado}/{automatico}")
    public List<Carro> findby2(@PathParam("cidade") String cidade, @PathParam("precoInicial") String ini, @PathParam("precoFinal") String fim,@PathParam("arCondicionado") String arCondicionado,@PathParam("automatico") String automatico){
        List<Parametro> p = new ArrayList<>();
        p.add(new Parametro("cidade", cidade));
        p.add(new Parametro("precoInicial", ini));
        p.add(new Parametro("precoFinal", fim));
        p.add(new Parametro("arCondicionado", arCondicionado));
        p.add(new Parametro("automatico", automatico));
        return dao.findWithParameters(p);
    }
    
    @GET
    @Path("pesquisa1/{cidade}/{precoInicial}/{precoFinal}/{arCondicionado}/{automatico}/{combustivel}")
    public List<Carro> findby3(@PathParam("cidade") String cidade, @PathParam("precoInicial") String ini, @PathParam("precoFinal") String fim,@PathParam("arCondicionado") String arCondicionado,@PathParam("automatico") String automatico, @PathParam("combustivel") String combustivel){
    List<Parametro> p = new ArrayList<>();
    p.add(new Parametro("cidade", cidade));
    p.add(new Parametro("precoInicial", ini));
    p.add(new Parametro("precoFinal", fim));
    p.add(new Parametro("arCondicionado", arCondicionado));
    p.add(new Parametro("automatico", automatico));
    p.add(new Parametro("combustivel", combustivel));
    return dao.findWithParameters(p);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public long insert(Carro carro) {
        return dao.save(carro);
    }
}
