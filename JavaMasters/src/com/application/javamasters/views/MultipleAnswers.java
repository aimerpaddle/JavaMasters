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
	BusinessLogic buslog = null;
	public MultipleAnswers(String subTopicName, String questionNumber){


		super(subTopicName, questionNumber);
		buslog = new BusinessLogic();
			
		int subTopicID = buslog.getSubtopicID(subTopicName);
		int challengeID = buslog.getChallengeId(subTopicID, questionNumber);	
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
		Label fillInTheBlankLabel = new Label("Fill In The Blank");
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
		//The window stuff is just to visually test if I am correctly getting the textField text
		// and correctly comparing them.
		Window responseWindow = new Window();
		String comparingString = "";
		Notification correct = new Notification("");
		
		correct.setDescription("Testing Description");
		correct.setCaption("Correct!");
		correct.setStyleName("Success");
       	
		correct.setPosition(Position.MIDDLE_CENTER);
		correct.setDelayMsec(2000);

        responseWindow.setCaption("");
		responseWindow.setResizable(true);
        responseWindow.setClosable(true);
        responseWindow.setHeight("100px");
        responseWindow.setWidth("200px");
        responseWindow.center();
        getUI().addWindow(responseWindow);
        
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
       	
        	responseWindow.setCaption("Invalid");
        if (comparingString.equalsIgnoreCase(solution.replaceAll("[^0-9]", ""))) {
        	
        	correct.show(Page.getCurrent());
        	//responseWindow.setCaption("Good Job");
        }
        else
        	correct.show(Page.getCurrent());
        	//responseWindow.setCaption("NO!!!!");
        
        //responseWindow.focus();
        
	}
	

	/**
	 * Creates a hint button that the student can click.  When
	 * clicked, it will show a hint in a window.
	 * 
	 * @param Text to go inside the hint.
	 * @return Hint Button
	 */
	private Button createHintButton(final String hintText) {

		final Window win = new Window("Hint");

		
		final Button hint = new Button("Hint",
                new ClickListener() {
					
                    @Override
                    public void buttonClick(ClickEvent event) {
                        getUI().addWindow(win);
                        win.setCaption(hintText);
                		win.setResizable(true);
                        win.setClosable(true);
                        win.setHeight(null);
                        win.center();
                        win.focus();
                        event.getButton().setEnabled(false);
                    }
                });
		
		return hint;
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

}