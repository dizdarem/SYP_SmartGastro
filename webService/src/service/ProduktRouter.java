package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import dal.Database;
import bll.ProduktTyp;

@Path("/produkte")
public class ProduktRouter {
	@Context
    private UriInfo context;

    public ProduktRouter() {
    }

    @GET
    @Path("/get")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProdukte() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getProdukte()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("Get Produkte called");
        return response.build();
    }
    
    @GET
    @Path("/get/{typ}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getProdukteByTyp(@PathParam("typ") String typ) {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
        	ProduktTyp.valueOf(typ);
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getProdukteByTyp(typ.toLowerCase())));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("Get Produkte by typ '" + typ + "' called");
        return response.build();
    }
}
