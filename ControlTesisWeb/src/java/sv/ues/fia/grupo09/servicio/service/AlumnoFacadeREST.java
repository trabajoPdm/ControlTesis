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
import javax.ws.rs.core.Response;
import sv.ues.fia.grupo09.entidad.Alumno;

/**
 *
 * @author oscar
 */
@Stateless
//para acceder a cada controlador se indica la ruta del path
@Path("sv.ues.fia.grupo09.entidad.alumno")
public class AlumnoFacadeREST extends AbstractFacade<Alumno> {
    @PersistenceContext(unitName = "ControlTesisWebPU")
    private EntityManager em;

    public AlumnoFacadeREST() {
        super(Alumno.class);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public Response createAlumno(Alumno entity) {
        super.create(entity);
        return Response.ok().build();
    }

    @PUT
    @Consumes({"application/xml", "application/json"})
    public Response editAlumno(Alumno entity) {
        super.edit(entity);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") String id) {
        super.remove(super.find(id));
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Alumno find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Alumno> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Alumno> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
