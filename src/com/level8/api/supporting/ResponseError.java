package com.level8.api.supporting;

import org.json.JSONObject;

public class ResponseError {
	public enum Error{
		INTERNAL_ERROR("There was an internal error"),
		TOO_MANY_REQUESTS("This ip address has made too many requests"),
		INVALID_RESOURCE("Resource may not exist or is invalid"),
		INVALID_PARAMETER("A parameter was invalid"),
		INVALID_KEY("The key presented was invalid"),
		NOT_PERMITTED("Not permitted");
		
		public String desc;
		private Error(String desc){
			this.desc = desc;
		}
	}
	
	public static JSONObject generate(Error e){
		return generate(e, e.desc);
	}
	
	public static JSONObject generate(Error e, String message){
		JSONObject o = new JSONObject();
		o.put("success", false);
		o.put("error", e.toString());
		o.put("message", message);
		
		return o;
	}
}
