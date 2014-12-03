package com.application.javamasters.views;

import java.util.ArrayList;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class MultipleChoice extends PracticeProblem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 182216139118824118L;
	private BusinessLogic buslog = null;
	private OptionGroup group;
	private ArrayList<String> radioButtons;
	private Panel solPanel = null;
	private int challengeID;

	public MultipleChoice(String subTopicName, String questionNumber) {

		super(subTopicName, questionNumber);
		buslog = new BusinessLogic();

		int subTopicID = buslog.getSubtopicID(subTopicName);
		challengeID = buslog.getChallengeId(subTopicID, questionNumber);
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
		Label multipleChoiceLabel = new Label("Please choose an option below.");
		subLayout.addComponent(multipleChoiceLabel);

		radioButtons = buslog.getRadioButtons(challengeID);

		group = new OptionGroup();

		group.addItem(radioButtons.get(0));
		group.addItem(radioButtons.get(1));
		group.addItem(radioButtons.get(2));
		group.addItem(radioButtons.get(3));
		group.addItem(radioButtons.get(4));

		subLayout.addComponent(group);
		questionContent.setContent(subLayout);
		return questionContent;
	}

	private void validateUserAnswer(String solution) {
		Notification correct = new Notification("");
		Notification incorrect = new Notification("");
		Notification invalid = new Notification("");

		correct.setDescription("Your answer was correct! Good job!");
		correct.setCaption("Correct!");
		correct.setPosition(Position.MIDDLE_CENTER);
		correct.setDelayMsec(3000);
		correct.setStyleName("success");

		incorrect
				.setDescription("Your answer was incorrect. Please try again.");
		incorrect.setCaption("Incorrect!");
		incorrect.setPosition(Position.MIDDLE_CENTER);
		incorrect.setDelayMsec(2000);
		incorrect.setStyleName("failure");

		invalid.setDescription("Please select an option before clicking submit.");
		invalid.setCaption("Error");
		invalid.setPosition(Position.MIDDLE_CENTER);
		invalid.setDelayMsec(2000);
		invalid.setStyleName("error");

		if (group.getValue() == null) {
			invalid.show(Page.getCurrent());
		} else if (radioButtons.get(Integer.parseInt(solution) - 1).equals(
				group.getValue())) {
			correct.show(Page.getCurrent());
			solPanel = createSolutionPanel(true);
			if (!solPanel.isVisible()) {
				this.addComponent(solPanel, "right: 0px; top: 250px;");
			} else {
				removeComponent(solPanel);
				this.addComponent(solPanel, "right: 0px; top: 250px;");

			}
		} else {
			incorrect.show(Page.getCurrent());
			solPanel = createSolutionPanel(true);
			if (!solPanel.isVisible()) {
				this.addComponent(solPanel, "right: 0px; top: 250px;");
			} else {
				removeComponent(solPanel);
				this.addComponent(solPanel, "right: 0px; top: 250px;");

			}
		}
	}

	private Button createSubmitButton(final String solution) {
		Button hint = new Button("Submit", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				validateUserAnswer(solution);
			}
		});

		return hint;
	}

	private Panel createSolutionPanel(boolean isCorrect) {

		Panel solutionPanel = new Panel();
		solutionPanel.setWidth(null);
		solutionPanel.setHeight(null);

		VerticalLayout vLayout = new VerticalLayout();
		Label correct = new Label("Correct!");
		correct.addStyleName("success");
		Label incorrect = new Label("Incorrect!");
		incorrect.addStyleName("failure");

		vLayout.setSpacing(true);
		// vLayout.addStyleName("well");
		Button solution = new Button("Solution", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				String solutionText = buslog.getSolution(challengeID);
				final Window win = new Window("Answer");
				win.setClosable(true);
				win.setContent(new Label("Solution: " + solutionText));
				getUI().addWindow(win);
				win.center();
				win.focus();
			}
		});

		if (isCorrect) {
			vLayout.addComponent(correct);
		} else {
			vLayout.addComponent(incorrect);
		}
		vLayout.addComponent(solution);
		solution.setSizeFull();
		solutionPanel.setContent(vLayout);

		return solutionPanel;
	}

}