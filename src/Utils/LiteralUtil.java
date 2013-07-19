package Utils;

import org.openrdf.model.Literal;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

public class LiteralUtil {
	private StringBuilder litBuilder;
	private ValueFactory valueFactory;
	
	public LiteralUtil() {
		litBuilder = new StringBuilder();
		valueFactory = new ValueFactoryImpl();
	}
	
	public LiteralUtil(String namespace) {
		litBuilder = new StringBuilder(namespace);
		valueFactory = new ValueFactoryImpl();
	}
	
	public LiteralUtil(LiteralUtil u) {
		//TODO
		litBuilder = new StringBuilder();
		valueFactory = new ValueFactoryImpl();
	}
	
	public void setValueFactory(ValueFactory vf) {
		this.valueFactory = vf; 
	}
	
	public Literal getLiteral(String str) {
		return valueFactory.createLiteral(str);
	}
	
	/*
	 * To set the Literal with language label.
	 * if languageEnable is true,
	 * it will split the object value with symbol '@'
	 * and return the literal with language label.
	 * else it will return raw literal.
	 */
	public Literal getLiteral(String str, boolean langEnable) {
		if(langEnable) {
			String[] strSeq = str.split("@");
			return valueFactory.createLiteral(strSeq[0],strSeq[1]);
		} else {
			return valueFactory.createLiteral(str);
		}
	}

}
