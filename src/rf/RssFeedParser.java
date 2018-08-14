package rf;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.management.RuntimeErrorException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;

public class RssFeedParser {

	static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String CATEGORY = "category";
    static final String IMGAGE = "image";
    static final String IMG_URL = "url";
    
    final URL url;

    public RssFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Feed readFeed()
    {
    	Feed feed = null;
    	try
    	{
    		Boolean isHeader = true;
    		// Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";
            String category = "";
            String imgurl = "";
    		
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while(eventReader.hasNext())
            {
            	XMLEvent event = eventReader.nextEvent();
            	if (event.isStartElement())
            	{
            		String localPArt = event.asStartElement().getName().getLocalPart();
            		switch(localPArt)
            		{
            		case ITEM:
            			if(isHeader)
            			{
            				isHeader = false;
            				feed = new Feed(title, link, description, language, copyright, imgurl);
            			}
            			event = eventReader.nextEvent();
            			break;
            		case TITLE:
            			title = getCharacterData(event, eventReader);
            			break;
            		case DESCRIPTION:
            			description = getCharacterData(event, eventReader);
            			break;
            		case LINK:
                        link = getCharacterData(event, eventReader);
                        break;
                    case GUID:
                        guid = getCharacterData(event, eventReader);
                        break;
                    case LANGUAGE:
                        language = getCharacterData(event, eventReader);
                        break;
                    case AUTHOR:
                        author = getCharacterData(event, eventReader);
                        break;
                    case PUB_DATE:
                        pubdate = getCharacterData(event, eventReader);
                        break;
                    case COPYRIGHT:
                        copyright = getCharacterData(event, eventReader);
                        break;
                    case CATEGORY:
                    	category = getCharacterData(event, eventReader);
                    	break;
                    case IMG_URL:
                    	imgurl = getCharacterData(event, eventReader);
                    	break;
            		}
            	}
            	else if(event.isEndElement())
            	{
            		if(event.asEndElement().getName().getLocalPart() == (ITEM))
            		{
            			RssFeedMessage message = new RssFeedMessage();
            			message.setAuthor(author);
            			message.setDescription(description);
            			message.setGuid(guid);
            			message.setLink(link);
            			message.setTitle(title);
            			message.setCategory(category);
            			feed.getMessages().add(message);
            			event = eventReader.nextEvent();
            			continue;
            		}
            	}
            }
            
            
    	}
    	catch(XMLStreamException e)
    	{
    		throw new RuntimeException(e);
    	}
    	
    	return feed;
    }
    
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException
    {
    	String result = "";
    	
    	event = eventReader.nextEvent();
    	if (event instanceof Characters)
    	{
    		result = event.asCharacters().getData();
    	}
    	
    	return result;
    }
    
    private InputStream read()
    {
    	try
    	{
    		return url.openStream();
    	}
    	catch(IOException e)
    	{
    		throw new RuntimeException(e);
    	}
    }
    
    
	
}
