package dk.profundo.kmlbrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * KML Applet
 * 
 * Copyright (C) 2006 Erik Martino Hansen
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 */

@SuppressWarnings("serial")
/**
 * interpreter that handles keypresses and loads images, KML documents and
 * displays them
 */
public class KMLInterpreter extends KMLCanvas implements KeyListener {

	public KMLInterpreter() {
		reset();
		this.addKeyListener(this);
	}

	protected void reset() {
		menus.clear();
		// userKeyActions.clear();
		preloadedImages.clear();
		timer.cancel();
		timer = new Timer();
		activeMenu = 0;
	}

	List<KMLActionBlock> menus = new ArrayList<KMLActionBlock>();

	int activeMenu = 0;

	Timer timer = new Timer();

	Map<String, KMLActionBlock> userKeyActions = new HashMap<String, KMLActionBlock>();

	Map<String, Image> preloadedImages = new HashMap<String, Image>();

	private Map<String, BufferedImage> cachedImages = new HashMap<String, BufferedImage>();

	public static void main(String... args) throws Exception {
		JFrame f = new JFrame("KMLBrowser");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		KMLInterpreter cp = new KMLInterpreter();
		f.getContentPane().add(cp, BorderLayout.CENTER);
		f.getContentPane().add(new JLabel("KMLBrowser"), BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);
		f.addKeyListener(cp);
		// cp.setUrl("http://kml.profundo.dk:8888/index.kml");
		cp.setUrl("http://www.radiozendamateur.com/kml/links.htm");
		// cp.setUrl("http://www.radiozendamateur.com/kml/links.htm");
	}

	protected void processElement(Element el) {
		KMLCommand elementName = KMLCommand.valueOf(el.getTagName());

		switch (elementName) {
		case BACKGROUND: {
			String t;
			if ((t = el.getAttribute("href")) != null && t.length() > 0) {
				backgroundimage(Util.loadImage(t));

			} else if ((t = el.getAttribute("vgradient")) != null
					&& t.length() > 0) {
				Color[] grad = Util.parseGradient(t);
				vgradient(grad[0], grad[1]);

			} else if ((t = el.getAttribute("hgradient")) != null
					&& t.length() > 0) {
				Color[] grad = Util.parseGradient(t);
				hgradient(grad[0], grad[1]);

			} else {
				Logger.getAnonymousLogger()
						.warning("unknown background: " + el);
			}
			break;
		}
		case TEXT: {
			String text = Util.innerText(el);
			text(Util.parsePoint(el.getAttribute("coords")), Fonts.getFont(el
					.getAttribute("font")), Palette.getColor(Integer
					.parseInt(el.getAttribute("pen"))), text);
			break;
		}
		/*
		 * case LINE: { box.setColor(GetColor(el.getAttribute("pen"))); Point
		 * start = GetPoint(el.getAttribute("coords")); Point end =
		 * GetEndPoint(el.getAttribute("coords")); this.line()
		 * box.drawLine(start.x, start.y, end.x, end.y); break; }
		 */
		case RECT: {
			Color pen = Palette.getColor(Integer.parseInt(el
					.getAttribute("pen")));
			Rectangle coords = Util.parseRectangle(el.getAttribute("coords"));
			rect(coords, pen);
			break;
		}
		case FILLRECT: {
			Color pen = Palette.getColor(Integer.parseInt(el
					.getAttribute("pen")));
			Rectangle coords = Util.parseRectangle(el.getAttribute("coords"));
			fillrect(coords, pen);
			break;
		}
		case MENUITEM: {
			addMenuItem(el);
			break;
		}
		case ONTIMER: {
			final String code = Util.innerText(el);
			long timeout = (long) Double
					.parseDouble(el.getAttribute("timeout"));
			timer.schedule(new TimerTask() {
				public void run() {
					try {
						processInnerXml(code);
					} catch (Exception e) {
						e.printStackTrace();
						message(e.getMessage());
					}
				}
			}, timeout);
			break;
		}
		case LOADIMAGE: {
			BufferedImage image;
			String src = el.getAttribute("href");
			if ((image = cachedImages.get(src)) == null) {
				image = Util.loadImage(src);
				cachedImages.put(src, image);
			}
			preloadedImages.put(el.getAttribute("id"), image);
			break;
		}
		case IMAGE: {
			Image img = preloadedImages.get(el.getAttribute("id"));
			Point p = Util.parsePoint(el.getAttribute("coords"));
			image(p, img);
			break;
		}
		case GOTO: {
			setUrl(el.getAttribute("href"));
			break;
		}
		case KEY: {
			addKeyAction(el);
			break;
		}

		case MENUSELECT:
			selectMenu(el.getAttribute("item"));
			break;
		default:
			message("unhandled" + elementName);
		}
	}

	/** process xml not enclosed by a root tag */
	protected void processInnerXml(String innerXml) {
		message(innerXml);
		String xml = "<root>" + innerXml + "</root>";
		processDocument(Util.parseXmlString(xml));
	}

	/** process a newly loaded document document */
	protected void process(Document document) {
		// clearAll();
		processDocument(document);
		refreshMenus();
		repaint();
	}

	protected void refreshMenus() {
		for (int i = 0; i < menus.size(); i++) {
			KMLActionBlock menu = menus.get(i);
			if (i == activeMenu) {
				processInnerXml(menu.hilite);
			} else {
				processInnerXml(menu.unhilite);
			}
		}
	}

	protected void processDocument(Document doc) {
		for (Node n : Util.filterElement(Util.asIterable(doc.getFirstChild()
				.getChildNodes()))) {
			processElement((Element) n);
		}
	}

