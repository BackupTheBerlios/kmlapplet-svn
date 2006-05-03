package dk.profundo.kmlbrowser;

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

/**
 * <p>
 * Remote control button codes.
 * </p>
 * <p>
 * Source: KML2 Programmers Manual, Appendix B
 * </p>
 */
public enum Button {
	// "Button Code Name","Button Title","Scancode (Decimal)","Scancode (Hex)"),
	ONE("1", 74, 0x004A), TWO("2", 75, 0x004B), //
	THREE("3", 76, 0x004C), FOUR("4", 77, 0x004D), FIVE("5", 79, 0x004F), //
	SIX("6", 80, 0x0050), SEVEN("7", 81, 0x0051), EIGHT("8", 82, 0x0052), //
	NINE("9", 83, 0x0053), ZERO("0", 73 /* 73 or 78 */, 0x0049), // 
	TITLE("Title Menu", 84, 0x0054), MENU("Root Menu", 65, 0x0041), // 
	RETURN("Return", 93, 0x005D), UP("Up", 67, 0x0043), DOWN("Down", 68, 0x0044), // 
	LEFT("Left", 69, 0x0045), RIGHT("Right", 70, 0x0046), // 
	ENTER("Enter", 71 /* 71 or 115 */, 0x0047), REV("Fast Reverse", 94, 0x005E), //
	PLAY("Play/Pause", 16387, 0x4003), FWD("Fast Forward", 95, 0x005F), //
	PREV("Previous", 16389 /* 3 or 16389 */, 0x4005), //
	NEXT("Next", 16388 /* 2 or 16388 */, 0x4004), // 
	STEP("Single Step", 96, 0x0060), AUDIO("Audio", 97, 0x0061), //
	SUBTITLE("Subtitle", 98, 0x0062), ANGLE("Angle", 100, 0x0064), //
	ZOOM("Zoom", 99, 0x0063), REPEAT("Repeat", 101, 0x0065), //
	BOOKMARK("Bookmark", 102, 0x0066), SETUP("Setup", 768, 0x0300), //
	CLEAR("Clear", 103, 0x0067), SEARCH("Search", 104, 0x0068), UNKNOWN(
			"Unknown key", 0, 0x0000);
	private String text;

	private int scancode;

	private static Map<Integer, Button> scancodes;

	Button(String text, int code, int hexCode) {
		this.text = text;
		this.scancode = hexCode;
	}

	static {
		initscancodes();
	}

	private static void initscancodes() {
		scancodes = new HashMap<Integer, Button>();
		for (Button b : values()) {
			scancodes.put(b.scancode, b);
		}
	}

	/**
	 * scancodes are used with the KEY action event
	 * 
	 * @return scancode
	 */
	public int getScancode() {
		return scancode;
	}

	/** retreive button object from scancode */
	public static Button fromScancode(int scancode) {
		return scancodes.get(scancode);
	}

	public String getText() {
		return text;
	}

	public String getId() {
		return Integer.toString(getScancode());
	}
}
