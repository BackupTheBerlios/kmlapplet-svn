package osd.image;

import dk.profundo.kmlbrowser.*;

public class LoadImage {
	public static void main(String[] args) {
		LoadImage l = new LoadImage();
		l.test();
	}
	
	public void test() {
		Util.loadImage(getClass().getResource("chip.gif").toString());
	}
}
