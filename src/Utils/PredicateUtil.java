package Utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDFS;

public class PredicateUtil {
	private HashMap<String,URI> defaultUri;
	private UriUtil uriUtil;
	private ArrayList<String> propertyPred;
	
	public PredicateUtil() {
		defaultUri = new HashMap<String,URI>();
		propertyPred = new ArrayList<String>();
		uriUtil = new UriUtil();
		initialize();
	}
	
	public void setNameSpace(String ns) {
		uriUtil.setNameSpace(ns);
	}
	
	public void setType(String type) {
		uriUtil.setType(type);
	}
	
	/*
	 * Read the default RDF file
	 * and create default RDF set.
	 */
	private void initialize() {
		//TODO
		defaultUri.put("LEBAL", RDFS.LABEL);
		defaultUri.put("DATATYPE", RDFS.DATATYPE);
		
		propertyPred.add("NAME");
		propertyPred.add("AGE");
	}
	
	/*
	 * To insert one default URI record.
	 */
	public void insertDefaultUri(String key, URI uri) {
		String uriKey = new String(key.toUpperCase());
		URI defUri = uriUtil.getUri(uri);
		defaultUri.put(uriKey, defUri);
	}
	
	public URI getPredUri(String predStr) {
		if(predStr.matches("^(rdf:)")) {
			predStr = predStr.substring(4);
			return defaultUri.get(predStr);
		} else {
			return uriUtil.getUri(predStr);
		}
	}
	
	public boolean isObjUri(String predStr) {
		return propertyPred.contains(predStr);
	}

}
