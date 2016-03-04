package com.level8.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

/**
 * Allows the client to detect if the server is running
 * @author Ben McLean
 * @version 1.0
 */
@Path("/ping")
public class PingService {
	
	/**
	 * Produces the response "pong"
	 * @return "pong", time taken
	 */
	@GET
	@Produces("application/json")
	public Response ping(){
		long start = System.currentTimeMillis();
		
		JSONObject response = new JSONObject();
		response.put("ping", "pong");
		
		response.put("time", System.currentTimeMillis() - start);
		return Response.status(200).entity(response.toString()).build();
	}
	
	/**
	 * Produces r
	 * @param r The response string
	 * @return r, time taken
	 */
	@GET
	@Path("/{r}")
	@Produces("application/json")
	public Response ping(@PathParam("r") String r){
		long start = System.currentTimeMillis();
		
		JSONObject response = new JSONObject();
		response.put("ping", r);
		
		response.put("time", System.currentTimeMillis() - start);
		return Response.status(200).entity(response.toString()).build();
	}
}
