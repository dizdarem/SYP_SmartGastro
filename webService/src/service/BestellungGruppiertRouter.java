package service;

import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Request;

import com.google.gson.Gson;

import dal.Database;
@Path("/BestellungGruppiert")
public class BestellungGruppiertRouter {
	@Context
    private UriInfo context;

    public BestellungGruppiertRouter() {
    }
    
    @GET
    @Path("/drinks")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGetraenke() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getBestellungGruppiertD()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("get drinks Bestellung Gruppiert called");
        return response.build();
    }
    @GET
    @Path("/meals")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getGerichte() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getBestellungGruppiertG()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("get meals Bestellung Gruppiert called");
        return response.header("Access-Control-Allow-Origin", "*").build();
    }
    
    @Path("/meals")
    @OPTIONS
    public Response provideOptionsMeals(){
        Response response = Response.ok("meals options")
                             .header("Access-Control-Allow-Origin", "*")
                             .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                             .header("Access-Control-Allow-Headers", "access-control-allow-headers,access-control-allow-origin,content-type,X-Requested-With")
                             .build();
        return response;
    }
    
    @GET
    @Path("/filteredMeals")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFilteredGerichte() {
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        try {
            Database db = Database.newInstance();
            response.entity(new Gson().toJson(db.getFilteredBestellungGruppiertG()));
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
        }
        System.out.println("get filtered meals Bestellung Gruppiert called");
        return response.header("Access-Control-Allow-Origin", "*").build();
    }
    
    @Path("/filteredMeals")
    @OPTIONS
    public Response provideOptionsFilteredMeals(){
        Response response = Response.ok("filtered meals options")
                             .header("Access-Control-Allow-Origin", "*")
                             .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                             .header("Access-Control-Allow-Headers", "access-control-allow-headers,access-control-allow-origin,content-type,X-Requested-With")
                             .build();
        return response;
    }
    
    @PUT
    @Path("/updateBrought")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateBrought(@Context Request request, String jsonString) throws Exception {
    	//System.out.println(new Gson().toJson(request));
    	Response.ResponseBuilder response = Response.status(Response.Status.OK);
        Database db = Database.newInstance();
        
        try {
        	Map map = new Gson().fromJson(jsonString, Map.class);
            db.updateBrought(((Double)map.get("idBestellung")).intValue(), ((Double)map.get("idPosten")).intValue(), (Boolean) map.get("brought"));
            
        } catch (Exception e) {
            response.status(Response.Status.BAD_REQUEST);
            response.entity("[ERROR] " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("PUT toggleBrought called");
        return response.header("Access-Control-Allow-Origin", "*").build();
    }
    
    @Path("/updateBrought")
    @OPTIONS
    public Response provideOptionsBrought(){
        Response response = Response.ok("updateBrought options")
                             .header("Access-Control-Allow-Origin", "*")
                             .header("Access-Control-Allow-Methods", "PUT, OPTIONS")
                             .header("Access-Control-Allow-Headers", "access-control-allow-headers,access-control-allow-origin,content-type,X-Requested-With")
                             .build();
        return response;
    }
}
