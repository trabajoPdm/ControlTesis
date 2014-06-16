/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.ues.fia.grupo09.servicio.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import sv.ues.fia.grupo09.entidad.Nota;
import sv.ues.fia.grupo09.entidad.NotaPK;

/**
 *
 * @author oscar
 */
@Stateless
//para acceder al controlador del servicion nota especifico en la url el pad del servicio
@Path("sv.ues.fia.grupo09.entidad.nota")
public class NotaFacadeREST extends AbstractFacade<Nota> {
    @PersistenceContext(unitName = "ControlTesisWebPU")
    private EntityManager em;

    private NotaPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;idetapa=idetapaValue;carnet=carnetValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        sv.ues.fia.grupo09.entidad.NotaPK key = new sv.ues.fia.grupo09.entidad.NotaPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> idetapa = map.get("idetapa");
        if (idetapa != null && !idetapa.isEmpty()) {
            key.setIdetapa(idetapa.get(0));
        }
        java.util.List<String> carnet = map.get("carnet");
        if (carnet != null && !carnet.isEmpty()) {
            key.setCarnet(carnet.get(0));
        }
        return key;
    }

    public NotaFacadeREST() {
        super(Nota.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response createNota(Nota entity) {
        super.create(entity);
        return Response.ok().build();
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Response editNota(Nota entity) {
        super.edit(entity);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") PathSegment id) {
        sv.ues.fia.grupo09.entidad.NotaPK key = getPrimaryKey(id);
        super.remove(super.find(key));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Nota find(@PathParam("id") PathSegment id) {
        sv.ues.fia.grupo09.entidad.NotaPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Nota> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Nota> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
