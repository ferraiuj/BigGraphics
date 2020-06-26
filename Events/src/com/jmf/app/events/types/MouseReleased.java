package com.jmf.app.events.types;

import com.jmf.app.events.Event;

public class MouseReleased extends MouseButtonEvent{
	
	public MouseReleased(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_RELEASED);
		
	}
}
