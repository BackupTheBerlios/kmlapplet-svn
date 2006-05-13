package dk.profundo.kmlbrowser.remote;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.profundo.kmlbrowser.Button;

public class RemoteDp558 extends JPanel implements ActionListener {
	public static void main(String[] args) {
		JFrame f = new JFrame("Hej");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new RemoteDp558());
		f.pack();
		f.setVisible(true);
	}

	public RemoteDp558() {
		initComponents();
	}

	private void initComponents() {
		GridLayout g = new GridLayout();
		g.setColumns(1);
		g.setRows(0);
		setLayout(g);
		add(new RemoteDp558(this, 1));
		add(new RemoteDp558(this, 2));
		add(new RemoteDp558(this, 3));
		add(new RemoteDp558(this, 4));
		add(new RemoteDp558(this, 5));
	}

	private RemoteDp558(RemoteDp558 parent, int n) {
		switch (n) {
		case 1:
			init_numbers(parent);
			break;
		case 2:
			init_row5(parent);
			break;
		case 3:
			init_dir(parent);
			break;
		case 4:
			init_row910(parent);
			break;
		case 5:
			init_row1113(parent);
		}

	}

	private void init_numbers(RemoteDp558 parent) {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = initConstraints();
		createButton(parent, gridbag, c, 0, 0, "1", Button.ONE);
		createButton(parent, gridbag, c, 1, 0, "2", Button.TWO);
		createButton(parent, gridbag, c, 2, 0, "3", Button.THREE);
		createButton(parent, gridbag, c, 0, 1, "4", Button.FOUR);
		createButton(parent, gridbag, c, 1, 1, "5", Button.FIVE);
		createButton(parent, gridbag, c, 2, 1, "6", Button.SIX);
		createButton(parent, gridbag, c, 0, 2, "7", Button.SEVEN);
		createButton(parent, gridbag, c, 1, 2, "8", Button.EIGHT);
		createButton(parent, gridbag, c, 2, 2, "9", Button.NINE);
		createButton(parent, gridbag, c, 1, 3, "0", Button.ZERO);
	}

	private void init_row5(RemoteDp558 parent) {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = initConstraints();
		createButton(parent, gridbag, c, 0, 0, "Ejct", Button.UNKNOWN);
		createButton(parent, gridbag, c, 1, 0, "Rec", Button.UNKNOWN);
		createButton(parent, gridbag, c, 2, 0, "Del", Button.CLEAR);
		createButton(parent, gridbag, c, 3, 0, "Ret", Button.RETURN);
	}

	private void init_dir(RemoteDp558 parent) {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = initConstraints();
		createButton(parent, gridbag, c, 1, 0, "Up", Button.UP);
		createButton(parent, gridbag, c, 0, 1, "Left", Button.LEFT);
		createButton(parent, gridbag, c, 1, 1, "Play", Button.PLAY);
		createButton(parent, gridbag, c, 2, 1, "Right", Button.RIGHT);
		createButton(parent, gridbag, c, 1, 2, "Down", Button.DOWN);
	}

	private void init_row910(RemoteDp558 parent) {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = initConstraints();
		createButton(parent, gridbag, c, 0, 0, "Prev", Button.PREV);
		createButton(parent, gridbag, c, 1, 0, "Stop", Button.UNKNOWN);
		createButton(parent, gridbag, c, 2, 0, "Next", Button.NEXT);
		createButton(parent, gridbag, c, 0, 1, "Menu", Button.PREV);
		createButton(parent, gridbag, c, 1, 1, "List", Button.UNKNOWN);
		createButton(parent, gridbag, c, 2, 1, "Setp", Button.NEXT);
	}

	private void init_row1113(RemoteDp558 parent) {
		GridBagLayout gridbag = new GridBagLayout();
		setLayout(gridbag);
		GridBagConstraints c = initConstraints();
		createButton(parent, gridbag, c, 0, 0, "Mark", Button.UNKNOWN);
		createButton(parent, gridbag, c, 1, 0, "Audio", Button.AUDIO);
		createButton(parent, gridbag, c, 2, 0, "Title", Button.TITLE);
		createButton(parent, gridbag, c, 3, 0, "Sub", Button.SUBTITLE);

		createButton(parent, gridbag, c, 0, 1, "Input", Button.UNKNOWN);
		createButton(parent, gridbag, c, 1, 1, "TV", Button.UNKNOWN);
		createButton(parent, gridbag, c, 2, 1, "HDD", Button.UNKNOWN);
		createButton(parent, gridbag, c, 3, 1, "Online", Button.UNKNOWN);

		createButton(parent, gridbag, c, 0, 2, "Zoom", Button.ZOOM);
		createButton(parent, gridbag, c, 1, 2, "-", Button.UNKNOWN);
		createButton(parent, gridbag, c, 2, 2, "+", Button.UNKNOWN);
		createButton(parent, gridbag, c, 3, 2, "Store", Button.UNKNOWN);
	}

	private GridBagConstraints initConstraints() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = 1;
		c.gridheight = 1;
		return c;
	}

	private void createButton(RemoteDp558 parent, GridBagLayout gridbag,
			GridBagConstraints c, int gx, int gy, String title, int keyCode) {
		JButton b = new JButton(title);
		c.gridx = gx;
		c.gridy = gy;
		gridbag.setConstraints(b, c);
		b.setName(Integer.toString(keyCode));
		b.addActionListener(parent);
		add(b);
	}

	private ArrayList buttonListeners;

	public void addButtonListener(ButtonListener listener) {
		if (buttonListeners == null)
			buttonListeners = new ArrayList();
		buttonListeners.add(listener);
	}

	public void removeButtonListener(ButtonListener listener) {
		buttonListeners.remove(listener);
	}

	public void actionPerformed(ActionEvent e) {
		JComponent source = (JComponent) e.getSource();
		int buttonCode = Integer.parseInt(source.getName());
		ButtonEvent ev = new ButtonEvent(this, buttonCode);
		if (buttonListeners != null) {
			for (Iterator i = buttonListeners.iterator(); i.hasNext();) {
				ButtonListener l = (ButtonListener) i.next();
				l.buttonPressed(ev);
			}
		}
	}
}
