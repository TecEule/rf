package rf;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JTextArea;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class RssFeedPanel extends JPanel {
	JTextArea textArea_Description;
	JLabel lblImage;
	private JTextArea textAreaTitle;
	JLabel lblCategory;

	private ArrayList<RssFeedMessage> feedList;
	private static int FeedIndex = 0;
	private static String RssFeedUrl = "";
	private static int AnzahlNachrichten = 5;

	/**
	 * Create the panel.
	 */
	public RssFeedPanel() {
		setBackground(Color.BLACK);
		setLayout(null);

		textAreaTitle = new JTextArea();
		textAreaTitle.setBorder(new MatteBorder(2, 0, 2, 0, (Color) Color.WHITE));
		textAreaTitle.setBackground(Color.BLACK);
		textAreaTitle.setFont(new Font("Arial", Font.BOLD, 11));
		textAreaTitle.setBounds(7, 53, 297, 47);
		textAreaTitle.setLineWrap(true);
		textAreaTitle.setWrapStyleWord(true);
		textAreaTitle.setForeground(Color.WHITE);
		add(textAreaTitle);

		lblImage = new JLabel("Image");
		lblImage.setBounds(209, 4, 95, 40);
		add(lblImage);

		textArea_Description = new JTextArea();
		textArea_Description.setBackground(Color.BLACK);
		textArea_Description.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea_Description.setBounds(7, 111, 297, 166);
		textArea_Description.setLineWrap(true);
		textArea_Description.setWrapStyleWord(true);
		textArea_Description.setForeground(Color.WHITE);
		add(textArea_Description);

		lblCategory = new JLabel("New label", JLabel.LEFT);
		lblCategory.setBackground(Color.BLACK);
		lblCategory.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategory.setForeground(Color.WHITE);
		lblCategory.setBounds(7, 7, 168, 35);
		add(lblCategory);


		Thread wechsel = new Thread(new Runnable() {
			@Override
			public void run() {
				int index = 0;
				while (true) {

					try {
												
						setRssFeedMessageThread(index);

						Thread.sleep(5000);
						
						index++;
						
						if (index >= AnzahlNachrichten)
							index = 0;
						System.out.println("Index: " + index + " FeedIndex : " + FeedIndex);
						
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}

		});

		wechsel.start();
	}

	public void initRss(String rssFeedUrl) {
		RssFeedParser parser = new RssFeedParser(rssFeedUrl);
		Feed feed = parser.readFeed();
		setFeedValues(feed);
		fillFeedList(feed);
//		rssNtv.setRssFeedMessage(message);
	}

	private void setFeedValues(Feed feed) {
		Image img = null;
		try {
			URL url = new URL(feed.ImageUrl);
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(
				img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_DEFAULT));

		lblImage.setIcon(icon);
	}

	private void setRssFeedMessage(RssFeedMessage message, int index) {
		textAreaTitle.setText(message.title);
		textArea_Description.setText(message.description);
		lblCategory.setText(String.valueOf(++index) + "/" + AnzahlNachrichten + " " + message.getCategory());
		FeedIndex++;
	}

	private void fillFeedList(Feed feed) {
		
		feedList = new ArrayList<>();		
		FeedIndex = 0;
		int counter = 0;
		feedList.clear();
		for (RssFeedMessage message : feed.getMessages()) {
			feedList.add(message);
			if (counter++ == AnzahlNachrichten)
				break;
		}

		RssFeedMessage msg = feedList.get(FeedIndex);
		setRssFeedMessageThread(0);
	}

	private void setRssFeedMessageThread(int index) {

		RssFeedMessage message = null;
		if (feedList != null) {
			if (FeedIndex < feedList.size())
				message = feedList.get(index);

			if (message != null) {
				setRssFeedMessage(message, index);
			}

			if (FeedIndex > AnzahlNachrichten)
				FeedIndex = 0;
		}
	}

}
