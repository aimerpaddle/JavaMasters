package com.application.javamasters.views;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Overview extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private VerticalLayout panelLayout = new VerticalLayout();
	private VerticalLayout linksLayout = new VerticalLayout();
	private Panel linksPanel = new Panel();
	private Link video;
	
	public Overview(String Topic, String Subtopic, String video) 
	{
		super();
	
		this.setContent(panelLayout);
		panelLayout.addComponent(new Label("Overview"));
		panelLayout.addComponent(linksPanel);
		this.linksPanel.setContent(linksLayout);
		this.video = new Link("Video Turorial", new ExternalResource(video));
		linksLayout.addComponent(this.video);
	}
}
