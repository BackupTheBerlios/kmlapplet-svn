package dk.profundo.kmlbrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

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

public class KMLCanvas extends EM85xx {
	/** <code>&lt;BACKGROUND vgradient="0,0,0-0,0,128" /></code> */
	public void vgradient(Color from, Color to) {
		Graphics2D g = getYuvGraphics();
		GradientPaint gradient = new GradientPaint(0, 0, from, 0,
				EM85xx.HEIGHT, to);
		g.setPaint(gradient);
		g.fillRect(0, 0, EM85xx.WIDTH, EM85xx.HEIGHT);
		g.dispose();
	}

	/** <code>&lt;BACKGROUND hgradient="0,0,0-0,0,128" /></code> */
	public void hgradient(Color from, Color to) {
		Graphics2D g = getYuvGraphics();
		GradientPaint gradient = new GradientPaint(0, 0, from, EM85xx.WIDTH, 0,
				to);
		g.setPaint(gradient);
		g.fillRect(0, 0, EM85xx.WIDTH, EM85xx.HEIGHT);
		g.dispose();
	}

	/** <code>&lt;FILLRECT coords="x,y,w,h" pen="color" /></code> */
	public void fillrect(Rectangle coords, Color pen) {
		Graphics2D g = getOsdGraphics();
		g.setColor(pen);
		g.fillRect(coords.x, coords.y, coords.width, coords.height);
		g.dispose();
	}

	/** <code>&lt;FILLRECT coords="x,y,w,h" pen="color" /></code> */
	public void rect(Rectangle coords, Color pen) {
		Graphics2D g = getOsdGraphics();
		g.setColor(pen);
		g.drawRect(coords.x, coords.y, coords.width, coords.height);
		g.dispose();
	}

	/** <code>&lt;IMAGE coords='x,y' src='name' /></code> */
	public void image(Point coords, Image src) {
		Graphics2D g = getOsdGraphics();
		g.drawImage(src, coords.x, coords.y, this);
		g.dispose();
	}

	/** <code>&lt;IMAGE coords='x,y' src='name' /></code> */
	public void backgroundimage(Image src) {
		Graphics2D g = getYuvGraphics();
		g.drawImage(src, 0, 0, this);
		g.dispose();
	}

	/** <code>&lt;TEXT coords='x,y' [font='(0-3)’] [pen='color']></code> */
	public void text(Point coords, Font font, Color pen, String text) {
		Graphics2D g = getOsdGraphics();
		g.setColor(pen);
		g.setFont(font);
		FontMetrics m = g.getFontMetrics();
		int baselineOffset = m.getAscent() + m.getLeading();
		g.drawString(text, coords.x, coords.y + baselineOffset);
		g.dispose();
	}

	public static void main(String[] args) throws Exception {
		JFrame f = new JFrame("KMLBrowser");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		KMLCanvas c = new KMLCanvas();
		f.getContentPane().add(c, BorderLayout.CENTER);
		f.getContentPane().add(new JLabel(c.getClass().getName()),
				BorderLayout.NORTH);
		f.pack();

		/*
		 * <BACKGROUND vgradient="146,208,250-11,47,72" /> <FILLRECT
		 * coords="0,0,719,479" pen="0" /> <FILLRECT coords="0,0,719,56"
		 * pen="250" />
		 * 
		 * <FIP speed="2" intensity="6">Profundo RSS Viewer</FIP>
		 * 
		 * <TEXT coords="75,1" font="3" pen="16">Profundo RSS Viewer</TEXT>
		 * <TEXT coords="74,0" font="3" pen="255">Profundo RSS Viewer</TEXT>
		 */
		c.vgradient(new Color(146, 208, 250), new Color(11, 47, 72));
		c.fillrect(new Rectangle(0, 0, 719, 479), Palette.getColor(0));
		c.fillrect(new Rectangle(0, 0, 719, 56), Palette.getColor(250));
		c.text(new Point(75, 1), Fonts.getFont(4), Palette.getColor(16),
				"Profundo RSS Viewer");
		c.text(new Point(74, 0), Fonts.getFont(4), Palette.getColor(255),
				"Profundo RSS Viewer");

		f.setVisible(true);
	}

}
