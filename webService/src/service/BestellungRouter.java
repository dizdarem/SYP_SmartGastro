package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import dal.Database;
import bll.Bestellung;

@Path("/Bestellung")
public class BestellungRouter {
	
	@Context
    private UriInfo context;

    public BestellungRouter() {
    }

    /*
    @GET
    @Path("/getCurrentId")
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
        System.out.println("get drinks Bestellung Produkt called");
        return response.build();
    }
    
    */
    
   @POST
   @Consumes({MediaType.APPLICATION_JSON})
   public Response addBestellung(String strBestellung) throws Exception {
       Response.ResponseBuilder response = Response.status(Response.Status.OK);
       

       try {
       	Bestellung best = new Gson().fromJson(strBestellung, Bestellung.class);
       	Database.newInstance().addBestellung(best);
           response.entity("bestellung added");
       } catch (Exception e) {
           response.status(Response.Status.BAD_REQUEST);
           response.entity("[ERROR] " + e.getMessage());
           System.out.println(e.getMessage());
       }
       System.out.println("POST bestellung called");
       return response.build();
   }
}
