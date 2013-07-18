package Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UriUtil {
	private StringBuilder uriBuilder;
	
	public UriUtil() {
		uriBuilder = new StringBuilder();
	}
	
	public UriUtil(String namespace) {
		uriBuilder = new StringBuilder(namespace);
	}
	
	public void typeAppender(String type) {
		uriBuilder.append(type);
	}
	
	public String getUri() {
		return uriBuilder.toString();
	}
	
	public String getUri(String iden) {
		String url = null;
		try {
			url = URLEncoder.encode(iden,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder rtn = uriBuilder;
		return rtn.append(url).toString();
	}
	
}
