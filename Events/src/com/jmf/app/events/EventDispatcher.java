package com.jmf.app.events;

import com.jmf.app.events.Event.Type;

public class EventDispatcher {

	private Event event;

	public EventDispatcher(Event event) {
		this.event = event;

	}

	public void dispatch(Event.Type type, EventHandler handler) {
		if (event.handled) 
			return;
		
		if (event.getType() == type) {
			event.handled = handler.onEvent(event);
		}

	}

	
}