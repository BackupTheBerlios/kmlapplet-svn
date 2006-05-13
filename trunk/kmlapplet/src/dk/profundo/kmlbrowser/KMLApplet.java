package dk.profundo.kmlbrowser;

import java.awt.BorderLayout;

import javax.swing.JApplet;

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

public class KMLApplet extends JApplet {
	KMLInterpreter interpreter;// = new KMLInterpreter();

	private String url;

	public KMLApplet() {
		setLayout(new BorderLayout());

	}

	public void init() {
		super.init();
		url = this.getParameter("url");
		interpreter = new KMLInterpreter();
		getContentPane().add(interpreter, BorderLayout.CENTER);
		addKeyListener(interpreter);
	}

	public void start() {
		super.start();
		if (url != null && interpreter != null)
			interpreter.setUrl(url);
		repaint();
	}
}
