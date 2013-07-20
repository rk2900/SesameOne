package Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class UriUtil {
	private StringBuilder uriBuilder;
	private ValueFactory valueFactory;
	
	public UriUtil() {
		uriBuilder = new StringBuilder();
		valueFactory = new ValueFactoryImpl();
	}
	
	public UriUtil(String namespace) {
		uriBuilder = new StringBuilder(namespace);
		valueFactory = new ValueFactoryImpl();
	}
	
	public UriUtil(UriUtil u) {
		uriBuilder = new StringBuilder(u.getNsAndType());
		valueFactory = new ValueFactoryImpl();
	}
	
	public void setValueFactory(ValueFactory vf) {
		this.valueFactory = vf;
	}
	
	public void setNameSpace(String ns) {
		this.uriBuilder = new StringBuilder(ns);
	}
	
	public void setType(String type) {
		uriBuilder.append(type+'/');
	}
	
	/*
	 * To get the namespace and type value
	 * ex:
	 * namespace: http://test.org/
	 * type: person{null}
	 * result: http://test.org/person
	 */
	public String getNsAndType() {
		return uriBuilder.toString();
	}
	
	public URI getUri() {
		URI rtn = null;
		rtn = valueFactory.createURI(uriBuilder.toString());
		return rtn;
	}
	
	public URI getUri(URI uri) {
		return valueFactory.createURI(uri.toString());
	}
	
	/*
	 * To get the URI of the specific string value
	 * 1. if it is already a URI, then return;
	 * 2. else translate it to URI format and return.
	 */
	public URI getUri(String iden) {
		URI rtn = null;
		String url = null;
		
		if(isUri(iden)) {
			return valueFactory.createURI(iden);
		} else {
			try {
				url = URLEncoder.encode(iden,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			rtn = valueFactory.createURI(uriBuilder.append(url).toString());
			return rtn;
		}
	}
	
	/*
	 * To justify if the input string is 
	 * in the format of URI.
	 */
	public boolean isUri(String obj) {
		return obj.matches("(([a-zA-Z][0-9a-zA-Z+\\\\-\\\\.]*:)?/{0,2}[0-9a-zA-Z;/?:@&=+$\\\\.\\\\-_!~*'()%]+)?(#[0-9a-zA-Z;/?:@&=+$\\\\.\\\\-_!~*'()%]+)?");
	}
	
}
