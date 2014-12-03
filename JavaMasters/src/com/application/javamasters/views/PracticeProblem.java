package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.application.javamasters.components.QuestionNavigation;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PracticeProblem extends AbsoluteLayout{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hintText;
	private String solution;
	private BusinessLogic buslog = null;

	public PracticeProblem(String subTopicName, String questionNumber) {


		setWidth("800px");
		setHeight("100%");
		
		buslog = new BusinessLogic();
		
		int subTopicID = buslog.getSubtopicID(subTopicName);
		int challengeID = buslog.getChallengeId(subTopicID, questionNumber);	
			
		Panel questionPanel = createQuestion(questionNumber, buslog.getQuestion(challengeID));
		Button hint = createHintButton(buslog.getHint(challengeID));
		Panel questionNavigation = new QuestionNavigation();


		addComponent(questionPanel, "left: 0px; top: 0px;");
		addComponent(hint, "right: 300px; top: 570px;");
		addComponent(questionNavigation, "right: 0px; top: 0px;");
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
                        win.setHeight("200px");
                        win.setWidth("300px");
                        win.center();
                        win.focus();
                        event.getButton().setEnabled(false);
                    }
                });
		
		return hint;
	}

	private Button createSubmitButton(final String solution) {
		Button submit = new Button("Submit",
				new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						validateUserAnswer(solution);
					}
				});

		return submit;
	}
	
	private void validateUserAnswer(String solution){
	}

	private Panel createQuestion(String questionNumber, String questionText) {

		Panel questionPanel = new Panel(questionNumber);
		questionPanel.setIcon(FontAwesome.QUESTION);
		questionPanel.setWidth("600px");
		questionPanel.setHeight("150px");

		Label question = new Label(questionText);
		question.addStyleName("huge");
		questionPanel.setContent(question);

		return questionPanel;
	}

	private Panel createQuestionContent(int ChallengeID) {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");

		Label qcontent = new Label(
				"This is where the question element will go (ex. Multiple choice, fill in the blank, etc.");
		questionContent.setContent(qcontent);

		return questionContent;
	}
}