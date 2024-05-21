package AmazeingGui;

import AmazeingGui.CustomActionListeners.CustomActionListener;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.CustomEvent.EventType;

import java.util.ArrayList;
import java.util.HashMap;

public final class CustomEventManager {
    private static CustomEventManager instance = null;
    private final HashMap<EventType, ArrayList<CustomActionListener>> registeredListeners;

    private CustomEventManager()
    {
        registeredListeners = new HashMap<>();
    }

    public void registerListener(EventType type, CustomActionListener listener)
    {
        if(instance == null)
            throw new RuntimeException("Nie zainicializowano event managera!");

        if(!registeredListeners.containsKey(type))
            registeredListeners.put(type, new ArrayList<>());

        registeredListeners.get(type).add(listener);
    }

    public synchronized void callEvent(EventType type, CustomEvent event)
    {
        if(instance == null)
            throw new RuntimeException("Nie zainicializowano event managera!");

        ArrayList<CustomActionListener> listeners = registeredListeners.get(type);

        if(listeners == null)
            return;

        listeners.forEach(customActionListener -> customActionListener.call(event));
    }

    public static CustomEventManager getInstance() {
        return instance;
    }

    public static void initialize()
    {
        instance = new CustomEventManager();
    }
}
