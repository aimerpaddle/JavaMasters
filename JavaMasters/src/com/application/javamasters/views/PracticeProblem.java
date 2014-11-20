package com.application.javamasters.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PracticeProblem extends AbsoluteLayout{
	
	private String hintText = null;

	public PracticeProblem(String subTopic, String mainTopic) {

		setWidth("800px");
		setHeight("100%");

		Panel questionPanel = createQuestion(); 	
		Panel questionContent = createQuestionContent();
		Panel questionNavigation = createQuestionNavigation();
		Button hint = createHintButton();
		Button submit = createSubmitButton();

		addComponent(questionPanel, "left: 0px; top: 0px;");
		addComponent(questionContent, "left: 0px; top: 160px;");
		addComponent(hint, "right: 300px; top: 570px;");
		addComponent(submit, "right: 200px; top: 570px;");
		addComponent(questionNavigation, "right: 0px; top: 0px");
	}
	
	private Button createHintButton() {

		String hintText = "This is a test hint";
		final Window win = new Window("Hint");
		win.setCaption("testing the hint stuff");
		win.setResizable(true);
        win.setClosable(true);
        win.setHeight(null);
        win.center();
        win.focus();
		
		final Button hint = new Button("Hint",
                new ClickListener() {
					
                    @Override
                    public void buttonClick(ClickEvent event) {
                        getUI().addWindow(win);
                        win.setCaption("testing the hint stuff");
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

	private Button createSubmitButton() {
		Button hint = new Button("Submit");

		return hint;
	}

	private Panel createQuestion() {

		Panel questionPanel = new Panel("Example Question 1");
		questionPanel.setIcon(FontAwesome.QUESTION);
		questionPanel.setWidth("600px");
		questionPanel.setHeight("150px");

		Label question = new Label("Is this a test question?");
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

	private Panel createQuestionNavigation() {

		Panel questionNavigation = new Panel("Questions");
		questionNavigation.setIcon(FontAwesome.LIST_ALT);
		questionNavigation.setImmediate(true);
		questionNavigation.setWidth("150px");
		questionNavigation.setHeight(null);

		VerticalLayout questionLayout = new VerticalLayout();

		questionNavigation.setContent(questionLayout);

		Button question1 = new Button("Question 1");
		Button question2 = new Button("Question 2");
		Button question3 = new Button("Question 3");
		Button question4 = new Button("Question 4");
		Button question5 = new Button("Question 5");

		Button[] questionButtons = { question1, question2, question3,
				question4, question5 };

		for (Button button : questionButtons) {
			button.setSizeFull();
			questionLayout.addComponent(button);

		}

		return questionNavigation;
	}
}
