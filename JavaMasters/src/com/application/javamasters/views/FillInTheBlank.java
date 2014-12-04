package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/**
 * The Fill in the Blank Variant of Practice Problem.
 * 
 * @author Ryan Wilson
 */
public class FillInTheBlank extends PracticeProblem {
	
	
	
	private static final long serialVersionUID = 1L;
	private TextField userInputTextField;
	BusinessLogic buslog = null;
	private Panel solPanel = null;

	private int challengeID;

	/**
	 * A constructor that 
	 * 
	 * @param This problems mainTopic
	 * @param This problems subTopic
	 * @param What question number (1, 2, 3, 4, or 5) this question is
	 */
	public FillInTheBlank(String subTopicName, String questionNumber){
			

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
		
		HorizontalLayout subLayout = new HorizontalLayout();
		
		Label fillInTheBlankLabel = new Label("Fill In The Blank");
		subLayout.addComponent(fillInTheBlankLabel);
		
		userInputTextField = new TextField();
		userInputTextField.setNullSettingAllowed(true);
		subLayout.addComponent(userInputTextField);

		questionContent.setContent(subLayout);
		return questionContent;
	}
	
	/**
	 * Method that tests if the User's answer is correct, incorrect, or invalid.
	 * 
	 * @param The solution from the database
	 */
	private void validateUserAnswer(String solution)
	{
		//The window stuff is just to visually test if I am correctly getting the textField text
		// and correctly comparing them.
		Notification correct = new Notification("");
		Notification incorrect = new Notification("");
		Notification invalid = new Notification("");
		
		correct.setDescription("Your answer was correct! Good job!");
		correct.setCaption("Correct!");
		correct.setPosition(Position.MIDDLE_CENTER);
		correct.setDelayMsec(3000);
		correct.setStyleName("success");

		incorrect.setDescription("Your answer was incorrect. Please try again!");
		incorrect.setCaption("Incorrect!");
		incorrect.setPosition(Position.MIDDLE_CENTER);
		incorrect.setDelayMsec(2000);
		incorrect.setStyleName("failure");
        
		invalid.setDescription("Please enter an answer before submitting!");
		invalid.setCaption("Error");
		invalid.setPosition(Position.MIDDLE_CENTER);
		invalid.setDelayMsec(2000);
		invalid.setStyleName("error");
		
		if (userInputTextField.getValue().equals(null) || userInputTextField.getValue().equals(""))
		{
        	invalid.show(Page.getCurrent());
        	
		}
		else
		{
			if (userInputTextField.getValue().equalsIgnoreCase(solution))
			{
				//Tell the user that they got the question correct
	        	correct.show(Page.getCurrent());
	        	solPanel = createSolutionPanel(true);
	        	if (!solPanel.isVisible()){
	        		this.addComponent(solPanel, "right: 0px; top: 250px;");
	        	}else {
	        		removeComponent(solPanel);
	        		this.addComponent(solPanel, "right: 0px; top: 250px;");
	        		
	        	}
			}
			else
			{
				//Tell the user that they got the wrong answer.
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
					
					
					private static final long serialVersionUID = 1L;

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
		Label correct = new Label("Correct!  ");
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