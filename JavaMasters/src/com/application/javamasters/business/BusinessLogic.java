package com.application.javamasters.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

	// private SQLContainer helpfulLinksContainer =
	// dbManager.getHelpfulLinksContainer();
	// private SQLContainer overviewContainer =
	// dbManager.getOverviewContainer();

	public BusinessLogic() {

		try {
			dbManager = new DatabaseManager();

			dbManager.initConnectionPool();
			conn = dbManager.initConnection();

			this.challengeContainer = dbManager.initContainers();
			// this.subTopicContainer = dbManager.getSubTopicContainer();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public String getOverviewContent(int subTopicID) {

		String content = "";

		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT content FROM javamaster.overview WHERE subtopic_id = ?;");

			stmt.setInt(1, subTopicID);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				content = rs.getString(1);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return content;
	}

	public String getOverviewVideo(int subTopicID) {

		String url = "";
		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT video_link FROM javamaster.overview "
							+ "WHERE subtopic_id = ?");
			stmt.setInt(1, subTopicID);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				url = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException n) {
			n.printStackTrace();
		}

		return url;

	}

	public String getHelpfulLink(int subTopicID, int linkNum) {

		String helpfulLink = "";

		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT helpful_link"
							+ linkNum
							+ " FROM javamaster.helpful_links WHERE subtopic_id = ?;");

			stmt.setInt(1, subTopicID);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				helpfulLink = rs.getString(1);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return helpfulLink;
	}

	public String getHelpfulVideo(int subTopicID, int videoNum) {

		String videoURL = "";

		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT video_link"
							+ videoNum
							+ " FROM javamaster.helpful_links WHERE subtopic_id = ?;");

			stmt.setInt(1, subTopicID);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				videoURL = rs.getString(1);

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}

		return videoURL;
	}

	public int getSubtopicID(String subtopicName) {

		int id = 0;
		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT id FROM javamaster.subtopic WHERE name = ?;");

			stmt.setString(1, subtopicName);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				id = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return id;

	}

	public int getChallengeId(int subtopicID, String questionNumber) {

		int id = 0;
		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT id FROM challenge "
							+ "WHERE subtopic_id = ? and title = ?");
			stmt.setInt(1, subtopicID);
			stmt.setString(2, questionNumber);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				id = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;

	}

	public String getSubTopicName(int subTopicID) {

		String name = "";
		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT name FROM subtopic "
							+ "WHERE id = ?");
			stmt.setInt(1, subTopicID);

			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				name = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return name;
	}

	public int getChallengeTypeID(int challengeID) {

		int id = 0;

		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT type_id FROM challenge WHERE id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				id = rs.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public String getQuestionTitle(int challengeID) {

		String title = "";

		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT title FROM challenge WHERE id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				title = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return title;
	}

	public String getQuestion(int challengeID) {

		String question = "";

		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT question FROM challenge WHERE id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				question = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return question;
	}

	public String getSolution(int challengeID) {

		String solution = "";

		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT solution FROM challenge WHERE id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				solution = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return solution;
	}
	
	public ArrayList<String> getCheckBoxes(int challengeID){
		
		ArrayList<String> checkboxes = new ArrayList<String>();
		
		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT value1, value2, value3, value4, value5 FROM multiple_answer WHERE challenge_id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				checkboxes.add(rs.getString(1));
				checkboxes.add(rs.getString(2));
				checkboxes.add(rs.getString(3));
				checkboxes.add(rs.getString(4));
				checkboxes.add(rs.getString(5));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checkboxes;
		
	}

	public ArrayList<String> getRadioButtons(int challengeID){
		
		ArrayList<String> radioButtons = new ArrayList<String>();
		
		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT value1, value2, value3, value4, value5 FROM multiple_choice WHERE challenge_id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				radioButtons.add(rs.getString(1));
				radioButtons.add(rs.getString(2));
				radioButtons.add(rs.getString(3));
				radioButtons.add(rs.getString(4));
				radioButtons.add(rs.getString(5));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return radioButtons;
		
	}
	
	
	public String getHint(int challengeID) {

		String hint = "";

		try {

			PreparedStatement stmt = conn
					.prepareStatement("SELECT hint FROM challenge WHERE id = ?");
			stmt.setInt(1, challengeID);
			stmt.executeQuery();
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {

				hint = rs.getString(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hint;
	}

	// public String getSubtopicID(String subtopicName){
	//
	// Filter filter = new SimpleStringFilter("name", subtopicName, true,
	// false);
	//
	// mySQLContainer.addContainerFilter(
	// new Equal("LASTNAME", "Johnson"));
	//
	// subTopicContainer.addContainerFilter(new Equal("Name", subtopicName));
	//
	//
	// }
	// public String getHelpfulLinksContent(int helpfulLinkId) {
	//
	// Object challengeItemId =
	// helpfulLinksContainer.getIdByIndex(helpfulLinkId);
	// return
	// helpfulLinksContainer.getItem(challengeItemId).getItemProperty("CONTENT")
	// .getValue().toString();
	// }
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
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return id;

	}

}
