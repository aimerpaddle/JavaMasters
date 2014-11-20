package com.application.javamasters.business;

import com.application.javamasters.business.DatabaseManager;
import com.vaadin.data.util.sqlcontainer.SQLContainer;

public class BusinessLogic {

	private DatabaseManager dbManager = new DatabaseManager();
	private SQLContainer challengeContainer = dbManager.getChallengeContainer();
	private SQLContainer subTopicContainer = dbManager.getSubTopicContainer();
	private SQLContainer helpfulLinksContainer = dbManager.getHelpfulLinksContainer();
	private SQLContainer overviewContainer = dbManager.getOverviewContainer();

	public BusinessLogic(){
		
	}

	public String getQuestionTitle(int challengeId){
		
		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("TITLE")
				.getValue().toString();
	}
	
	public String getQuestion(int challengeId){
		
		Object challengeItemId = challengeContainer.getIdByIndex(challengeId);
		return challengeContainer.getItem(challengeItemId).getItemProperty("QUESTION")
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
//		
//		
//	}
	public String getHelpfulLinksContent(int helpfulLinkId) {
		
		Object challengeItemId = helpfulLinksContainer.getIdByIndex(helpfulLinkId);
		return helpfulLinksContainer.getItem(challengeItemId).getItemProperty("CONTENT")
				.getValue().toString();
	}
	
}
