package AmazeingGui.CustomActionListeners;

import AmazeingGui.CustomEvent.CustomEvent;

import java.util.EventListener;

public interface CustomActionListener extends EventListener {
    void call(CustomEvent event);
}
