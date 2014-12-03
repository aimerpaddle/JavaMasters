package com.application.javamasters.views;

import java.util.ArrayList;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The multiple answer problem type.  It uses 5 check boxes
 * for the user to enter their solution of the problem.
 * 
 * @author Ryan Wilson
 */
public class MultipleAnswers extends PracticeProblem {
	

	private static final long serialVersionUID = 1L;
	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	private CheckBox box4;
	private CheckBox box5;
	private Panel solPanel = null;
	BusinessLogic buslog = null;
	private int challengeID;
	public MultipleAnswers(String subTopicName, String questionNumber){


		super(subTopicName, questionNumber);
		buslog = new BusinessLogic();
			
		int subTopicID = buslog.getSubtopicID(subTopicName);
		challengeID = buslog.getChallengeId(subTopicID, questionNumber);	
		Panel questionContent = createQuestionContent(challengeID);
			
		Button submit = createSubmitButton(buslog.getSolution(challengeID));

		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(submit, "right: 200px; top: 570px;");

	}
	
	/**
	 * Overridden center Question Content panel that has the
	 * components needed for the fill in the blank question type.
	 * 
	 * @return Fill in the blank panel
	 */
	private Panel createQuestionContent(int challengeID) {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");
		
		VerticalLayout subLayout = new VerticalLayout();
	
		subLayout.setSpacing(true);
		Label fillInTheBlankLabel = new Label("Select all that apply.");
		subLayout.addComponent(fillInTheBlankLabel);
	
		ArrayList<String> checkboxes = buslog.getCheckBoxes(challengeID);

		box1 = new CheckBox(checkboxes.get(0));
		box2 = new CheckBox(checkboxes.get(1));
		box3 = new CheckBox(checkboxes.get(2));
		box4 = new CheckBox(checkboxes.get(3));
		box5 = new CheckBox(checkboxes.get(4));
		
		subLayout.addComponent(box1);
		subLayout.addComponent(box2);
		subLayout.addComponent(box3);
		subLayout.addComponent(box4);
		subLayout.addComponent(box5);

		questionContent.setContent(subLayout);
		return questionContent;
	}
	
	/**
	 * Method that tests if the User's answer is correct, incorrect, or invalid.
	 * 
	 * Note that in order for this to work, the solution from the database for
	 * multiple answer questions must be in ascending order (1,2,3,4,5).  If it
	 * is in any other order (2,5,1,3 or 5,4,3,2,1), this method won't correctly
	 * compare the user's answer with the solution.
	 * 
	 * @param The solution from the database
	 */
	private void validateUserAnswer(String solution)
	{
		String comparingString = "";
		Notification correct = new Notification("");
		Notification incorrect = new Notification("");
		Notification invalid = new Notification("");
		
		correct.setDescription("Your answer was correct! Good job!");
		correct.setCaption("Correct!");
		correct.setPosition(Position.MIDDLE_CENTER);
		correct.setDelayMsec(3000);
		correct.setStyleName("success");

		incorrect.setDescription("Your answer was incorrect. Please try again.");
		incorrect.setCaption("Incorrect!");
		incorrect.setPosition(Position.MIDDLE_CENTER);
		incorrect.setDelayMsec(2000);
		incorrect.setStyleName("failure");
        
		invalid.setDescription("Your response was invalid. Please try again.");
		invalid.setCaption("Error");
		invalid.setPosition(Position.MIDDLE_CENTER);
		invalid.setDelayMsec(2000);
		invalid.setStyleName("error");

        if (box1.getValue())
        	comparingString = "1";
        if (box2.getValue())
        	comparingString += "2";
        if (box3.getValue())
        	comparingString += "3";
        if (box4.getValue())
        	comparingString += "4";
        if (box5.getValue())
        	comparingString += "5";
        
        if (comparingString.equalsIgnoreCase("") || comparingString == null)
        	invalid.show(Page.getCurrent());
        else if (comparingString.equalsIgnoreCase(solution.replaceAll("[^0-9]", ""))){         	
        	correct.show(Page.getCurrent());
        	solPanel = createSolutionPanel(true);
        	if (!solPanel.isVisible()){
        		this.addComponent(solPanel, "right: 0px; top: 250px;");
        	}else {
        		removeComponent(solPanel);
        		this.addComponent(solPanel, "right: 0px; top: 250px;");
        		
        	}
        }
        else{
        	incorrect.show(Page.getCurrent());
        	solPanel = createSolutionPanel(false);
        	if (!solPanel.isVisible()){
        		this.addComponent(solPanel, "right: 0px; top: 250px;");
        	}else {
        		removeComponent(solPanel);
        		this.addComponent(solPanel, "right: 0px; top: 250px;");
        		
        	}
        }
        
        
	}
	
	/**
	 * Creates the submit button with a clickListener which calls a
	 * method that tests whether the user's input is correct.
	 * 
	 * @param solution retrieved from database
	 * @return Button with a click listener
	 */
	private Button createSubmitButton(final String solution) {
		Button hint = new Button("Submit",
				new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						validateUserAnswer(solution);
					}
				});
		
		return hint;
	}
	
	private Panel createSolutionPanel(boolean isCorrect){
		
		Panel solutionPanel = new Panel();
		solutionPanel.setWidth(null);
		solutionPanel.setHeight(null);


		VerticalLayout vLayout = new VerticalLayout();
		Label correct = new Label("Correct!");
		correct.addStyleName("success");
		Label incorrect = new Label("Incorrect!");
		incorrect.addStyleName("failure");

		
		vLayout.setSpacing(true);
		//vLayout.addStyleName("well");
		Button solution = new Button("Solution",
				new ClickListener() {
			
					@Override 
					public void buttonClick(ClickEvent event) {
					
						String solutionText = buslog.getSolution(challengeID);
						final Window win = new Window("Answer");
						win.setClosable(true);
						win.setContent(new Label ("Solution: " + solutionText));
						getUI().addWindow(win);
						win.center();
						win.focus();
					}
		});
		

		if(isCorrect){
			vLayout.addComponent(correct);
		}else{
			vLayout.addComponent(incorrect);
		}
		vLayout.addComponent(solution);
		solution.setSizeFull();
		solutionPanel.setContent(vLayout);

		return solutionPanel;
	}

}