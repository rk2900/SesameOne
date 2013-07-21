package Repository;

import java.util.ArrayList;

import org.openrdf.model.URI;
import org.openrdf.rio.RDFFormat;

import Utils.ObjectUtil;
import Utils.PredicateUtil;
import Utils.RepoUtil;
import Utils.UriUtil;
import Const.Const;

public class test {
//	private URI uri;
	/**
	 * @Author RenKan
	 */
	public static void main(String[] args) {
		RepoUtil r = new RepoUtil();
		ArrayList<URI> uriList = new ArrayList<URI>();
		UriUtil uriUtil = new UriUtil();
		PredicateUtil predUtil = new PredicateUtil();
		ObjectUtil litUtil = new ObjectUtil();
//		URI uri1 = u.getUri("http://rk/test");
//		System.out.println(uri1);
		uriList.add(uriUtil.getUri("http://test.com/relation/friend"));
		uriList.add(uriUtil.getUri("http://test.com/relation/post"));

		String NS = "http://test.com/";
		String baseURI = "http://rk/";
		
		r.addRecords(Const.rdfPath, baseURI, RDFFormat.RDFXML);
		r.addRecord(NS+"subj", NS+"pred", NS+"obj");
		r.addRecords(Const.streamRDFPath);
		
		if(uriList.contains(uriUtil.getUri("http://test.com/relation/firend"))) {
			
		}
		
		System.out.print(uriUtil.isUri("http://fat/"));
		
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
