package rf;

import java.util.ArrayList;

/*
 * Stores an RSS feed
 */
public class Feed {
	
	final String title;
	final String link;
    final String description;
    final String language;
    final String copyright;
    final String ImageUrl;
    
    
    final ArrayList<RssFeedMessage> entries = new ArrayList<>();
    
    public Feed(String title, String link, String description, String language, String copyright, String ImageUrl)
    {
    	this.title = title;
    	this.link= link;
    	this.description = description;
    	this.language = language;
    	this.copyright = copyright;
    	this.ImageUrl = ImageUrl;
    }
    
    public ArrayList<RssFeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getLanguage() {
        return language;
    }

    public String getCopyright() {
        return copyright;
    }

    public String ImageUrl() {
        return ImageUrl;
    }

    @Override
    public String toString() {
        return "Feed [copyright=" + copyright + ", description=" + description
                + ", language=" + language + ", link=" + link + ", ImageUrl="
                + ImageUrl + ", title=" + title + "]";
    }
	

}
