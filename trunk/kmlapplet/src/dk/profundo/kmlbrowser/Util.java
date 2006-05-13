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
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

	public static void main(String[] strings) {
		System.out.println(repairXml("\n<MENUSELECT item='3'>   "));
	}

	static Rectangle parseRectangle(String coords) {
		String[] c = coords.split(",");
		return new Rectangle(parseInt(c[0]), parseInt(c[1]), parseInt(c[2]),
				parseInt(c[3]));
	}

	static Point parsePoint(String coords) {
		String[] c = coords.split(",");
		return new Point(parseInt(c[0]), parseInt(c[1]));
	}

	private static int parseInt(String x) {
		try {
			return Integer.parseInt(x);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	static Color[] parseGradient(String t) {
		String[] fromto = t.split("-");
		String[] from = fromto[0].split(",");
		String[] to = fromto[1].split(",");
		return new Color[] {
				new Color(parseInt(from[0]), parseInt(from[1]),
						parseInt(from[2])),
				new Color(parseInt(to[0]), parseInt(to[1]), parseInt(to[2])) };
	}

	static Document parseXmlString(String xml) {
		try {
			return parseXmlString(xml, "UTF-8");
		} catch (Exception e) {
			return parseXmlString(xml, "ISO-8859-1");
		}
	}

	private static Document parseXmlString(String xml, String encoding) {
		InputStream in;
		try {

			in = new ByteArrayInputStream(xml.getBytes(encoding));
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
			throw new RuntimeException("loadDocument("+url+") "+e);
		}
		return doc;
	}

	public static Map nameValueMap(Class classObject) {
		HashMap values = new HashMap();
		Field[] fields = classObject.getFields();
		for (int i = 0; i < fields.length; i++) {
			try {
				values.put(fields[i].getName(), new Integer(fields[i]
						.getInt(null)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return values;
	}
}
