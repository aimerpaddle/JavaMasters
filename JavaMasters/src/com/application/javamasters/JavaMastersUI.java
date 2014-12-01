package com.application.javamasters;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.servlet.annotation.WebServlet;

import com.application.javamasters.components.NavigationMenu;
import com.application.javamasters.views.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("valo")
public class JavaMastersUI extends UI {

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int screenWidth = screenSize.width;
	
	private static VerticalLayout mainLayout;
	private static Panel centerPanel;
	private static AbsoluteLayout problemLayout;
	private static HorizontalLayout horizontalLayout;
	private static Label title;
	private static boolean centerContentIsALayout = false;

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = JavaMastersUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		setContent(mainLayout);
		
		mainLayout.addComponent(createHeader());
		
		horizontalLayout = createCenterLayout();
		mainLayout.addComponent(horizontalLayout);
		mainLayout.setExpandRatio(horizontalLayout, 1);
		
	}
	
	/**
	 * Responsible for creating the NavigationMenu and the panel that contains it.
	 * 
	 * @return Panel with the NavigationMenu
	 */
	private Panel createNavigation() {
		
		Panel menuContainer = new Panel("Navigation");
		menuContainer.setIcon(FontAwesome.NAVICON);
		menuContainer.addStyleName("light"); // No border
		menuContainer.setWidth("300px"); // Undefined width
		menuContainer.setHeight("100%");
		
		final VerticalLayout verticalNavigationLayout = new VerticalLayout();
		menuContainer.setHeight(null);	
		Accordion accordion = new NavigationMenu();
		menuContainer.setContent(verticalNavigationLayout);
		verticalNavigationLayout.addComponent(accordion);

		return menuContainer;
	}
	
	/**
	 * Method that returns the area that has the accordion menu
	 * and the centerPanel which can contain Overview, Practice
	 * Problems or helpful links.
	 * 
	 * @return 
	 */
	private HorizontalLayout createCenterLayout()
	{
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.setSpacing(true);
		hLayout.setHeight("100%");
		
		Panel navContainer = createNavigation();
		hLayout.addComponent(navContainer);
		
		centerPanel = createCenterContentPanel();
		hLayout.addComponent(centerPanel);
		
		return hLayout;
	}
	
	/**
	 * Creates and returns a Panel that is located at the center of the UI
	 * and contains the major content of the program.
	 * 
	 * @return Panel with a Label
	 */
	private Panel createCenterContentPanel() 
	{
		Panel centerPanel = new Panel();
		centerPanel.setSizeFull();
		AbsoluteLayout centerPanelLayout = new AbsoluteLayout();
		centerPanelLayout.setWidth(new String(2.25 * screenWidth / 3 + "px"));
		centerPanelLayout.setHeight("100%");
		centerPanel.setContent(centerPanelLayout);
		
		Label headerLabel = new Label("Welcome to Java Masters Learning Software");
		centerPanelLayout.addComponent(headerLabel);
		
		return centerPanel;
	}
	
	/**
	 * A method that creates the Header/Title label at the top of the page.
	 * 
	 * @return Horizontal Layout with a Label
	 */
	private HorizontalLayout createHeader() {
		
		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");
		title = new Label("Welcome to JavaMasters"); 
		header.addComponent(title);
		
		title.addStyleName("huge");
		title.addStyleName("color1");
		
		return header;
	}
	
	/**
	 * A method that changes the central panel to display information
	 * about either Overview, Practice Problems, or Helpful Links.
	 * 
	 * Note that this version of changing pages is pretty much entirely
	 * hard coded.  We eventually need to find a way to refactor this into
	 * a method or class.
	 * 
	 * @param main topic
	 * @param sub topic
	 * @param Overview, Practice Problems, or Helpful Links
	 */
	public static void changePage(String mainTopic, String subTopic, String page)
	{
		if (centerContentIsALayout)
			horizontalLayout.removeComponent(problemLayout);
		else
			horizontalLayout.removeComponent(centerPanel);
		switch (mainTopic)
		{
			case "Variables":
				switch (subTopic)
				{
					case "Variable Declaring / Instantiation":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
//								horizontalLayout.addComponent(centerPanel = new com.application.javamasters.views.Overview(
//										"https://www.google.com/?gws_rd=ssl",
//										"https://www.google.com/?gws_rd=ssl",
//										"https://www.google.com/?gws_rd=ssl")););
								centerContentIsALayout = false;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Practice Problems");
								horizontalLayout.addComponent(problemLayout = new MultipleAnswers(
										"Variables",	//Topic
										"Variable Declaring / Instantiation",	//Subtopic
										"2",	//QuestionID
										"Which of the following are used for loops?",	//Question
										"Hint... Hint",	//Hint text
										//We will need another parameter for the checkBoxValues (The text next to the checkboxes.)
										"1,2,3"));
								centerContentIsALayout = true;
								
								break;
							case "Helpful Links":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(centerPanel = new HelpfulLinks(
										"https://www.google.com/?gws_rd=ssl",
										"https://www.google.com/?gws_rd=ssl",
										"https://www.google.com/?gws_rd=ssl",
										"https://www.google.com/?gws_rd=ssl"));
								centerContentIsALayout = false;

								break;
						}
						break;
					case "Int":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
//								horizontalLayout.addComponent(centerPanel = new com.application.javamasters.views.Overview(
//										"https://www.google.com/?gws_rd=ssl",
//										"https://www.google.com/?gws_rd=ssl",
//										"https://www.google.com/?gws_rd=ssl")););
								centerContentIsALayout = false;

								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Practice Problems");
								horizontalLayout.addComponent(problemLayout = new FillInTheBlank(
										"Variables", 
										"Int",
										"3",
										"White boards are usually ____.",
										"Hint... Hint",
										"White"));
								centerContentIsALayout = true;

								break;
							case "Helpful Links":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(centerPanel = new com.application.javamasters.views.HelpfulLinks(
										"https://www.hotmail.com/",
										"https://www.google.com/?gws_rd=ssl",
										"https://www.google.com/?gws_rd=ssl",
										"https://www.google.com/?gws_rd=ssl"));
								centerContentIsALayout = false;

								break;
						}
						break;
					case "Double / Float":
						break;
					case "String / Char":
						break;
					case "Boolean":
						break;
					case "Scanner":
						break;
				}
				break;
				
			case "Control Statements 1":
				switch (subTopic)
				{
					case "Selection Statements":
						break;
					case "Logic Operators":
						break;
					case "Increment / Decrement":
						break;
				}
				break;
				
			case "Control Statements 2":
				switch (subTopic)
				{
					case "For Loop":
						break;
					case "While / Do While":
						break;
					case "For Each Loop":
						break;
				}
				break;
				
			case "Methods and Classes":
				switch (subTopic)
				{
					case "Classes - Declare/Create/Access":
						break;
					case "Methods":
						break;
					case "Getters / Setters":
						break;
					case "Constructors":
						break;
				}
				break;
				
			case "Arrays":
				switch (subTopic)
				{
					case "Passing Arrays as Arguments":
						break;
					case "Methods":
						break;
					case "Class Array":
						break;
					case "Array List <E>":
						break;
				}
				break;
			
		}
	}
	
	/**
	 * Changes the question depending on which question button the user clicks.
	 * 
	 * Again, this feature is pretty much entirely hard coded.  We eventually
	 * need a way to refactor this into a better method or class.
	 * 
	 * @param mainTopic
	 * @param subTopic
	 * @param What question number is it
	 * @param Question text 
	 * @param hint 
	 * @param solution text
	 * @param What type of question is it? (MultipleChoice, FillInTheBlank, or MultipleAnswer)
	 */
	public static void changeProblemType(
			String mainTopic,
			String subTopic,
			String questionChallengeID,
			String question,
			String hint,
			String solution,
			String questionTypeID)
	{
		PracticeProblem temp = null;
		switch (questionTypeID)
		{
			case "1":
//				temp = new MultipleChoice();
				break;
			case "2":
				temp = new MultipleAnswers(
						mainTopic,
						subTopic,
						questionChallengeID,
						question,
						hint,
						solution);
				break;
			case "3":
				temp = new FillInTheBlank(
						mainTopic,
						subTopic,
						questionChallengeID,
						question,
						hint,
						solution);
				break;
		}
		horizontalLayout.removeComponent(problemLayout);
		switch (mainTopic)
		{
			case "Variables":
				switch (subTopic)
				{
					case "Variable Declaring / Instantiation":
						horizontalLayout.addComponent(problemLayout = temp);
						break;
					case "Int":
						break;
					case "Double / Float":
						break;
					case "String / Char":
						break;
					case "Boolean":
						break;
					case "Scanner":
						break;
				}
				break;
				
			case "Control Statements 1":
				switch (subTopic)
				{
					case "Selection Statements":
						break;
					case "Logic Operators":
						break;
					case "Increment / Decrement":
						break;
				}
				break;
				
			case "Control Statements 2":
				switch (subTopic)
				{
					case "For Loop":
						break;
					case "While / Do While":
						break;
					case "For Each Loop":
						break;
				}
				break;
				
			case "Methods and Classes":
				switch (subTopic)
				{
					case "Classes - Declare/Create/Access":
						break;
					case "Methods":
						break;
					case "Getters / Setters":
						break;
					case "Constructors":
						break;
				}
				break;
				
			case "Arrays":
				switch (subTopic)
				{
					case "Passing Arrays as Arguments":
						break;
					case "Methods":
						break;
					case "Class Array":
						break;
					case "Array List <E>":
						break;
				}
				break;
			
		}
	}
}