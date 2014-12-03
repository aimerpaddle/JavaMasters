package com.application.javamasters.business;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.vaadin.data.Container;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

public class DatabaseManager {
	
	

	public SimpleJDBCConnectionPool connectionPool = null;
	public Connection conn = null;
    private SQLContainer challengeContainer = null;
    private SQLContainer helpfulLinksContainer = null;
    private SQLContainer overviewContainer = null;
    private SQLContainer subTopicContainer = null;

	public DatabaseManager() throws SQLException {
		
		//Connection conn = initConnection();
		//initConnectionPool();
		//buildChallengeContainer();
		//initContainers();
	}
	
	public Connection initConnection() {
		
		try {
			conn = connectionPool.reserveConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	
	}

	/*
	 * Creates a new connection to the server, then tries to connect  
	 * to the javamaster database.
	 */
	public SimpleJDBCConnectionPool initConnectionPool() {
		try {
			//Class.forName("com.mysql.jdbcDriver");
			connectionPool = new SimpleJDBCConnectionPool(
					"com.mysql.jdbc.Driver",
					"jdbc:mysql://localhost:3306/javamaster", "root",
					"lak3rs24", 2, 50);
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connectionPool;
	}
	
	public void closeConnectionPool() {
	
		
	}
	
	public SQLContainer initContainers() {
		
		try {
			TableQuery q1 = new TableQuery("challenge", connectionPool);
			q1.setVersionColumn("VERSION");
			this.challengeContainer = new SQLContainer(q1);
			
			
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		return challengeContainer;
	}
//	private void initContainers() {
//        try {
//            /* TableQuery and SQLContainer for challenge table */
//            TableQuery q1 = new TableQuery("challenge", connectionPool);
//            q1.setVersionColumn("VERSION");
//            this.challengeContainer = new SQLContainer(q1);
//
//            /* TableQuery and SQLContainer for subtopic table */
//            TableQuery q2 = new TableQuery("subtopic", connectionPool);
//            q2.setVersionColumn("VERSION");
//            this.subTopicContainer = new SQLContainer(q2);
//
//            /* TableQuery and SQLContainer for helpful_links table */
//            TableQuery q3 = new TableQuery("helpful_links", connectionPool);
//            q3.setVersionColumn("VERSION");
//            this.helpfulLinksContainer = new SQLContainer(q3);
//
//            /* TableQuery and SQLContainer for overview table */
//            TableQuery q4 = new TableQuery("overview", connectionPool);
//            q4.setVersionColumn("VERSION");
//            this.overviewContainer = new SQLContainer(q4);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//	}
	
		private void buildChallengeContainer() throws SQLException {
		
			SQLContainer container = new SQLContainer(new FreeformQuery("SELECT * "
					+ "FROM challenge",connectionPool));
			this.challengeContainer = container;
		
		}
        public SQLContainer getChallengeContainer() {
            return challengeContainer;
        }

        public SQLContainer getSubTopicContainer() {
            return subTopicContainer;
        }

        public SQLContainer getHelpfulLinksContainer() {
            return helpfulLinksContainer;
        }
        public SQLContainer getOverviewContainer() {
            return overviewContainer;
        }

        /**
         * Fetches a city name based on its key.
         * 
         * @param cityId
         *            Key
         * @return City name
         */
        public String getCityName(int challengeId) {

            Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
            return challengeContainer.getItem(challengeItemId).getItemProperty("NAME")
                    .getValue().toString();
        }
}
