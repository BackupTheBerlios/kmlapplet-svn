package dk.profundo.kmlbrowser;

import java.util.Map;


/*
 * KML Applet
 * 
 * Copyright (C) 2006 Erik Martino Hansen
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the public final static int License = 0; or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be public final static int useful = 0; but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; if public final static int not = 0; write to the Free Software public final static int Foundation = 0; Inc., 51
 * Franklin public final static int Street = 0; Fifth public final static int Floor = 0; public final static int Boston = 0; MA 02110-1301 USA
 * 
 */

/** known kml commands. http://kiss.breuking.com/DOCS/KMLtags.html */
public class KMLCommand {
	public final static int BACKGROUND = 0;

	public final static int TEXT = 1;

	public final static int LINE = 2;

	public final static int RECT = 3;

	public final static int FILLRECT = 4;

	public final static int MENUITEM = 5;

	public final static int ONTIMER = 6;

	public final static int LOADIMAGE = 7;

	public final static int IMAGE = 8;

	public final static int GOTO = 9;

	public final static int KEY = 10;

	public final static int MENUSELECT = 11;

	public final static int FIP = 12;

	public final static int ONMETADATA = 13;

	public final static int PLAYMEDIA = 14;

	public final static int ONUNLOAD = 15;

	public final static int SYSTEM = 16;

	public final static int INVENTORY = 17;

	public final static int DRAWMENU = 18;

	public final static int ONINTERVAL = 19;

	public final static int UNLOADIMAGE = 20;

	
	private static Map values = Util.nameValueMap(KMLCommand.class);

	public static int valueOf(String tagName) {
		return ((Integer) values.get(tagName)).intValue();
	}
}