package com.jmf.app.events.types;

import com.jmf.app.events.Event;

public class MouseMoved extends Event {

	private int x , y;
	private boolean dragged;
	

	public MouseMoved(int x, int y, boolean dragged) {
		super(Event.Type.MOUSE_MOVED);
		this.x = x;
		this.y = y;
		this.dragged = dragged;
		
		// TODO Auto-generated constructor stub
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public boolean getDragged() {
		return dragged;
	}
	

}
