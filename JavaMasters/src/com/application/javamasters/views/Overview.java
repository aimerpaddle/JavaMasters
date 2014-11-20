package com.application.javamasters.views;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Overview extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private VerticalLayout panelLayout = new VerticalLayout();
	private VerticalLayout linksLayout = new VerticalLayout();
	private Panel linksPanel = new Panel();
	private Link video;
	
	public Overview(String topic, String subtopic, String video) {
		super();

		this.video = new Link("Video Turorial", new ExternalResource(video));
		
		this.setContent(panelLayout);
		panelLayout.addComponent(new Label("Overview"));
		panelLayout.addComponent(linksPanel);
		this.linksPanel.setContent(linksLayout);
		this.linksLayout.addComponent(this.video);
	
		Embedded youtubeVideo = new Embedded(null, new ExternalResource(
	            "https://www.youtube.com/watch?v=jZYpdrXNQMg&list=UUPaQ9GNO5Ivo4al4OKMh9dQ"));
		youtubeVideo.setAlternateText("Posch Video");
	    youtubeVideo.setMimeType("application/x-shockwave-flash");
	    youtubeVideo.setParameter("allowFullScreen", "true");
	    youtubeVideo.setWidth("320px");
	    youtubeVideo.setHeight("265px");
	    linksLayout.addComponent(createVideo("https://www.youtube.com/watch?v=jZYpdrXNQMg&list=UUPaQ9GNO5Ivo4al4OKMh9dQ", "Posch Video"));
	}
	
	private Embedded createVideo(String url, String title) {
		
		Embedded youtubeVideo = new Embedded(null, new ExternalResource(url));
		youtubeVideo.setAlternateText(title);
	    youtubeVideo.setMimeType("application/x-shockwave-flash");
	    youtubeVideo.setParameter("allowFullScreen", "true");
	    youtubeVideo.setWidth("320px");
	    youtubeVideo.setHeight("265px");
	    
		return youtubeVideo;
	}
}

