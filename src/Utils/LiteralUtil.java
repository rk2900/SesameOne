package Utils;

import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class LiteralUtil {
//	public static enum litType{PURE, LANG, URI};
//	private StringBuilder litBuilder;
	private ValueFactory valueFactory;
	
	public LiteralUtil() {
//		litBuilder = new StringBuilder();
		valueFactory = new ValueFactoryImpl();
	}
	
	public LiteralUtil(String namespace) {
//		litBuilder = new StringBuilder(namespace);
		valueFactory = new ValueFactoryImpl();
	}
	
	public LiteralUtil(LiteralUtil u) {
		//TODO
//		litBuilder = new StringBuilder();
		valueFactory = new ValueFactoryImpl();
	}
	
	public void setValueFactory(ValueFactory vf) {
		this.valueFactory = vf; 
	}
	
	public Literal getLiteral(String str) {
		return valueFactory.createLiteral(str);
	}
	
	public Literal getLiteral(String str, boolean langEnable) {
//		switch(type) {
//		case litType.PURE:
//			return valueFactory.createLiteral(str);
//		case litType.LANG:
//			String[] strSeq = str.split("@");
//			return valueFactory.createLiteral(strSeq[0],strSeq[1]);
//		case litType.URI:
//			
//		}
		if(langEnable) {
			String[] strSeq = str.split("@");
			return valueFactory.createLiteral(strSeq[0],strSeq[1]);
		} else {
			return valueFactory.createLiteral(str);
		}
	}
	
	public URI getUriObject(String objStr) {
		return valueFactory.createURI(objStr);
	}

}
