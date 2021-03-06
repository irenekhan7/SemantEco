package edu.rpi.tw.escience.semanteco.test;

import java.io.File;
import java.net.URI;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;

import edu.rpi.tw.escience.semanteco.Domain;
import edu.rpi.tw.escience.semanteco.ModuleConfiguration;
import edu.rpi.tw.escience.semanteco.QueryExecutor;
import edu.rpi.tw.escience.semanteco.QueryFactory;
import edu.rpi.tw.escience.semanteco.Request;
import edu.rpi.tw.escience.semanteco.Resource;

/**
 * Provides a TestModuleConfiguration that provides basic functionality
 * of a ModuleConfiguration that can be override for unit testing.
 * @author ewpatton
 *
 */
public class TestModuleConfiguration extends ModuleConfiguration {

	private static final long serialVersionUID = 1L;
	
	/**
	 * A query executor that can be overwritten by unit tests
	 */
	public TestQueryExecutor executor = new TestQueryExecutor();
	/**
	 * A query factory that can be overwritten by unit tests
	 */
	public TestQueryFactory factory = new TestQueryFactory();

	@Override
	public String getSparqlEndpoint() {
		return executor.getDefaultSparqlEndpoint();
	}

	@Override
	public QueryFactory getQueryFactory() {
		return factory;
	}

	@Override
	public QueryExecutor getQueryExecutor(final Request request) {
		return executor;
	}

	@Override
	public Resource getResource(String path) {
		File file = new File(System.getProperty("user.dir")+"/target/classes/META-INF/res/"+path);
		Assert.assertTrue(file.exists());
		return new TestResource();
	}

	@Override
	public Resource generateStringResource(String content) {
		return new TestStringResource();
	}

	@Override
	public Logger getLogger() {
		return Logger.getRootLogger();
	}

	@Override
	public Domain getDomain(URI uri, boolean create) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Domain> listDomains() {
		// TODO Auto-generated method stub
		return null;
	}

}
