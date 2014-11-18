package com.application.javamasters.views;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Overview extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private VerticalLayout panelLayout = new VerticalLayout();
	
	
	public Overview(String Topic, String Subtopic) 
	{
		super();
	
		this.setContent(panelLayout);
		panelLayout.addComponent(new Label("Overview"));
	}
}
