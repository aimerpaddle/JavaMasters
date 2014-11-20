package com.application.javamasters.business;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

public class DatabaseManager {

	private SimpleJDBCConnectionPool connectionPool = null;
    private SQLContainer challengeContainer = null;
    private SQLContainer helpfulLinksContainer = null;
    private SQLContainer overviewContainer = null;
    private SQLContainer subTopicContainer = null;

	public DatabaseManager() {
		initConnectionPool();
		initContainers();
	}

	/*
	 * Creates a new connection to the server, then tries to connect  
	 * to the javamaster database.
	 */
	private void initConnectionPool() {
		try {
			connectionPool = new SimpleJDBCConnectionPool(
					"com.mysql.jdbc.Driver",
					"jdbc:mysql://aimerpaddle.com:3306/javamaster", "testing",
					"Test1234", 2, 5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnectionPool() {
	
		
	}
	private void initContainers() {
        try {
            /* TableQuery and SQLContainer for challenge table */
            TableQuery q1 = new TableQuery("challenge", connectionPool);
            q1.setVersionColumn("VERSION");
            challengeContainer = new SQLContainer(q1);

            /* TableQuery and SQLContainer for subtopic table */
            TableQuery q2 = new TableQuery("subtopic", connectionPool);
            q2.setVersionColumn("VERSION");
            subTopicContainer = new SQLContainer(q2);

            /* TableQuery and SQLContainer for helpful_links table */
            TableQuery q3 = new TableQuery("helpful_links", connectionPool);
            q2.setVersionColumn("VERSION");
            helpfulLinksContainer = new SQLContainer(q2);

            /* TableQuery and SQLContainer for overview table */
            TableQuery q4 = new TableQuery("overview", connectionPool);
            q2.setVersionColumn("VERSION");
            overviewContainer = new SQLContainer(q2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
