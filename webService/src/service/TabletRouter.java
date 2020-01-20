package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;

import bll.Bestellung;
import dal.Database;

@Path("/Tablet")
public class TabletRouter {
	@Context
	private UriInfo context;

	public TabletRouter() {
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response addTablet(String strTablet) throws Exception {
		Response.ResponseBuilder response = Response.status(Response.Status.OK);

		try {
			Bestellung tablet = new Gson().fromJson(strTablet, Bestellung.class);
			Database.newInstance().addTablet(tablet);
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
