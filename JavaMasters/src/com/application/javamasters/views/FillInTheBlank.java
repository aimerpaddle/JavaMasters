package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
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
		Window responseWindow = new Window();

        responseWindow.setCaption("");
		responseWindow.setResizable(true);
        responseWindow.setClosable(true);
        responseWindow.setHeight("100px");
        responseWindow.setWidth("200px");
        responseWindow.center();
        getUI().addWindow(responseWindow);
        
		if (userInputTextField.getValue().equals(null) || userInputTextField.getValue().equals(""))
		{
			//Tell the user that their input is invalid
	        responseWindow.setCaption("INVALID");
			responseWindow.focus();
		}
		else
		{
			if (userInputTextField.getValue().equalsIgnoreCase(solution))
			{
				//Tell the user that they got the question correct
		        responseWindow.setCaption("CORRECT");
				responseWindow.focus();
			}
			else
			{
				//Tell the user that they got the wrong answer.
		        responseWindow.setCaption("INCORRECT");
				responseWindow.focus();
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

}