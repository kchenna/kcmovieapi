package com.cks.api.movies;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UnPackServiceImpl implements UnPackService {

	@Override
	public String getMovieUrl(String unpackString) {
		
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://ck-unpack.herokuapp.com/post/data";
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
		HttpEntity<String> requestEntity = new HttpEntity<>(finalRes,headers);
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		String body = response.getBody();
		String result = body.substring(body.indexOf("sources")+8,body.lastIndexOf("]});")+1) ;
		return result;
	}

	
	
}
