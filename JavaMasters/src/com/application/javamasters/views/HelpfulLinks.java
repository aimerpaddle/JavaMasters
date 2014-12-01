package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HelpfulLinks extends Panel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private VerticalLayout panelLayout = new VerticalLayout();
	private VerticalLayout linksLayout = new VerticalLayout();
	private HorizontalLayout videoLayout = new HorizontalLayout();
	private Panel linksPanel = new Panel();
	private Panel videoPanel = new Panel();
	private Link link1;
	private Link link2;
	private Link video1;
	private Link video2;
	
	public HelpfulLinks(String link1, String link2, String videoUrl1, String videoUrl2) {
		
		
		super();
		
		BusinessLogic buslog = new BusinessLogic();

		this.link1 = new Link(link1, new ExternalResource(link1));
		this.link2 = new Link(link2, new ExternalResource(link2));
		this.video1 = new Link("Video Turorial 1", new ExternalResource(videoUrl1));
		this.video2 = new Link("Video Turorial 2", new ExternalResource(videoUrl2));
		
		this.link1.setTargetName("_blank");
		this.link2.setTargetName("_blank");
		this.video1.setTargetName("_blank");
		this.video2.setTargetName("_blank");
		
		this.setContent(panelLayout);
		//panelLayout.addComponent(new Label(buslog.getChallengeId(32, "Question1")));
		panelLayout.addComponent(new Label(buslog.getQuestionText(4)));
		//panelLayout.addComponent(new Label("Helpful Links"));
		panelLayout.addComponent(linksPanel);
		linksPanel.setContent(linksLayout);
		linksLayout.addComponent(this.link1);
		linksLayout.addComponent(this.link2);
		linksLayout.addComponent(this.video1);
		linksLayout.addComponent(this.video2);
	
		panelLayout.addComponent(videoPanel);
		videoPanel.setContent(videoLayout);
		
		
		
//		Embedded youtubeVideo = new Embedded(null, new ExternalResource(
//	            "https://www.youtube.com/v/M-pXDoe5a0E"));
//		youtubeVideo.setAlternateText("Sharknado");
//	    youtubeVideo.setMimeType("application/x-shockwave-flash");
//	    youtubeVideo.setParameter("allowFullScreen", "true");
//	    youtubeVideo.setWidth("320px");
//	    youtubeVideo.setHeight("265px");
	    videoLayout.addComponent(createVideo("https://www.youtube.com/v/M-pXDoe5a0E", "Sharknado Trailer"));
	    
//	    Embedded youtubeVideo2 = new Embedded(null, new ExternalResource(
//	            "https://www.youtube.com/v/M-pXDoe5a0E"));
//		youtubeVideo2.setAlternateText("Sharknado");
//	    youtubeVideo2.setMimeType("application/x-shockwave-flash");
//	    youtubeVideo2.setParameter("allowFullScreen", "true");
//	    youtubeVideo2.setWidth("320px");
//	    youtubeVideo2.setHeight("265px");
	    videoLayout.addComponent(createVideo("https://www.youtube.com/v/M-pXDoe5a0E", "Sharknado Trailer"));
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