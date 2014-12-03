package com.application.javamasters;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.servlet.annotation.WebServlet;

import com.application.javamasters.business.BusinessLogic;
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
	private static AbsoluteLayout contentLayout;
	private static AbsoluteLayout problemLayout;
	private static HorizontalLayout horizontalLayout;
	private static Label title;
	private static boolean centerContentIsALayout = false;
	private static boolean hLayoutIsSet = false;

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
		hLayoutIsSet = true;
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
		if (hLayoutIsSet)
			horizontalLayout.removeComponent(centerPanel);
		if (centerContentIsALayout)
			horizontalLayout.removeComponent(contentLayout);
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
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Variable Declaring / Instantiation"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Variable Declaring / Instantiation", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Variable Declaring / Instantiation"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Int":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Int  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Int"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Int  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new FillInTheBlank("Int", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  Int  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Int"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Double / Float":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Double / Float  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Double / Float"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Double / Float  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleChoice("Double / Float", "Question 4"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  Double / Float  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Double / Float"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "String / Char":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  String / Char  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("String / Char"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  String / Char  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("String / Char", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  String / Char  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("String / Char"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Boolean":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Boolean  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Boolean"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Boolean  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Boolean", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  Boolean  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Boolean"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Scanner":
						switch (page)
						{
							case "Overview":
								title.setCaption("Variables  |  Scanner  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Scanner"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Variables  |  Scanner  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Scanner", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Variables  |  Scanner  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Scanner"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
				}
				break;
				
			case "Control Statements 1":
				switch (subTopic)
				{
					case "Selection Statements":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 1  |  Selection Statements  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Selection Statements"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 1  |  Selection Statements  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Selection Statements", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 1  |  Selection Statements  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Selection Statements"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Logic Operators":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 1  |  Logic Operators  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Logic Operators"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 1  |  Logic Operators  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Logic Operators", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 1  |  Logic Operators  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Logic Operators"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Increment / Decrement":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 1  |  Increment / Decrement  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Increment / Decrement"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 1  |  Increment / Decrement  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Increment / Decrement", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 1  |  Increment / Decrement  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Increment / Decrement"));
										
								centerContentIsALayout = true;

								break;
						}

						break;
				}
				break;
				
			case "Control Statements 2":
				switch (subTopic)
				{
					case "For Loop":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 2  |  For Loop  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("For Loop"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 2  |  For Loop  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("For Loop", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 2  |  For Loop  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("For Loop"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "While / Do While":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 2  |  While / Do While  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("While / Do While"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 2  |  While / Do While  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("While / Do While", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 2  |  While / Do While  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("While / Do While"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "For Each Loop":
						switch (page)
						{
							case "Overview":
								title.setCaption("Control Statements 2  |  For Each Loop  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("For Each Loop"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Control Statements 2  |  For Each Loop  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("For Each Loop", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Control Statements 2  |  For Each Loop  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("For Each Loop"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
				}
				break;
				
			case "Methods and Classes":
				switch (subTopic)
				{
					case "Classes - Declare/Create/Access":
						switch (page)
						{
							case "Overview":
								title.setCaption("Methods and Classes  |  Classes - Declare/Create/Access  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Classes - Declare/Create/Access"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Methods and Classes  |  Classes - Declare/Create/Access  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Classes - Declare/Create/Access", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Methods and Classes  |  Classes - Declare/Create/Access  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Classes - Declare/Create/Access"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Methods":
						switch (page)
						{
							case "Overview":
								title.setCaption("Methods and Classes  |  Methods  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Methods"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Methods and Classes  |  Methods  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Methods", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Methods and Classes  |  Methods  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Methods"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Getters / Setters":
						switch (page)
						{
							case "Overview":
								title.setCaption("Methods and Classes  |  Getters / Setters  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Getters / Setters"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Methods and Classes  |  Getters / Setters  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Getters / Setters", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Methods and Classes  |  Getters / Setters  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Getters / Setters"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
					case "Constructors":
						switch (page)
						{
							case "Overview":
								title.setCaption("Methods and Classes  |  Constructors  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Constructors"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Methods and Classes  |  Constructors  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Constructors", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Methods and Classes  |  Constructors  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Constructors"));
										
								centerContentIsALayout = true;

								break;
						}
						break;
				}
				break;
				
			case "Arrays":
				switch (subTopic)
				{
					case "Passing Arrays as Arguments":
						switch (page)
						{
							case "Overview":
								title.setCaption("Arrays  |  Passing Arrays as Arguments  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Passing Arrays as Arguments"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Arrays  |  Passing Arrays as Arguments  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Passing Arrays as Arguments", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Arrays  |  Passing Arrays as Arguments  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Passing Arrays as Arguments"));
										
								centerContentIsALayout = true;

								break;
						}
											
						break;
					case "Methods":
						switch (page)
						{
							case "Overview":
								title.setCaption("Arrays  |  Variable Declaring / Instantiation  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Logic Operators"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Arrays  |  Variable Declaring / Instantiation  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Logic Operators", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Arrays  |  Variable Declaring / Instantiation  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Logic Operators"));
										
								centerContentIsALayout = true;

								break;
						}
						
						break;
					case "Class Array":
						switch (page)
						{
							case "Overview":
								title.setCaption("Arrays  |  Class Array  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Class Array"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Arrays  |  Class Array  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Class Array", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Arrays  |  Class Array  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Class Array"));
										
								centerContentIsALayout = true;

								break;
						}
						
						break;
					case "Array List <E>":
						switch (page)
						{
							case "Overview":
								title.setCaption("Arrays  |  Array List <E>  |  Overview");	//This is just a test...
								horizontalLayout.addComponent( contentLayout = new com.application.javamasters.views.Overview("Array List <E>"));
								centerContentIsALayout = true;
								break;
							case "Practice Problems":
								title.setCaption("Arrays  |  Array List <E>  |  Practice Problems");
								horizontalLayout.addComponent(contentLayout = new MultipleAnswers("Array List <E>", "Question 2"));
										
								centerContentIsALayout = true;
								
								break;
								
							case "Helpful Links":
								title.setCaption("Arrays  |  Array List <E>  |  Helpful Links");	//This is just a test...
								horizontalLayout.addComponent(contentLayout = new com.application.javamasters.views.HelpfulLinks("Array List <E>"));
										
								centerContentIsALayout = true;

								break;
						}
						
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
	public static void changeProblemType(String subTopic, String questionNum){

		BusinessLogic bl = new BusinessLogic();
		
		int subtopicID = bl.getSubtopicID(subTopic);
		int challengeID = bl.getChallengeId(subtopicID, questionNum);
		
		int questionTypeID = bl.getChallengeTypeID(challengeID);
		PracticeProblem temp = null;
		switch (questionTypeID)
		{
			case 1:
				temp = new MultipleChoice(subTopic, questionNum);
				break;
			case 2:
				temp = new MultipleAnswers(subTopic, questionNum);
						
				break;
			case 3:
				temp = new FillInTheBlank(subTopic, questionNum);
				break;
		}
		horizontalLayout.removeComponent(problemLayout);
		horizontalLayout.addComponent(temp);
		
	}
}