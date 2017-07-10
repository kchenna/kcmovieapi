package com.cks.api.movies;


import com.jaunt.UserAgent;

public class JauntExample {

	public static void main(String[] args) {
	    try{
	        UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
	        System.out.println("SETTINGS:\n" + userAgent.settings);      //print the userAgent's default settings.
	        userAgent.settings.autoSaveAsHTML = true;                    //change settings to autosave last visited page. 
	        
	        
	        
	        //userAgent.visit("http://tamilgun.ooo/video/anbanavan-asaradhavan-adangadhavan-hd/");
	        
	        userAgent.visit("http://tamilgun.ooo/wp-content/plugins/tamil/lib/play.php?data[]=OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm9_4A2pYj&data[]=QNG6Slddeq9SL68-SuVDFSsufDnz3NyppinWzGlZqxkTwzEtSx&data[]=ks8yXe_WyBQBlQJEIHX-zwTTju4JJUg8vxDDjXTvOhyPTHhCIN&data[]=mWpx58P4eHK86zJn3ddxuHNEBgCxXvgqD4KlhnjFvEPrB-OsFi&data[]=ikTS3uFbYTSnFQ_O1KJx8ocDMyplPLrCDCZgO4yIqLau9t_AU6&data[]=QhpmKe&stream=1");                        //visit a url.

	        System.out.println(userAgent.getLocation());

	        userAgent.visit("http://tamilgun.ooo/wp-content/plugins/tamil/lib/play.php?data[]=OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm9_4A2pYj&data[]=QNG6Slddeq9SL68-SuVDFSsufDnz3NyppinWzGlZqxkTwzEtSx&data[]=ks8yXe_WyBQBlQJEIHX-zwTTju4JJUg8vxDDjXTvOhyPTHhCIN&data[]=mWpx58P4eHK86zJn3ddxuHNEBgCxXvgqD4KlhnjFvEPrB-OsFi&data[]=ikTS3uFbYTSnFQ_O1KJx8ocDMyplPLrCDCZgO4yIqLau9t_AU6&data[]=QhpmKe&stream=1");

	        System.out.println(userAgent.getLocation());
	        
	        // Element div = userAgent.doc.findFirst("<video>"); //find first div who's class matches 'images' 
	        
	         //System.out.println(div);
	         
	    	  
	        /* WebClient webClient = new WebClient();
	         webClient.getOptions().setThrowExceptionOnScriptError(false);
	         webClient.getOptions().setJavaScriptEnabled(false);
	         webClient.getPage("http://tamilgun.ooo/video/anbanavan-asaradhavan-adangadhavan-hd/");
*/
	      }
	      catch(Exception e){   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
	        System.err.println(e);          
	      }
	}
}
