package dk.profundo.kmlbrowser;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

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

/** not sure what the correct sizes are */
public class Fonts {

	private static final int WEIGHT = Font.PLAIN;
	private static final String FONT = "Verdana";

	private static Font createFont(int fontNumber) {
		switch (fontNumber) {
		case 1:
			return new Font(FONT, WEIGHT, 24);
		case 2:
			return new Font(FONT, WEIGHT, 44);
		case 3:
			return new Font(FONT, WEIGHT, 44);
		case 4:
			return new Font("Wingdings", Font.PLAIN, 16);
		default:
			return new Font(FONT, WEIGHT, 16);
		}
	}

	static Map fonts = new HashMap();

	public static Font getFont(int fontNumber) {
		Font f = (Font) fonts.get(new Integer(fontNumber));
		if (f == null) {
			fonts.put(new Integer(fontNumber), f = createFont(fontNumber));
		}
		return f;
	}

	public static Font getFont(String font) {
		return getFont(Integer.parseInt(font));
	}
}
