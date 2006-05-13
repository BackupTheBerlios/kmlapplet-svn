package dk.profundo.kmlbrowser.remote;

import java.awt.event.KeyEvent;
import java.util.EventListener;

public interface ButtonListener extends EventListener {

    /**
     * Invoked when a button has been typed.
     */
    public void buttonPressed(ButtonEvent e);
}
