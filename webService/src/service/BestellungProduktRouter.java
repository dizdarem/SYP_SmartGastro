package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import dal.Database;

@Path("/BestellungProdukt")
public class BestellungProduktRouter {
	@Context
    private UriInfo context;

    public BestellungProduktRouter() {
    }

    // Return the list of books for applications & browser
    @GET
    @Path("/drinks")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGetraenke() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getBestellungProduktD()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("======================webservice GET called");
        return response.build();
    }
    @GET
    @Path("/meals")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGerichte() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getBestellungProduktG()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("======================webservice GET called");
        return response.build();
    }
}
