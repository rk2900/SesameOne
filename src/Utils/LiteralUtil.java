package Utils;

import org.openrdf.model.Literal;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;

public class LiteralUtil {
	private StringBuilder litBuilder;
	private ValueFactory valueFactory;
	
	public LiteralUtil() {
		litBuilder = new StringBuilder();
	}
	
	public LiteralUtil(String namespace) {
		litBuilder = new StringBuilder(namespace);
	}
	
	public LiteralUtil(LiteralUtil u) {
		//TODO
		litBuilder = new StringBuilder();
	}
	
	public void setValueFactory(ValueFactory vf) {
		this.valueFactory = vf; 
	}
	
	public void setValueFactory(RepositoryConnection repoConn) {
		this.valueFactory = repoConn.getValueFactory();
	}
	
	public Literal getLiteral(String str) {
		return valueFactory.createLiteral(str);
	}
	
	public Literal getLiteral(String str, boolean langEnable) {
		if(langEnable) {
			String[] strSeq = str.split("@");
			return valueFactory.createLiteral(strSeq[0],strSeq[1]);
		} else {
			return valueFactory.createLiteral(str);
		}
	}

}
