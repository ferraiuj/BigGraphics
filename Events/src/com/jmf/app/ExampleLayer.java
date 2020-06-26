package com.jmf.app;



import com.jmf.app.events.Event;
import com.jmf.app.events.EventDispatcher;
import com.jmf.app.events.types.MousePressed;
import com.jmf.app.layers.Layer;

public class ExampleLayer extends Layer {

	public void onEvent (Event event){
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> (onMousePressed((MousePressed) e)));
		
		
	}
	
	public boolean onMousePressed(MousePressed e ) {
		System.out.println("Mouse Pressed: " + e.getButton());
		return false;
	}
}
