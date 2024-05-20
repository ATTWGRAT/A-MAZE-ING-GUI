package AmazeingGui;

import AmazeingGui.CustomActionListeners.CustomActionListener;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.CustomEvent.EventType;

import java.util.ArrayList;
import java.util.HashMap;

public final class CustomEventManager {
    private final static CustomEventManager instance = new CustomEventManager();
    private final HashMap<EventType, ArrayList<CustomActionListener>> registeredListeners;

    private CustomEventManager()
    {
        registeredListeners = new HashMap<>();
    }

    public void registerListener(EventType type, CustomActionListener listener)
    {
        if(!registeredListeners.containsKey(type))
            registeredListeners.put(type, new ArrayList<>());

        registeredListeners.get(type).add(listener);
    }

    public synchronized void callEvent(EventType type, CustomEvent event)
    {
        registeredListeners.get(type).forEach(customActionListener -> customActionListener.call(event));
    }

    public static CustomEventManager getInstance() {
        return instance;
    }
}
