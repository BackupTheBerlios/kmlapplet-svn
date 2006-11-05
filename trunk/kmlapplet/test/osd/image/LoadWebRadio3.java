package osd.image;

import dk.profundo.kmlbrowser.*;

public class LoadWebRadio3 {
	public static void main(String[] args) {
		LoadWebRadio3 l = new LoadWebRadio3();
		l.test();
	}
	
	public void test() {
		Browser f = new Browser();
		f.pack();
		f.setVisible(true);
		//f.setUrl("http://webradio2.kiss-technology.com/webradio3/kml/index.php");
		f.setUrl("http://webradio2.kiss-technology.com:80/webradio3/kml/showcat.php?category=1002&redraw=true");
	}
}
