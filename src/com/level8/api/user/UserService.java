package com.level8.api.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.level8.api.DOSProtect;
import com.level8.api.supporting.ResponseError;
import com.level8.supporting.DB;

@Path("/user")
public class UserService {
	
	@GET
	@Path("/{username}")
	@Produces("application/json")
	public Response get(@Context HttpServletRequest request, @PathParam("username") String username){
		if(!DOSProtect.check(request)){
			return DOSProtect.generate();
		}
		
		long start = System.currentTimeMillis();
		
		JSONObject response = new JSONObject();
		
		try {
			PreparedStatement prepared = DB.db.getDatabase().prepareStatement("SELECT `email`, `username` FROM `users` WHERE `username` = ?"); // I dont know the structure
			prepared.setString(1, username);
			
			ResultSet rs = prepared.executeQuery();
			
			if(!rs.next()){
				return Response.status(403).entity(ResponseError.generate(ResponseError.Error.INVALID_RESOURCE).toString()).build();
			}
			
			response.put("email", rs.getString("email"));
			response.put("username", rs.getString("username"));
		} catch (SQLException e) {
			return Response.status(403).entity(ResponseError.generate(ResponseError.Error.INTERNAL_ERROR, e.getMessage()).toString()).build();
		} 
		
		response.put("time", System.currentTimeMillis() - start);
		return Response.status(200).entity(response.toString()).build();
	}
}
