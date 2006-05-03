package dk.profundo.kmlbrowser;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;

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
/** misc static utility methods and classes */
public class Util {

	public static Iterable<Node> asIterable(final NodeList childNodes) {
		return new Iterable<Node>() {
			public Iterator<Node> iterator() {
				return new Iterator<Node>() {
					int i = 0;

					int length = childNodes.getLength();

					public boolean hasNext() {
						return i < length;
					}

					public Node next() {
						return childNodes.item(i++);
					}

					public void remove() {
						throw new RuntimeException();
					}
				};
			}
		};
	}

	public static Iterable<Element> filterElement(final Iterable<Node> nodes) {
		return new Iterable<Element>() {
			public Iterator<Element> iterator() {
				final Iterator<Node> j = nodes.iterator();
				return new Iterator<Element>() {
					Element next;

					public boolean hasNext() {
						while (j.hasNext()) {
							Node n = j.next();
							if (n instanceof Element) {
								next = (Element) n;
								return true;
							}
						}
						return false;
					}

					public Element next() {
						return next;
					}

					public void remove() {
						throw new RuntimeException();
					}
				};
			}
		};
	}

	public static String innerText(Element el) {
		StringBuilder b = new StringBuilder();
		NodeList childNodes = el.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node n = childNodes.item(i);
			if (n.getNodeType() == Node.TEXT_NODE
					|| n.getNodeType() == Node.CDATA_SECTION_NODE) {
				b.append((String) n.getNodeValue());
			}
		}
		return repairXml(b.toString());
	}

	public static String repairXml(String s) {
		s = s.replaceAll("^\\s*<([^>]*[^>/])>\\s*$", "<$1/>");
		return s;
	}

	public static void main(String... strings) {
		System.out.println(repairXml("\n<MENUSELECT item='3'>   "));
	}

	static Rectangle parseRectangle(String coords) {
		String[] c = coords.split(",");
		return new Rectangle(Integer.parseInt(c[0]), Integer.parseInt(c[1]),
				Integer.parseInt(c[2]), Integer.parseInt(c[3]));
	}

	static Point parsePoint(String coords) {
		String[] c = coords.split(",");
		return new Point(Integer.parseInt(c[0]), Integer.parseInt(c[1]));
	}

	static Color[] parseGradient(String t) {
		String[] fromto = t.split("-");
		String[] from = fromto[0].split(",");
		String[] to = fromto[1].split(",");
		return new Color[] {
				new Color(Integer.parseInt(from[0]), Integer.parseInt(from[1]),
						Integer.parseInt(from[2])),
				new Color(Integer.parseInt(to[0]), Integer.parseInt(to[1]),
						Integer.parseInt(to[2])) };

	}

	static Document parseXmlString(String xml) {
		InputStream in;
		try {
			in = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return Util.parseDocument(in);
	}

	static Document parseDocument(InputStream in) {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected static BufferedImage loadImage(String url) {
		try {
			BufferedImage img = ImageIO.read(new URL(url));
			ColorModel colorModel = img.getColorModel();
			if (colorModel instanceof IndexColorModel) {
				Raster data = img.getData();
				if (data instanceof WritableRaster)
					return new BufferedImage(new Palette(),
							(WritableRaster) data, false, null);
			}
			return img;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Document loadDocument(String url) {
		Document doc;
		try {
			InputStream in = new TrimInputStream(new java.net.URL(url)
					.openStream());
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return doc;
	}
}
