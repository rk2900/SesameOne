package Utils;

import java.util.HashMap;

import org.openrdf.model.URI;
import org.openrdf.model.vocabulary.RDFS;

public class PredicateUtil {
	private HashMap<String,URI> defaultUri;
	private UriUtil uriUtil;
	
	public PredicateUtil() {
		defaultUri = new HashMap<String,URI>();
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
	}
	
	public URI getPredUri(String predStr) {
		if(predStr.matches("^(rdf:)")) {
			predStr = predStr.substring(4);
			return defaultUri.get(predStr);
		} else {
			return uriUtil.getUri(predStr);
		}
	}

}
