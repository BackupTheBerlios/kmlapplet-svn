package dk.profundo.kmlbrowser.remote;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.util.EventObject;

public class ButtonEvent extends EventObject {

	private long when;

	ButtonEvent(Component source, int buttonCode) {
		this(source, System.currentTimeMillis(), buttonCode);
	}

	ButtonEvent(Component source, long when, int buttonCode) {
		super(source);
		this.when = when;
		this.buttonCode = buttonCode;
	}

	private int buttonCode;

	public int getButtonCode() {
		return buttonCode;
	}
}
