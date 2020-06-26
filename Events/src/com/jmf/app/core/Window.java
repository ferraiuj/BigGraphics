package com.jmf.app.core;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.jmf.app.events.Event;
import com.jmf.app.events.types.MouseMoved;
import com.jmf.app.events.types.MousePressed;
import com.jmf.app.events.types.MouseReleased;
import com.jmf.app.layers.Layer;

public class Window extends JFrame  {

	private static final long serialVersionUID = 1L;

	private Screen screen;
	
	private List<Layer> layerStack = new ArrayList<Layer>();
	public Window (String name, int width, int height ){
		
		screen = new Screen (width, height);
		
		setTitle(name);
		add(screen);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		screen.addMouseListener(new MouseAdapter(){
			
			public void mouseReleased(MouseEvent e ){
				MouseReleased event = new MouseReleased(e.getButton(), e.getX(), e.getY());
					onEvent(event);
			}
			public void mousePressed(MouseEvent e ){
				MousePressed event = new MousePressed(e.getButton(), e.getX(), e.getY());
				onEvent(event);
			}
		});
		
		screen.addMouseMotionListener(new MouseMotionListener(){
			
			public void mouseMoved(MouseEvent e){
				
				MouseMoved event = new MouseMoved( e.getX(), e.getY(), false);
				onEvent(event);
			}
			public void mouseDragged(MouseEvent e){
				MouseMoved event = new MouseMoved(e.getX(), e.getY(), true);
				onEvent(event);
			}
		});Runnable doHelloWorld = new Runnable() {
	     public void run() {
	         System.out.println("Hello World on " + Thread.currentThread());
	     }
	 };

	 SwingUtilities.invokeLater(doHelloWorld);
	 System.out.println("This might well be displayed before the other message.");
		
		run();
	}
	
	
	private void run(){
		screen.init();
		screen.beginRendering();
		screen.clear();
		onRender(screen.getGraphicsObject());
		screen.endRendering();
		try {
			Thread.sleep(3);
		}catch(InterruptedException e){
			
		}
		SwingUtilities.invokeLater(() -> run());
	}
	public void onEvent(Event event){
		
		for(int i = layerStack.size() - 1; i >= 0; i--)
			layerStack.get(i).onEvent(event);
	}
	
	private void onRender(Graphics g){
		for(int i = 0; i < layerStack.size(); i++)
			layerStack.get(i).onRender(g);
	}
	
	public void addLayer(Layer layer){
		System.out.println(layer);
		layerStack.add(layer);
	}
}
