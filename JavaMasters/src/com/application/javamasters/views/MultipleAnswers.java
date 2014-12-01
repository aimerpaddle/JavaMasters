package com.application.javamasters.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
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
	
	private CheckBox box1;
	private CheckBox box2;
	private CheckBox box3;
	private CheckBox box4;
	private CheckBox box5;
	private CheckBox[] boxes;
	private String[] checkBoxValues;

	public MultipleAnswers(
			String mainTopic,
			String subTopic,
			String questionChallengeID,
			/*String[] checkBoxValues,*/ 
			String question,
			String hintText,
			String solution){
		
		
//		for (int i = 0; i < checkBoxValues.length; i++)
//			this.checkBoxValues[i] = checkBoxValues[i];
		
//		boxes = new CheckBox[5];
//		for (int i = 0; i < 5; i++)
//			boxes[i] = new CheckBox(checkBoxValues[i]);
			
		
		
		
		Panel questionContent = createQuestionContent();
			
		//This line will have the arguments changed with calls to the database.
		Panel questionPanel = createQuestion("3", "What kind of somethin or other is a _______ when blah blah.");
		//This line will have the arguments changed with calls to the database.
		Button hint = createHintButton(hintText);
		//This line will have the arguments changed with calls to the database.
		Button submit = createSubmitButton(solution);

		addComponent(questionPanel, "left: 0px; top: 0px;");
		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(hint, "right: 300px; top: 570px;");
		addComponent(submit, "right: 200px; top: 570px;");
	}
	
	/**
	 * Overridden center Question Content panel that has the
	 * components needed for the fill in the blank question type.
	 * 
	 * @return Fill in the blank panel
	 */
	private Panel createQuestionContent() {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");
		
		VerticalLayout subLayout = new VerticalLayout();
		
		Label fillInTheBlankLabel = new Label("Fill In The Blank");
		subLayout.addComponent(fillInTheBlankLabel);
		
		
		
		box1 = new CheckBox("for");
		subLayout.addComponent(box1);
		
		box2 = new CheckBox("while");
		subLayout.addComponent(box2);
		
		box3 = new CheckBox("for each loop");
		subLayout.addComponent(box3);
		
		box4 = new CheckBox("switch");
		subLayout.addComponent(box4);
		
		box5 = new CheckBox("Sysout.");
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
        if (comparingString.equalsIgnoreCase(solution.replaceAll("[^0-9]", "")))
        	responseWindow.setCaption("Good Job");
        else
        	responseWindow.setCaption("NO!!!!");
        
        responseWindow.focus();
        
	}
	
	/**
	 * Creates the panel and the text for the problem type.
	 * 
	 * @param Either question number 1,2,3,4, or 5
	 * @param The text for the question
	 * @return formatted Panel
	 */
	private Panel createQuestion(String questionNumber, String questionText) {

		Panel questionPanel = new Panel("Question " + questionNumber);
		questionPanel.setIcon(FontAwesome.QUESTION);
		questionPanel.setWidth("600px");
		questionPanel.setHeight("150px");

		Label questionLabel = new Label(questionText);
		questionLabel.addStyleName("huge");
		questionPanel.setContent(questionLabel);

		return questionPanel;
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

//	public String getMainTopic() {
//		return mainTopic;
//	}
//
//	public String getSubTopic() {
//		return subTopic;
//	}

		
	
}