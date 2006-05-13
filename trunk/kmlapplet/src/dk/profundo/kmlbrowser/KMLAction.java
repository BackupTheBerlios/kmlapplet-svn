package dk.profundo.kmlbrowser;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/*
 * KML Applet
 * 
 * Copyright (C) 2006 Erik Martino Hansen
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General public final  License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General public final  License for more
 * details.
 * 
 * You should have received a copy of the GNU General public final  License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * 
 */
public final class KMLAction {
	public final static int ONCLICK = 0;

	public final static int ONHILITE = 1;

	public final static int ONUNHILITE = 2;

	public final static int ONLEFT = 3;

	public final static int ONRIGHT = 4;

	public final static int ONUP = 5;

	public final static int ONDOWN = 6;

	private static HashMap nameValueMap(Class classObject) {
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

	static Map values = nameValueMap(KMLAction.class);

	public static int valueOf(String tagName) {
		return ((Integer) values.get(tagName)).intValue();
	}
}