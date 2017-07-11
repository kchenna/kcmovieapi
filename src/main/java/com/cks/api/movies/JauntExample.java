package com.cks.api.movies;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.Element;
import com.jaunt.UserAgent;

//import com.jaunt.UserAgent;

public class JauntExample {
	
	public void getMovieUrl(String url){
		 try{
		    	
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.set( "User-Agent", "Mozilla/5.0");
				headers.set("Accept","text/html,*/*");
				HttpEntity<String> requestEntity = new HttpEntity<>(headers);
				ResponseEntity response = restTemplate.exchange(url+"&stream=1", HttpMethod.GET, requestEntity, String.class);
			System.out.println(response.getHeaders().getDate());
			
			response = restTemplate.exchange(url+"&stream=1", HttpMethod.GET, requestEntity, String.class);
			System.out.println(response.getHeaders().getLocation());
			
			/*UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
	        System.out.println("SETTINGS:\n" + userAgent.settings);      //print the userAgent's default settings.
	        
  	       userAgent.visit(url);                        //visit a url.
	       System.out.println(userAgent.getLocation());
	       userAgent.visit(url);
	       System.out.println(userAgent.getLocation());
	        Element div = userAgent.doc.findFirst("<video>"); //find first div who's class matches 'images' 
	         System.out.println(div);
*/
		      }
		      catch(Exception e){   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
		        e.printStackTrace();
		      }

	}

	public List<HashMap<String,String>> getLinkMap(String body){
		 try{
		    	
				ObjectMapper mapper = new ObjectMapper();
				String res = body.substring(body.indexOf("sources")+8,body.lastIndexOf("]});")+1) ;
				return mapper.readValue(res, new TypeReference<List<HashMap<String,String>>>(){});
			
		      }
		      catch(Exception e){   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
		        e.printStackTrace();
		      }
		 return null;

	}

	
	public List<HashMap<String,String>> test(String a){
		 try{
		    	
				RestTemplate restTemplate = new RestTemplate();
				String uri = "https://ck-unpack.herokuapp.com/post/data";
				HttpHeaders headers = new HttpHeaders();
				HttpEntity<String> requestEntity = new HttpEntity<>(a,headers);
				ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
				String body = response.getBody();
				ObjectMapper mapper = new ObjectMapper();
				String res = body.substring(body.indexOf("sources")+8,body.lastIndexOf("]});")+1) ;
				return mapper.readValue(res, new TypeReference<List<HashMap<String,String>>>(){});
			
		      }
		      catch(Exception e){   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
		        e.printStackTrace();
		      }
		 return null;

	}

	
}
