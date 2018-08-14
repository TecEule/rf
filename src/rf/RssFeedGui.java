package rf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class RssFeedGui extends JFrame {

	private JPanel contentPane;

	RssFeedPanel rssNtv;
	RssFeedPanel rssWelt;
	RssFeedPanel rssCnn;
	RssFeedPanel rssKicker;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RssFeedGui frame = new RssFeedGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RssFeedGui() {
		setSize(new Dimension(1503, 428));
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 428);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new Dimension(2500, 500));
		setContentPane(contentPane);
		
		
		rssNtv = new RssFeedPanel();
		rssNtv.setBounds(10, 10, 307, 249);
		rssNtv.setVisible(true);
		add(rssNtv);
		rssNtv.initRss("https://www.n-tv.de/rss");
		
		rssWelt = new RssFeedPanel();
		rssWelt.setBounds(322, 10, 307, 249);
		rssWelt.setVisible(true);
		add(rssWelt);
		rssWelt.initRss("https://www.welt.de/feeds/topnews.rss");
		

		
		rssCnn = new RssFeedPanel();
		rssCnn.setBounds(634, 10, 307, 249);
		rssCnn.setVisible(true);
		add(rssCnn);
		rssCnn.initRss("http://rss.cnn.com/rss/edition_world.rss");
		
		rssKicker = new RssFeedPanel();
		rssKicker.setBounds(946, 10, 307, 249);
		rssKicker.setVisible(true);
		add(rssKicker);
		rssKicker.initRss("http://rss.kicker.de/news/aktuell");

	}

//	private void initRssWelt()
//	{
//		RssFeedParser parser = new RssFeedParser("https://www.welt.de/feeds/topnews.rss");
//		Feed feed = parser.readFeed();
//		RssFeedMessage message= feed.getMessages().get(0);
//		
//		rssWelt.setFeedValues(feed);
//		rssWelt.setRssFeedMessage(message);
//	}
//	
//	private void initRssCnn()
//	{
//		RssFeedParser parser = new RssFeedParser("http://rss.cnn.com/rss/edition_world.rss");
//		Feed feed = parser.readFeed();
//		RssFeedMessage message= feed.getMessages().get(0);
//		
//		rssCnn.setFeedValues(feed);
//		rssCnn.setRssFeedMessage(message);
//	}
//	
//	private void initRssKicker()
//	{
//		RssFeedParser parser = new RssFeedParser("http://rss.kicker.de/news/aktuell");
//		Feed feed = parser.readFeed();
//		RssFeedMessage message= feed.getMessages().get(0);
//		
//		rssKicker.setFeedValues(feed);
//		rssKicker.setRssFeedMessage(message);
//	}
//	
//	RssFeedParser parser = new RssFeedParser("https://www.n-tv.de/rss");
//	//("http://rss.cnn.com/rss/edition_world.rss");
////("http://rss.kicker.de/news/aktuell");
////("https://www.welt.de/feeds/topnews.rss"); 
////"https://www.n-tv.de/rss");
//Feed feed = parser.readFeed();
//
//System.out.println(feed);
//int counter = 0;
//for(RssFeedMessage message:feed.getMessages())
//{
//System.out.println(message);
//if (++counter ==5)
//	break;
//}
}
