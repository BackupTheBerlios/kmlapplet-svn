package dk.profundo.kmlbrowser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Standalone browser */
public class Browser extends JFrame {
	KMLInterpreter browser = new KMLInterpreter();

	JTextField url = new JTextField();

	JButton button = new JButton("Go");

	public Browser() {
		initComponents();
		pack();
	}

	private void initComponents() {
		setLayout(new BorderLayout());
		add(browser, BorderLayout.CENTER);
		JPanel topline = new JPanel();
		add(topline, BorderLayout.NORTH);
		topline.setLayout(new BorderLayout());

		topline.add(url, BorderLayout.CENTER);

		topline.add(button, BorderLayout.EAST);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Browser.this.onGoAction();
			}
		});

		this.addKeyListener(browser);
		browser.addKeyListener(browser);
		button.addKeyListener(browser);
	}

	protected void onGoAction() {
		browser.setUrl(url.getText());
	}

	public static void main(String... args) {
		Browser b = new Browser();
		b.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		b.setVisible(true);
	}
}
