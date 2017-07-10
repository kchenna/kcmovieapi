package com.cks.api.movies;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//import com.jaunt.UserAgent;

public class JauntExample {

	public static void main(String[] args) {
	    try{
	    	
			RestTemplate restTemplate = new RestTemplate();
			String uri = "http://localhost:3000/post/data";
			HttpHeaders headers = new HttpHeaders();
			String a ="eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('4.B=\'A+z=\';4(\'1\').x({y:\'C\',D:{},H:\'G%\',F:\'E\',w:{u:\'p\'},o:[{\"d\":\"c\\/e\",\"f\":\"n\",\"h\":\"b:\\/\\/i.9\\/j-5\\/6\\/a\\/7\\/8.g?0[]=m&0[]=q-r&0[]=t-v&0[]=s&0[]=I-16&0[]=J\"},{\"d\":\"c\\/e\",\"f\":\"W\",\"h\":\"b:\\/\\/i.9\\/j-5\\/6\\/a\\/7\\/8.g?0[]=K-O&0[]=P-T-S&0[]=R-Q&0[]=Z&0[]=L-M&0[]=N\",\"U\":V}]});4(\'1\').12(\'14\',15(){11(l(\'1\').10(\'2-3-k\')){l(\'1\').X(\'2-3-k\').Y(\'2-3-13\')}});',62,69,'data|vz1m6ba5heq|jw|state|jwplayer|content|plugins|lib|play|ooo|tamil|http|video|type|mp4|label|php|file|tamilgun|wp|idle|jQuery|OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm9_4A2pYj|360p|sources|vapor|QNG6Slddeq9SL68|SuVDFSsufDnz3NyppinWzGlZqxkTwzEtSx|mWpx58P4eHK86zJn3ddxu5r8mxujOR_HH_bdunB6CErsi2gtsp|ks8yXe_WyBQBlQJEIHX|name|zwTTju4JJUg8vxDDjXTvOhyPTHhCIN|skin|setup|primary|yV2UHsZYTJKhuG6K3yLVFjLjyEAU|QaRGCJxkkwbZg9|key|html5|cast|550px|height|100|width|ckAIY1QPa9i9zM|EID9BD|OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm|9nrsB1B9mMj1OG0ccVRT7|sWrNml7Jnj57xpJ7FKPgYF7ymEvy|XL7Em4|mxPGFrZ|zTTb1mN46FAMervYvA|FC1427UClVnJl1EhNI2nWnzFJUg8vxDDjXTvOhyPTHhCIN|MMe|Lu3GD8MqLvg8q15eooobxB7Pv1Dk|8q|default|true|720p|removeClass|addClass|mWpx58P4eHK86zJn3ddxvaCJcj3yPYogMR6hiwAfdzVDG3UiAR|hasClass|if|on|buffering|displayClick|function|97bp8gRCCNpswd167kNmVLr2rmdMtNA_aoI'.split('|'),0,{}))";
			String tillP = a.substring(0,a.indexOf("return p}")) +"return p}";
			tillP = StringEscapeUtils.escapeJava(tillP);
			String afterP = a.substring(a.indexOf("return p}")+11,a.lastIndexOf(",'"));
			afterP = afterP.replace("'", "\\'");
			afterP = afterP.replace("\\/","\\\\/");
			afterP = afterP.replace(");\\'", ");'");
	    	String lastPart = a.substring(a.lastIndexOf(",'"));
	    	String finalRes = tillP+"('"+afterP+""+lastPart;
			HttpEntity requestEntity = new HttpEntity<>(finalRes,headers);
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
			String body = response.getBody();
			System.out.println(body.substring(body.indexOf("sources")+8,body.lastIndexOf("]});")+1) );
		
			
	//        UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
	        //System.out.println("SETTINGS:\n" + userAgent.settings);      //print the userAgent's default settings.
	       // userAgent.settings.autoSaveAsHTML = true;                    //change settings to autosave last visited page. 
	        
	        
	        
	        //userAgent.visit("http://tamilgun.ooo/video/anbanavan-asaradhavan-adangadhavan-hd/");
	        
	       // userAgent.visit("http://tamilgun.ooo/wp-content/plugins/tamil/lib/play.php?data[]=OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm9_4A2pYj&data[]=QNG6Slddeq9SL68-SuVDFSsufDnz3NyppinWzGlZqxkTwzEtSx&data[]=ks8yXe_WyBQBlQJEIHX-zwTTju4JJUg8vxDDjXTvOhyPTHhCIN&data[]=mWpx58P4eHK86zJn3ddxuHNEBgCxXvgqD4KlhnjFvEPrB-OsFi&data[]=ikTS3uFbYTSnFQ_O1KJx8ocDMyplPLrCDCZgO4yIqLau9t_AU6&data[]=QhpmKe&stream=1");                        //visit a url.

	       // System.out.println(userAgent.getLocation());

	       // userAgent.visit("http://tamilgun.ooo/wp-content/plugins/tamil/lib/play.php?data[]=OVYnvgtwyECYbM4JYAk0aWZnXBPjfLoD24GHXhScHm9_4A2pYj&data[]=QNG6Slddeq9SL68-SuVDFSsufDnz3NyppinWzGlZqxkTwzEtSx&data[]=ks8yXe_WyBQBlQJEIHX-zwTTju4JJUg8vxDDjXTvOhyPTHhCIN&data[]=mWpx58P4eHK86zJn3ddxuHNEBgCxXvgqD4KlhnjFvEPrB-OsFi&data[]=ikTS3uFbYTSnFQ_O1KJx8ocDMyplPLrCDCZgO4yIqLau9t_AU6&data[]=QhpmKe&stream=1");

	       // System.out.println(userAgent.getLocation());
	        
	        // Element div = userAgent.doc.findFirst("<video>"); //find first div who's class matches 'images' 
	        
	         //System.out.println(div);
	         
	    	  
	        /* WebClient webClient = new WebClient();
	         webClient.getOptions().setThrowExceptionOnScriptError(false);
	         webClient.getOptions().setJavaScriptEnabled(false);
	         webClient.getPage("http://tamilgun.ooo/video/anbanavan-asaradhavan-adangadhavan-hd/");
*/
	      }
	      catch(Exception e){   //if title element isn't found or HTTP/connection error occurs, handle JauntException.
	        e.printStackTrace();
	      }
	}
}
