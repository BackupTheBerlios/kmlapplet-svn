package dk.profundo.kmlbrowser;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
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

/**
 * The KiSS EM85xx hardware has two video buffers; one for overlay graphics (the
 * OSD – On Screen Display buffer) and one for video/background art (YUV, or
 * video buffer). The OSD buffer consists of a 720x480 pixel 8-bit bitmap
 * spanning the entire scan area of the display. Each pixel may have one of 256
 * colors. Each of the 256 colors are index-mapped to a set of corresponding RGB
 * 8-bit values and a 4-bit alpha (opacity) value, which is used to control OSD
 * layer pixel transparency (zero alpha allows unobscured see-thru to the video
 * layer beneath). A ‘blank’ OSD buffer is simply filled with color zero, which
 * has 0% alpha opacity, making it effectively invisible.
 * 
 * @author ermh
 * 
 */
@SuppressWarnings("serial")
public class EM85xx extends JComponent {
	public static final int HEIGHT = 512; // 480;

	public static final int WIDTH = 720;

	Image yuv;

	Image osd;

	public EM85xx() {
		setSize(WIDTH, HEIGHT);
		yuv = createYuvBuffer();
		osd = createOsdBuffer();
		clearYuv();
		clearOsd();
		setDoubleBuffered(true);
	}

	private Image createOsdBuffer() {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		return img;
	}

	private Image createYuvBuffer() {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		return img;
	}

	public void clearYuv() {
		Graphics2D g = getYuvGraphics();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
	}

	public void clearOsd() {
		Graphics2D g = getOsdGraphics();
		AlphaComposite i = AlphaComposite.getInstance(AlphaComposite.SRC);
		g.setComposite(i);
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.dispose();
	}

	public void clearAll() {
		clearOsd();
	}

	@Override
	public Dimension getMinimumSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(yuv, 0, 0, this);
		g.drawImage(osd, 0, 0, this);
	}

	/** video/background layer */
	public Graphics2D getYuvGraphics() {
		return (Graphics2D) yuv.getGraphics();
	}

	/** on screen display layer */
	public Graphics2D getOsdGraphics() {
		Graphics2D g = (Graphics2D) osd.getGraphics();
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
		return g;
	}

	public static void main(String... args) throws Exception {
		JFrame f = new JFrame("KMLBrowser");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		EM85xx cp = new EM85xx();
		f.getContentPane().add(cp, BorderLayout.CENTER);
		f.getContentPane().add(new JLabel(cp.getClass().getName()),
				BorderLayout.NORTH);
		f.pack();
		f.setVisible(true);
	}
}
