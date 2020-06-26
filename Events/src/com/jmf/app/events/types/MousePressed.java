package com.jmf.app.events.types;

import com.jmf.app.events.Event;

public class MousePressed extends MouseButtonEvent{

	public MousePressed(int button, int x, int y) {
		super(button, x, y, Event.Type.MOUSE_PRESSED);
		// TODO Auto-generated constructor stub
	}
	

}
