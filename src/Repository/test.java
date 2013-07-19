package Repository;

import org.openrdf.rio.RDFFormat;

import Utils.UriUtil;
import Const.Const;

public class test {
	/**
	 * @Author RenKan
	 */
	public static void main(String[] args) {
		Repo r = new Repo();

		String NS = "http://test.com/";
		String baseURI = "http://rk/";
		
		r.addRecord(Const.rdfPath, baseURI, RDFFormat.RDFXML);
		r.addRecord(NS+"subj", NS+"pred", NS+"obj");
		r.addRecord(Const.streamRDFPath);
		
		UriUtil u = new UriUtil();
		System.out.print(u.isUri("http://fat/"));
		
//		String query = "PREFIX test: <http://test.com/>" +
//				"SELECT ?s WHERE {?s test:age ?o.}";		
//		try {
//			repo.initialize();
//			RepositoryConnection conn = repo.getConnection();
//			ValueFactory vf = repo.getValueFactory();
//			URI subj = vf.createURI(NS + "person");
//			URI pred = vf.createURI(NS + "age");
//			Literal obj = vf.createLiteral(20);
//			conn.add(subj, pred, obj);
//			TupleQueryResult itr = conn.prepareTupleQuery(QueryLanguage.SPARQL, query).evaluate();
//			while (itr.hasNext()) {
//				BindingSet bf = itr.next();
//				System.out.println(bf.getValue("s"));
//			}
//			itr.close();
//			conn.close();
//		} catch (RepositoryException e) {
//			e.printStackTrace();
//		} catch (MalformedQueryException e) {
//			e.printStackTrace();
//		} catch (QueryEvaluationException e) {
//			e.printStackTrace();
//		}
	}
}
