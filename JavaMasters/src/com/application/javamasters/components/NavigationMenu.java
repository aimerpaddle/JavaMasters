package com.application.javamasters.components;


import com.application.javamasters.JavaMastersUI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;


@SuppressWarnings("serial")
@Theme("valo")
public class NavigationMenu extends Accordion
{	
	@VaadinServletConfiguration(productionMode = false, ui = com.application.javamasters.JavaMastersUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	public NavigationMenu()
	{
		setWidth("300");
		setSizeFull();
		
		//Main Topic - Variables
		Panel mainTopic_Variables = new Panel();
		VerticalLayout variablesLayout = new VerticalLayout();
		mainTopic_Variables.setContent(variablesLayout);
		addTab(mainTopic_Variables, "Variables");
		
		MenuBar subTopic_VarDec = getMenuBar("Variables", "Variable Declaring / Instantiation");
		subTopic_VarDec.addStyleName("color1");
		variablesLayout.addComponent(subTopic_VarDec);
		MenuBar subTopic_Int = getMenuBar("Variables", "Int");
		variablesLayout.addComponent(subTopic_Int);
		MenuBar subTopic_DoubleFloat = getMenuBar("Variables", "Double / Float");
		variablesLayout.addComponent(subTopic_DoubleFloat);
		MenuBar subTopic_StringChar = getMenuBar("Variables", "String / Char");
		variablesLayout.addComponent(subTopic_StringChar);
		MenuBar subTopic_Boolean = getMenuBar("Variables", "Boolean");
		variablesLayout.addComponent(subTopic_Boolean);
		MenuBar subTopic_Scanner = getMenuBar("Variables", "Scanner");
		variablesLayout.addComponent(subTopic_Scanner);
		
		
		//Main Topic - Control Statements 1
		Panel mainTopic_CS1 = new Panel();
		VerticalLayout controlStatements1Layout = new VerticalLayout();
		mainTopic_CS1.setContent(controlStatements1Layout);
		addTab(mainTopic_CS1, "Control Statements 1");
		
		MenuBar subTopic_SelectionStatements = getMenuBar("Control Statements 1", "Selection Statements");
		controlStatements1Layout.addComponent(subTopic_SelectionStatements);
		MenuBar subTopic_LogicOperators = getMenuBar("Control Statements 1", "Logic Operators");
		controlStatements1Layout.addComponent(subTopic_LogicOperators);
		MenuBar subTopic_IncDec = getMenuBar("Control Statements 1", "Increment / Decrement");
		controlStatements1Layout.addComponent(subTopic_IncDec);
		
		
		//Main Topic - Control Statements 2
		Panel mainTopic_CS2 = new Panel();
		VerticalLayout controlStatements2Layout = new VerticalLayout();
		mainTopic_CS2.setContent(controlStatements2Layout);
		addTab(mainTopic_CS2, "Control Statements 2");
		
		MenuBar subTopic_ForLoop = getMenuBar("Control Statements 2", "For Loop");
		controlStatements2Layout.addComponent(subTopic_ForLoop);
		MenuBar subTopic_WhileDoWhile = getMenuBar("Control Statements 2", "While / Do While");
		controlStatements2Layout.addComponent(subTopic_WhileDoWhile);
		MenuBar subTopic_ForEachLoop = getMenuBar("Control Statements 2", "For Each Loop");
		controlStatements2Layout.addComponent(subTopic_ForEachLoop);
		
		
		//Main Topic - Methods and Classes
		Panel mainTopic_MethodsClasses = new Panel();
		VerticalLayout methodsClassesLayout = new VerticalLayout();
		mainTopic_MethodsClasses.setContent(methodsClassesLayout);
		addTab(mainTopic_MethodsClasses, "Methods and Classes");
		
		MenuBar subTopic_Classes = getMenuBar("Methods and Classes", "Classes - Declare/Create/Access");
		methodsClassesLayout.addComponent(subTopic_Classes);
		MenuBar subTopic_Methods = getMenuBar("Methods and Classes", "Methods");
		methodsClassesLayout.addComponent(subTopic_Methods);
		MenuBar subTopic_GettersSetters = getMenuBar("Methods and Classes", "Getters / Setters");
		methodsClassesLayout.addComponent(subTopic_GettersSetters);
		MenuBar subTopic_Constructors = getMenuBar("Methods and Classes", "Constructors");
		methodsClassesLayout.addComponent(subTopic_Constructors);
		
		
		//Main Topic - Arrays
		Panel mainTopic_Arrays = new Panel();
		VerticalLayout arraysLayout = new VerticalLayout();
		mainTopic_Arrays.setContent(arraysLayout);
		addTab(mainTopic_Arrays, "Arrays");
		
		MenuBar subTopic_ArrayArguments = getMenuBar("Arrays", "Passing Arrays as Arguments");
		arraysLayout.addComponent(subTopic_ArrayArguments);
		MenuBar subTopic_Arrays = getMenuBar("Arrays", "Methods");
		arraysLayout.addComponent(subTopic_Arrays);
		MenuBar subTopic_ClassArray = getMenuBar("Arrays", "Class Array");
		arraysLayout.addComponent(subTopic_ClassArray);
		MenuBar subTopic_ArrayList = getMenuBar("Arrays", "Array List <E>");
		arraysLayout.addComponent(subTopic_ArrayList);
	}
	
	/**
	 * Returns a MenuBar that has the functionality to change pages
	 * depending on what topic, subtopic, and page the user selects.
	 * 
	 * @param mainTopic
	 * @param subTopic
	 * @return MenuBar
	 */
	static MenuBar getMenuBar(final String mainTopic, final String subTopic) {
        Command click = new Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Notification.show("Clicked " + mainTopic + "   |   " + subTopic + "   |   " + selectedItem.getText());
                
                JavaMastersUI.changePage(mainTopic, subTopic, selectedItem.getText());
            }
        };
        
        MenuBar menubar = new MenuBar();
        menubar.setWidth("100%");
        final MenuBar.MenuItem file = menubar.addItem(subTopic, null);
        file.addItem("Overview", click);
        file.addItem("Practice Problems", click);
        file.addItem("Helpful Links", click);

        return menubar;
    }
}