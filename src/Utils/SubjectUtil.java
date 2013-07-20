package Utils;

import org.openrdf.model.URI;

public class SubjectUtil {
	private UriUtil uriUtil;
	
	public SubjectUtil() {
		uriUtil = new UriUtil();
	}
	
	public void setNameSpace(String ns) {
		uriUtil.setNameSpace(ns);
	}
	
	public void setType(String type) {
		uriUtil.setType(type);
	}
	
	public URI getSubjUri(String subjStr) {
		return uriUtil.getUri(subjStr);
	}
}
