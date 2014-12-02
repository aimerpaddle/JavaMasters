package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Overview extends AbsoluteLayout{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private VerticalLayout panelLayout = new VerticalLayout();
	private VerticalLayout linksLayout = new VerticalLayout();
	private Panel linksPanel = new Panel();
	
	
	public Overview(String subTopicName) {
		setWidth("800px");
		setHeight("100%");

		BusinessLogic bl = new BusinessLogic();
	
		int subTopicID = bl.getSubtopicID(subTopicName);
		Panel overviewContent = new Panel(subTopicName + " - Overview ");
		Label contentText = new Label(bl.getOverviewContent(subTopicID));
		overviewContent.setContent(contentText);
		
		Panel overviewVideo = new Panel("Video");
		Embedded video = createVideo(bl.getOverviewVideo(subTopicID));
		overviewVideo.setContent(video);
		overviewVideo.setWidth("500px");

		addComponent(overviewContent, "left: 0px; top: 0px;");
		addComponent(overviewVideo, "left: 150px; top: 200px;");
	}
	
	private Embedded createVideo(String url) {
		
		Embedded youtubeVideo = new Embedded(null, new ExternalResource(url));
	    youtubeVideo.setMimeType("application/x-shockwave-flash");
	    youtubeVideo.setParameter("allowFullScreen", "true");
	    youtubeVideo.setWidth("500px");
	    youtubeVideo.setHeight("300px");
	    
		return youtubeVideo;
	}
}

