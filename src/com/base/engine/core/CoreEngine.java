package com.base.engine.core;

import com.base.engine.rendering.Window;

public class CoreEngine 
{	
	private boolean isRunning;
	private Game game;
	private RenderingEngine renderingEngine;
	private int width;
	private int height;
	private double frameTime;
	
	public CoreEngine(int width, int height, double framerate, Game game)
	{
		this.isRunning = false;
		this.game = game;
		this.width = width;
		this.height = height;
		this.frameTime = 1.0/framerate;
	}

	public void createWindow(String title)
	{
		Window.createWindow(width, height, title);
		this.renderingEngine = new RenderingEngine();
	}

	public void start()
	{
		if(isRunning)
			return;
		
		run();
	}
	
	public void stop()
	{
		if(!isRunning)
			return;
		
		isRunning = false;
	}
	
	private void run()
	{
		// S�ger till att spelet k�rs nu
		isRunning = true;
		
		// Fixar till fps
		int frames = 0;
		long frameCounter = 0;
		
		game.init();
		
		// Tidpunken allting b�rjar
		double lastTime = Time.getTime();
		// Tid mellan varje renderad bild
		double unprocessedTime = 0;
		
		while( isRunning )
		{
			boolean render = false;
			
			double startTime = Time.getTime();
			double passedTime = startTime - lastTime;
			lastTime = startTime;
			
			// L�gger till tiden som har g�tt i sekunder till unprocessedTime
			unprocessedTime  += passedTime;
			frameCounter += passedTime;
			
			// N�r det �r dags f�r en ny renderad bild
			while( unprocessedTime > frameTime )
			{
				render = true;
				unprocessedTime -= frameTime;
			
				if( Window.isCloseRequested() )
				{
					stop();
				}
				
				game.input((float)frameTime);
				renderingEngine.input( (float)frameTime );
				Input.update();
				game.update((float)frameTime);
				
				// N�r det har g�tt en sekund
				if( frameCounter >= 1.0 )
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			// Kollar om en ny bild beh�ver renderas in
			if( render )
			{
				renderingEngine.render( game.getRootObject() );
				Window.render();
				frames++;
			}
			else
			{
				try 
				{
					Thread.sleep(1);
				} 
				catch( InterruptedException e ) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	private void cleanUp()
	{
		Window.dispose();
	}
}