	public void selectMenu(String menuId) {
		KMLActionBlock old = getActiveMenu();

		for (int i = 0; i < menus.size(); i++) {
			if (menus.get(i).id.equals(menuId))
				activeMenu = i;
		}

		KMLActionBlock newMenu = getActiveMenu();
		changeMenu(old, newMenu);
	}

	/**
	 * set and display the KML document at
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		reset();
		process(Util.loadDocument(url));
	}

	protected void message(String string) {
		Logger.getAnonymousLogger().info(string);
	}

	protected void addMenuItem(Element el) {
		String id = el.getAttribute("id");
		NodeList actionItems = el.getChildNodes();
		menus.add(createActionBlock(id, actionItems));
	}

	protected KMLActionBlock createActionBlock(String id, NodeList actionItems) {
		KMLActionBlock actionBlock = new KMLActionBlock();
		actionBlock.id = id;
		for (Element elm : Util.filterElement(Util.asIterable(actionItems))) {
			try {
				KMLAction action = KMLAction.valueOf((elm).getTagName());
				actionBlock.setAction(action, Util.innerText(elm));
			} catch (RuntimeException e) {
				onException(e);
			}
		}
		return actionBlock;
	}

	protected void addKeyAction(Element el) {
		String id = el.getAttribute("id");
		NodeList actionItems = el.getChildNodes();
		KMLActionBlock actionBlock = createActionBlock(id, actionItems);
		userKeyActions.put(id, actionBlock);
	}

	protected void onException(Exception e) {
		Logger.getAnonymousLogger().throwing(getClass().getName(), "", e);
	}

	protected KMLActionBlock getActiveMenu() {
		try {
			if (activeMenu < 0)
				activeMenu += menus.size();
			else if (activeMenu >= menus.size())
				activeMenu -= menus.size();
			return menus.get(activeMenu % menus.size());
		} catch (Exception e) {
			return null;
		}
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent event) {
		try {
			int keyCode = event.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_0:
				onButton(Button.ZERO);
				break;
			case KeyEvent.VK_1:
				onButton(Button.ONE);
				break;
			case KeyEvent.VK_2:
				onButton(Button.TWO);
				break;
			case KeyEvent.VK_3:
				onButton(Button.THREE);
				break;
			case KeyEvent.VK_4:
				onButton(Button.FOUR);
				break;
			case KeyEvent.VK_5:
				onButton(Button.FIVE);
				break;
			case KeyEvent.VK_6:
				onButton(Button.SIX);
				break;
			case KeyEvent.VK_7:
				onButton(Button.SEVEN);
				break;
			case KeyEvent.VK_8:
				onButton(Button.EIGHT);
				break;
			case KeyEvent.VK_9:
				onButton(Button.NINE);
				break;
			case KeyEvent.VK_UP:
				onButton(Button.UP);
				break;
			case KeyEvent.VK_DOWN:
				onButton(Button.DOWN);
				break;
			case KeyEvent.VK_LEFT:
				onButton(Button.LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				onButton(Button.RIGHT);
				break;
			case KeyEvent.VK_BACK_SPACE:
			case KeyEvent.VK_DELETE:
				onButton(Button.RETURN);
				break;
			case KeyEvent.VK_ENTER:
			case KeyEvent.VK_SPACE:
				onButton(Button.PLAY);
				break;
			case KeyEvent.VK_PAGE_UP:
				onButton(Button.PREV);
				break;
			case KeyEvent.VK_PAGE_DOWN:
				onButton(Button.NEXT);
				break;
			default:
				message(event.toString());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	protected void nextMenu() {
		KMLActionBlock oldMenu = getActiveMenu();
		activeMenu++;
		KMLActionBlock newMenu = getActiveMenu();
		changeMenu(oldMenu, newMenu);
	}

	protected void prevMenu() {
		KMLActionBlock oldMenu = getActiveMenu();
		activeMenu--;
		KMLActionBlock newMenu = getActiveMenu();
		changeMenu(oldMenu, newMenu);
	}

	protected void changeMenu(KMLActionBlock oldMenu, KMLActionBlock newMenu) {
		if (oldMenu != null) {
			processInnerXml(oldMenu.unhilite);
		}
		if (newMenu != null) {
			processInnerXml(newMenu.hilite);
		}
		repaint();
	}

	/**
	 * My guess at how this works, you should check this
	 * <ul>
	 * <li> First check against the action blocks in the current menu </li>
	 * <li> Then against registered key events
	 * <li> Then against default prev/next menu events
	 * </ul>
	 * 
	 * @param button
	 * @author xylifyx
	 */
	protected void onButton(Button button) {
		String keyId = button.getId();
		KMLActionBlock menu = getActiveMenu();
		if (menu != null) {
			switch (button) {
			case UP:
				if (menu.up != null) {
					processInnerXml(menu.up);
					return;
				}
				break;
			case DOWN:
				if (menu.down != null) {
					processInnerXml(menu.down);
					return;
				}
				break;
			case LEFT:
				if (menu.left != null) {
					processInnerXml(menu.left);
					return;
				}
				break;
			case RIGHT:
				if (menu.right != null) {
					processInnerXml(menu.right);
					return;
				}
				break;
			case PLAY:
				if (menu.hilite != null) {
					processInnerXml(menu.click);
					return;
				}
			}
		}

		KMLActionBlock code = userKeyActions.get(keyId);
		if (code != null) {
			processInnerXml(code.click);
		} else {
			switch (button) {
			case DOWN:
			case RIGHT:
				nextMenu();
				break;
			case LEFT:
			case UP:
				prevMenu();
				break;
			}
		}
	}
}
