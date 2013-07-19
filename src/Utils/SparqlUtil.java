package Utils;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;

import Repository.Repo;

public class SparqlUtil {
	private RepositoryConnection repoConn;
	private StringBuilder query;
	
	public SparqlUtil(Repo repo) {
		repoConn = repo.getConnection();
		query = new StringBuilder();
	}
	
	public TupleQueryResult query(String[] prefix, String select, 
									String where, String orderBy, String limit) 
									throws QueryEvaluationException, RepositoryException, 
									MalformedQueryException {
		//PREFIX
		if(prefix != null) {
			query.append("PREFIX ");
			for(int i=0; i<prefix.length/2; i++) {
				query.append(prefix[2*i]);
				query.append(":");
				query.append("<");
				query.append(prefix[2*i+1]);
				query.append(">");
			}
		}
		//SELECT
		query.append("SELECT ");
		query.append(select);
		
		//WHERE
		if(where != null) {
			query.append("WHERE { ");
			query.append(where);
			query.append(" }");
		}
		
		//ORDER BY
		if(orderBy != null) {
			query.append("ORDER BY ");
			query.append(orderBy);
		}
		
		if(limit != null) {
			query.append("LIMIT ");
			query.append(limit);
		}
		
		return repoConn.prepareTupleQuery(QueryLanguage.SPARQL, query.toString()).evaluate();
		
	}
	
//	public String query() {
//		
//	}
}
