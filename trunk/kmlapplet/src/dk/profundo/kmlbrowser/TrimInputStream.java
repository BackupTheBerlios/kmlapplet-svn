package dk.profundo.kmlbrowser;

import java.io.IOException;
import java.io.InputStream;

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

public class TrimInputStream extends InputStream {
	private InputStream in;

	public TrimInputStream(InputStream in) {
		this.in = in;
	}

	boolean inStart = true;

	@Override
	public int read() throws IOException {
		if (inStart) {
			int c;
			do {
				c = in.read();
			} while (Character.isWhitespace(c) && c > 0);
			inStart = false;
			return c;
		} else
			return in.read();
	}

	@Override
	public void close() throws IOException {
		in.close();
		super.close();
	}

	@Override
	public long skip(long n) throws IOException {
		// TODO Auto-generated method stub
		return in.skip(n);
	}
}
