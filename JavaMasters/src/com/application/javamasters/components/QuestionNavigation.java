package com.application.javamasters.components;

import com.application.javamasters.JavaMastersUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class QuestionNavigation extends Panel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2630493460440558169L;
	private VerticalLayout questionLayout = new VerticalLayout();
	
	public QuestionNavigation() {
		this.setCaption("Questions");
		this.setIcon(FontAwesome.LIST_ALT);
		this.setImmediate(true);
		this.setWidth("150px");
		this.setHeight(null);
		
		this.setContent(questionLayout);

		Button question1 = new Button("Question 1",
				new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						//This method call will have its arguments replaced by database calls.
						JavaMastersUI.changeProblemType("Logic Operators", "Question 1");
						//JavaMastersUI.changeProblemType(mainTopic, subTopic, questionChallengeID, question, hint, solution, questionTypeID);
					}
				});
		Button question2 = new Button("Question 2", 
			new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					//This method call will have its arguments replaced by database calls.
					JavaMastersUI.changeProblemType("Logic Operators", "Question 2");
				}
			});
		Button question3 = new Button("Question 3");
		Button question4 = new Button("Question 4");
		Button question5 = new Button("Question 5");
		
		Button[] questionButtons = {question1, question2, question3, question4, question5};
		
		for (Button button : questionButtons) {
			button.setSizeFull();
			questionLayout.addComponent(button);
			
		}
	}
	
	
}