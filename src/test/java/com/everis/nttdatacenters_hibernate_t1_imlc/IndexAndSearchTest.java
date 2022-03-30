package com.everis.nttdatacenters_hibernate_t1_imlc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.everis.nttdatacenters_hibernate_t1_imlc.Services;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;

import static org.junit.Assert.assertEquals;

/**
 * Example testcase for Hibernate Search
 */
public class IndexAndSearchTest {

	private EntityManagerFactory emf;

	private EntityManager em;

	private static Logger log = LoggerFactory.getLogger( IndexAndSearchTest.class );

	@Before
	public void setUp() {
		initHibernate();
	}

	@After
	public void tearDown() {
		purge();
	}

	@Test
	public void testIndexAndSearch() throws Exception {
		List<Services> services = search( "hibernate" );
		assertEquals( "Should get empty list since nothing is indexed yet", 0, services.size() );

		index();

		// search by title
		services = search( "hibernate" );
		assertEquals( "Should find one book", 1, services.size() );
		assertEquals( "Wrong title", "Java Persistence with Hibernate", services.get( 0 ).getTitle() );

		// search author
		services = search( "\"Gavin King\"" );
		assertEquals( "Should find one book", 1, services.size() );
		assertEquals( "Wrong title", "Java Persistence with Hibernate", services.get( 0 ).getTitle() );
	}

	@Test
	public void testStemming() throws Exception {

		index();

		List<Services> services = search( "refactor" );
		assertEquals( "Wrong title", "Refactoring: Improving the Design of Existing Code", services.get( 0 ).getTitle() );

		services = search( "refactors" );
		assertEquals( "Wrong title", "Refactoring: Improving the Design of Existing Code", services.get( 0 ).getTitle() );

		services = search( "refactored" );
		assertEquals( "Wrong title", "Refactoring: Improving the Design of Existing Code", services.get( 0 ).getTitle() );

		services = search( "refactoring" );
		assertEquals( "Wrong title", "Refactoring: Improving the Design of Existing Code", services.get( 0 ).getTitle() );
	}


	private void initHibernate() {
		emf = Persistence.createEntityManagerFactory( "hibernate-search-example" );
		em = emf.createEntityManager();
	}

	private void index() {
		FullTextEntityManager ftEm = org.hibernate.search.jpa.Search.getFullTextEntityManager( em );
		try {
			ftEm.createIndexer().startAndWait();
		}
		catch ( InterruptedException e ) {
			log.error( "Was interrupted during indexing", e );
		}
	}

	private void purge() {
		FullTextEntityManager ftEm = org.hibernate.search.jpa.Search.getFullTextEntityManager( em );
		ftEm.purgeAll( Services.class );
		ftEm.flushToIndexes();
		ftEm.close();
		emf.close();
	}

	private List<Services> search(String searchQuery) throws ParseException {
		Query query = searchQuery( searchQuery );

		List<Services> services = query.getResultList();

		for ( Services b : services ) {
			log.info( "Title: " + b.getTitle() );
		}
		return services;
	}

	private Query searchQuery(String searchQuery) throws ParseException {

		String[] bookFields = { "title", "subtitle", "authors.name", "publicationDate" };

		//lucene part
		Map<String, Float> boostPerField = new HashMap<String, Float>( 4 );
		boostPerField.put( bookFields[0], (float) 4 );
		boostPerField.put( bookFields[1], (float) 3 );
		boostPerField.put( bookFields[2], (float) 4 );
		boostPerField.put( bookFields[3], (float) .5 );

		FullTextEntityManager ftEm = org.hibernate.search.jpa.Search.getFullTextEntityManager( em );
		Analyzer customAnalyzer = ftEm.getSearchFactory().getAnalyzer( "customanalyzer" );
		QueryParser parser = new MultiFieldQueryParser(
				Version.LUCENE_34, bookFields,
				customAnalyzer, boostPerField
		);

		org.apache.lucene.search.Query luceneQuery;
		luceneQuery = parser.parse( searchQuery );

		final FullTextQuery query = ftEm.createFullTextQuery( luceneQuery, Services.class );

		return query;
	}
}
