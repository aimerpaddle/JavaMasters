package com.application.javamasters.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class MultipleChoice extends PracticeProblem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 182216139118824118L;
	private OptionGroup possibleAnswers;
	private String[] radioButtonValues;
	
	public MultipleChoice(String mainTopic, String subTopic, String questionChallengeID, 
			String[] radioButtonValues, String question, String hintText, String solution){
		
		this.radioButtonValues = radioButtonValues;
		
		for (int i = 0; i < radioButtonValues.length - 1; i++)
			this.possibleAnswers.addItem(this.radioButtonValues[i]);	
		
		
		
		Panel questionContent = createQuestionContent();
			
		//This line will have the arguments changed with calls to the database.
		Panel questionPanel = createQuestion(questionChallengeID, question);
		//This line will have the arguments changed with calls to the database.
		Button hint = createHintButton(hintText);
		//This line will have the arguments changed with calls to the database.
		Button submit = createSubmitButton(solution);

		addComponent(questionPanel, "left: 0px; top: 0px;");
		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(hint, "right: 300px; top: 570px;");
		addComponent(submit, "right: 200px; top: 570px;");
	}
	
	private Panel createQuestionContent() {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");
		
		VerticalLayout subLayout = new VerticalLayout();
		
		Label multipleChoiceLabel = new Label("MultipleChoice");
		subLayout.addComponent(multipleChoiceLabel);
		
		subLayout.addComponent(this.possibleAnswers);

		questionContent.setContent(subLayout);
		return questionContent;
	}
	
	private void validateUserAnswer(String solution)
	{
		//The window stuff is just to visually test if I am correctly getting the radioButton text
		// and correctly comparing them.
		Window responseWindow = new Window();

        responseWindow.setCaption("");
		responseWindow.setResizable(true);
        responseWindow.setClosable(true);
        responseWindow.setHeight("100px");
        responseWindow.setWidth("200px");
        responseWindow.center();
        getUI().addWindow(responseWindow);
        
        
        if (((String) possibleAnswers.getValue()).equalsIgnoreCase(""))
        	responseWindow.setCaption("Invalid");
        if (((String) possibleAnswers.getValue()).equalsIgnoreCase(solution))
        	responseWindow.setCaption("Good Job");
        else
        	responseWindow.setCaption("NO!!!!");
        
        responseWindow.focus();
        
	}
	
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