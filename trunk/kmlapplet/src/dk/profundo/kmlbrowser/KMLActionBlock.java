package dk.profundo.kmlbrowser;

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

public class KMLActionBlock {
	String id, click, hilite, unhilite, left, right, up, down;

	public void setAction(int actionName, String value) {
		switch (actionName) {
		case KMLAction.ONCLICK:
			this.click = value;
			break;
		case KMLAction.ONHILITE:
			this.hilite = value;
			break;
		case KMLAction.ONUNHILITE:
			this.unhilite = value;
			break;
		case KMLAction.ONLEFT:
			this.left = value;
			break;
		case KMLAction.ONRIGHT:
			this.right = value;
			break;
		case KMLAction.ONUP:
			this.up = value;
			break;
		case KMLAction.ONDOWN:
			this.down = value;
			break;
		}
	}
}