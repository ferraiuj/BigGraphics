package com.jmf.app;

import com.jmf.app.core.Window;

public class EventStart {
	public static void main(String[] args){
		Window window = new Window("Events", 960, 540);
		window.addLayer(new ExampleLayer());
	}
}
