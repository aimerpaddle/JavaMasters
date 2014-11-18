package com.application.javamasters;

import javax.servlet.annotation.WebServlet;

import com.application.javamasters.components.NavigationMenu;
import com.application.javamasters.views.QuestionNavigation;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("valo")
public class JavaMastersUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = JavaMastersUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		final GridLayout pageLayout = new GridLayout(4, 3);
		pageLayout.setMargin(true);
		pageLayout.setSizeFull();
		pageLayout.setSpacing(true);
		setContent(pageLayout);
		
		Accordion acc = new NavigationMenu();
		pageLayout.addComponent(acc, 0, 0, 0, 1);
		
		Panel centerPanel = createCenterPanel();
		pageLayout.addComponent(centerPanel, 1, 0, 2, 2);
	}
	
	private Panel createCenterPanel() 
	{
		Panel centerPanel = new Panel();
		centerPanel.setSizeFull();
		GridLayout centerPanelLayout = new GridLayout(1, 3);
		centerPanelLayout.setSizeFull();
		centerPanel.setContent(centerPanelLayout);
		
		Label headerLabel = new Label("Welcome to Java Masters Learning Software");
		centerPanelLayout.addComponent(headerLabel);
		centerPanelLayout.setComponentAlignment(headerLabel, Alignment.MIDDLE_CENTER);
		
		return centerPanel;
	}

}