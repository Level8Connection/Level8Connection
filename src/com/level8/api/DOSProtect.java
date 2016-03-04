package com.level8.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import com.arctro.CacheMap;
import com.level8.api.supporting.ResponseError;

/**
 * Designed to stop denial of service attacks. It stores a list of visitors in the
 * last millisecond, and any that visit more than once a millisecond are blocked.
 * @author Ben McLean
 * @version 1.0
 */
public class DOSProtect {
	/**
	 * The cache of ip addresses
	 */
	public static CacheMap<String, String> ip = new CacheMap<String, String>();
	
	/**
	 * Check if ip should be allowed
	 * @param ipAddress ip to check
	 * @return If ip is allowed
	 */
	public static boolean check(String ipAddress){
		//DISABLED
		if(true){return true;}
		
		if(ip.expired(ipAddress)){
			ip.put(ipAddress, "", 1);
			return true;
		}
		return false;
	}
	
	/**
	 * Check if ip should be allowed
	 * @param request Request to extract ip from
	 * @return If ip is allowed
	 */
	public static boolean check(HttpServletRequest request){
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		if (ipAddress == null) {  
			ipAddress = request.getRemoteAddr();  
		}
		return check(ipAddress);   
	}
	
	public static Response generate(){
		return Response.status(403).entity(ResponseError.generate(ResponseError.Error.TOO_MANY_REQUESTS).toString()).build();
	}
}
