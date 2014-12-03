package com.application.javamasters.views;

import java.util.ArrayList;

import com.application.javamasters.business.BusinessLogic;
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
	private BusinessLogic buslog = null;
	
	public MultipleChoice(String subTopicName, String questionNumber){

		super(subTopicName, questionNumber);
		buslog = new BusinessLogic();
			
		int subTopicID = buslog.getSubtopicID(subTopicName);
		int challengeID = buslog.getChallengeId(subTopicID, questionNumber);	
		Panel questionContent = createQuestionContent(challengeID);
		Button submit = createSubmitButton(buslog.getSolution(challengeID));
		
		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(submit, "right: 200px; top: 570px;");
	}
	
	private Panel createQuestionContent(int challengeID) {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");
		
		VerticalLayout subLayout = new VerticalLayout();
		subLayout.setSpacing(true);
		Label multipleChoiceLabel = new Label("MultipleChoice");
		subLayout.addComponent(multipleChoiceLabel);
	
		ArrayList<String> radioButtons = buslog.getRadioButtons(challengeID);

		OptionGroup group = new OptionGroup();
		group.addItem(radioButtons.get(0));
		group.addItem(radioButtons.get(1));
		group.addItem(radioButtons.get(2));
		group.addItem(radioButtons.get(3));
		group.addItem(radioButtons.get(4));
		
		subLayout.addComponent(group);
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