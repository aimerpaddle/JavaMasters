package com.application.javamasters.views;

import com.application.javamasters.business.BusinessLogic;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class HelpfulLinks extends AbsoluteLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5473842857424711853L;
	private int subTopicID = 0;

	public HelpfulLinks(String subTopicName) {

		setWidth("800px");
		setHeight("100%");

		BusinessLogic buslog = new BusinessLogic();

		subTopicID = buslog.getSubtopicID(subTopicName);

		Panel helpfulLinks = new Panel("Helpful Links");
		Link link1 = new Link(buslog.getHelpfulLink(subTopicID, 1),
				new ExternalResource(buslog.getHelpfulLink(subTopicID, 1)));

		Link link2 = new Link(buslog.getHelpfulLink(subTopicID, 2),
				new ExternalResource(buslog.getHelpfulLink(subTopicID, 2)));
		Link link3 = new Link(buslog.getHelpfulLink(subTopicID, 3),
				new ExternalResource(buslog.getHelpfulLink(subTopicID, 3)));
		VerticalLayout links = new VerticalLayout();
		links.setSpacing(true);

		links.addComponent(link1);
		links.addComponent(link2);
		links.addComponent(link3);

		helpfulLinks.setContent(links);

		Panel video1 = new Panel("Video");
		video1.setWidth("325px");
		Panel video2 = new Panel("Video");
		video2.setWidth("325px");

		Embedded vid1 = createVideo(buslog.getHelpfulVideo(subTopicID, 1));
		Embedded vid2 = createVideo(buslog.getHelpfulVideo(subTopicID, 2));

		video1.setContent(vid1);
		video2.setContent(vid2);

		addComponent(helpfulLinks, "left:0px; top: 0px;");
		addComponent(video1, "left: 50px; top: 150px;");
		addComponent(video2, "right: 50px; top: 150px;");
	}

	private Embedded createVideo(String url) {

		Embedded youtubeVideo = new Embedded(null, new ExternalResource(url));
		youtubeVideo.setMimeType("application/x-shockwave-flash");
		youtubeVideo.setParameter("allowFullScreen", "true");
		youtubeVideo.setWidth("320px");
		youtubeVideo.setHeight("265px");

		return youtubeVideo;
	}

}