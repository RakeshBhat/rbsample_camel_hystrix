package com.example.demo.route;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

	@Handler
	public void validateResponse(@Body Object body) throws Exception{
		if ( null == body){
			throw new Exception("Invalid Response");
		}else{
			InputStream i = (InputStream) body;
			
			if( i.available() < 1){
				throw new Exception("Invalid Response");
			}
		}
	}
	
	@Handler
	public void cacheData(Exchange e){
		String b = e.getIn().getBody(String.class);
		String uType = e.getIn().getHeader("UserType", String.class);
		
		map.put(uType, b);
	}
	
	@Handler
	public void getCachedData(Exchange e) throws MissingCacheDataException{
		String uType = e.getIn().getHeader("UserType", String.class);
		String b = null;
		
		if(map.containsKey(uType)){
			b = map.get(uType);
		}else{
			throw new MissingCacheDataException("Missing value in cache");
		}
		
		e.getIn().setBody(b);
	}
}
