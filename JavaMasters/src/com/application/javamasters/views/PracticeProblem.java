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

	public PracticeProblem() {


		setWidth("800px");
		setHeight("100%");
		
		BusinessLogic buslog = new BusinessLogic();

		//Panel questionPanel = createQuestion("Test");
		Panel questionPanel = createQuestion(buslog.getQuestion(4));
		//Panel questionPanel = createQuestion(buslog.getQuestionText(4)); 	
		Panel questionContent = createQuestionContent();
		Panel questionNavigation = new QuestionNavigation();
		Button hint = createHintButton();
		Button submit = createSubmitButton();

		addComponent(questionPanel, "left: 0px; top: 0px;");
		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(hint, "right: 300px; top: 570px;");
		addComponent(submit, "right: 200px; top: 570px;");
		addComponent(questionNavigation, "right: 0px; top: 0px;");
	}
	
	private Button createHintButton() {

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

	private Button createSubmitButton() {
		Button hint = new Button("Submit",
				new ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						validateUserAnswer(solution);
					}
				});

		return hint;
	}
	
	private void validateUserAnswer(String solution){
	}

	private Panel createQuestion(String questionText) {

		Panel questionPanel = new Panel("Example Question 1");
		questionPanel.setIcon(FontAwesome.QUESTION);
		questionPanel.setWidth("600px");
		questionPanel.setHeight("150px");

		Label question = new Label(questionText);
		question.addStyleName("huge");
		questionPanel.setContent(question);

		return questionPanel;
	}

	private Panel createQuestionContent() {

		Panel questionContent = new Panel();
		questionContent.setWidth("600px");
		questionContent.setHeight("400px");

		Label qcontent = new Label(
				"This is where the question element will go (ex. Multiple choice, fill in the blank, etc.");
		questionContent.setContent(qcontent);

		return questionContent;
	}
}