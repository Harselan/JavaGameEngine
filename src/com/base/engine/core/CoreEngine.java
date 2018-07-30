package com.base.engine.core;

import com.base.engine.rendering.RenderUtil;
import com.base.engine.rendering.Window;

public class CoreEngine 
{	
	private boolean isRunning;
	private Game game;
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

	private void initializeRenderingSystem()
	{
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
	}

	public void createWindow(String title)
	{
		Window.createWindow(width, height, title);
		initializeRenderingSystem();
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
		long lastTime = Time.getTime();
		// Tid mellan varje renderad bild
		double unprocessedTime = 0;
		
		while( isRunning )
		{
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			// L�gger till tiden som har g�tt i sekunder till unprocessedTime
			unprocessedTime  += passedTime / (double)Time.SECOND;
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
				
				Time.setDelta(frameTime);
				
				game.input();
				Input.update();
				game.update();
				
				// N�r det har g�tt en sekund
				if( frameCounter >= Time.SECOND )
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			// Kollar om en ny bild beh�ver renderas in
			if( render )
			{
				render();	
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
	
	private void render()
	{
		RenderUtil.clearScreen();
		//long startTime = Time.getTime();
		game.render();
		//double millisecond = 1000 * (double)(Time.getTime() - startTime)/((double)Time.SECOND);
		//System.out.println(millisecond + "ms (" + (int)(1.0/(millisecond / 1000)) + " fps)");
		Window.render();

	}
	
	private void cleanUp()
	{
		Window.dispose();
	}
}
