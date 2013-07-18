package Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.openrdf.model.Literal;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.LinkedHashModel;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFWriter;
import org.openrdf.rio.Rio;
import org.openrdf.sail.nativerdf.NativeStore;

import Const.Const;

public class Repo {
	private Repository repo;
//	private MemoryStore memStore;
	private NativeStore natStore;
	private File repoFile;
	private RepositoryConnection repoConn;
	
	public Repo() {
		repoFile = new File(Const.repoPath);
		natStore = new NativeStore(repoFile);
		repo = new SailRepository(natStore);
		repoInitialize();
	}
	
	private void repoInitialize() {
		try {
			repo.initialize();
		} catch(RepositoryException e) {
			
		}
	}

	/*
	 * The URI-URI-URI format SPO record.
	 */
	public void addRecord(URI subj, URI pred, Literal obj) {
		try {
			repoConn = repo.getConnection();
			repoConn.add(subj, pred, obj);
			repoConn.close();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * The Str-Str-Str format SPO record.
	 */
	public void addRecord(String subjStr, String predStr, String objStr) {
		ValueFactory vf = repo.getValueFactory();
		URI subj = vf.createURI(subjStr);
		URI pred = vf.createURI(predStr);
		Literal obj = vf.createLiteral(objStr);
		addRecord(subj,pred,obj);
	}
	
	/*
	 * The RDF reader for insert records.
	 */
	public void addRecord(String rdfPath, String baseURI, RDFFormat format) {
		File rdfFile = new File(rdfPath);
		try {
			repoConn = repo.getConnection();
			repoConn.add(rdfFile, baseURI, format);
			repoConn.close();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (RDFParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Read the RDF file with SPO format in stream.
	 */
	public void addRecord(String spoFilePath) {
//		File spoFile = new File(spoFilePath);
		InputStream in = null;
		try {
			in = new FileInputStream(spoFilePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader reader = null;
		String line = null;
		String[] segments = null;
		String subjStr, predStr, objStr;
		URI subj, pred;
		Literal lit = null;
		Statement stat = null;
//		ArrayList<Statement> model = new ArrayList<Statement>();
		LinkedHashModel model = new LinkedHashModel();
		
		System.out.println("Start reading file in line!");
		reader = new BufferedReader(new InputStreamReader(in));
		
		try {
			while( (line = reader.readLine()) != null) {
				if(line.length() <= 0) {
					continue;
				}
				segments = line.split(" ");
				subjStr = segments[0];
				predStr = segments[1];
				objStr = segments[2];
				ValueFactory vf = repo.getValueFactory();
				subj = vf.createURI(subjStr);
				pred = vf.createURI(predStr);
				lit = vf.createLiteral(objStr);
				stat = vf.createStatement(subj, pred, lit);
				model.add(stat);
			}
			reader.close();
			in.close();
			repoConn = repo.getConnection();
			repoConn.add(model);
			saveRDFTurtle(model, ".//test.n3", RDFFormat.N3);
			repoConn.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Save triples into RDF files 
	 * with the specific RDF format.
	 */
	public void saveRDFTurtle(LinkedHashModel model, String filePath, RDFFormat rdfFormat) {
		try {
			FileOutputStream out = new FileOutputStream(filePath);
			RDFWriter writer = null;
			writer = Rio.createWriter(rdfFormat, out);
			writer.startRDF();
			for(Statement stat: model) {
				writer.handleStatement(stat);
			}
			writer.endRDF();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (RDFHandlerException e) {
			e.printStackTrace();
		}
		
	}
	
}
