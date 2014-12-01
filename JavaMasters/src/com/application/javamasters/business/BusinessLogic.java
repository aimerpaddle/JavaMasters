package com.application.javamasters.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.application.javamasters.business.DatabaseManager;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;

public class BusinessLogic {

	
	private DatabaseManager dbManager = null;
	private SQLContainer challengeContainer = null; 
	private SQLContainer subTopicContainer = null; 
	private Connection conn = null;
	//private SQLContainer helpfulLinksContainer = dbManager.getHelpfulLinksContainer();
	//private SQLContainer overviewContainer = dbManager.getOverviewContainer();

	public BusinessLogic(){
		
		try {			
			dbManager = new DatabaseManager();
			
			dbManager.initConnectionPool();
			conn = dbManager.initConnection();
			
			this.challengeContainer = dbManager.initContainers();
			//this.subTopicContainer = dbManager.getSubTopicContainer();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getSubtopicID(String subtopicName) {

		int id = 0;
		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT * FROM javamaster.subtopic WHERE name = ?;");
			
			stmt.setString(1, "Boolean");
			
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
			
				id = rs.getInt(1);
				
							
			}
		}catch (SQLException e){
			e.printStackTrace();
			
		}
		return id;
		
			
	}
	
	public String getChallengeId(int subtopicID, String questionNumber){
		
		String id = "";
		try{
		challengeContainer = new SQLContainer(new FreeformQuery("SELECT * FROM challenge WHERE subtopic_id = "
				+ subtopicID + "and title = " +
							questionNumber, dbManager.connectionPool));
		Object challengeID = challengeContainer.getIdByIndex(0);
		id = challengeContainer.getItem(challengeID).getItemProperty("id").getValue().toString();
		
		}catch (SQLException e ){
			e.printStackTrace();
		}
		
		return id;
		
	}
		//Object challengeID = challengeContainer.addContainerFilter(propertyId, filterString, ignoreCase, onlyMatchPrefix);
		
	
	public String getQuestionTitle(int challengeId){
		
		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("TITLE")
				.getValue().toString();
	}
	
	public String getQuestion(int challengeId){
		
		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("question")
				.getValue().toString();
	}

	public String getSolution(int challengeId){
		
		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("SOLUTION")
				.getValue().toString();
	}

	public String getHint(int challengeId){

		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("HINT")
				.getValue().toString();
	}
	
//	public String getSubtopicID(String subtopicName){
//		
//		Filter filter = new SimpleStringFilter("name", subtopicName, true, false);
//			
//		mySQLContainer.addContainerFilter(
//			    new Equal("LASTNAME", "Johnson"));
//
//		subTopicContainer.addContainerFilter(new Equal("Name", subtopicName));
//		
//		
//	}
//	public String getHelpfulLinksContent(int helpfulLinkId) {
//		
//		Object challengeItemId = helpfulLinksContainer.getIdByIndex(helpfulLinkId);
//		return helpfulLinksContainer.getItem(challengeItemId).getItemProperty("CONTENT")
//				.getValue().toString();
//	}
	public String getQuestionText(int challengeID) {
		
		String id = "";
		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT question FROM javamaster.challenge WHERE id = ?;");
			
			stmt.setInt(1, challengeID);
			
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
			
				id = rs.getString(1);
				
							
			}
		}catch (SQLException e){
			e.printStackTrace();
			
		}
		return id;
		
	}
	
}
