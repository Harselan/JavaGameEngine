package com.base.engine;

public class MainComponent 
{
	public static final int WIDHT = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "HarCom Game Engine";
	public static final double FRAME_CAP = 5000.0;
	
	private boolean isRunning;
	private Game game;
	
	public MainComponent()
	{
		isRunning = false;
		game = new Game();
	}
	
	public void start()
	{
		if( isRunning )
		{
			return;
		}
		
		run();
	}
	
	public void stop()
	{
		if( !isRunning )
		{
			return;
		}
		
		isRunning = false;
	}
	
	private void run()
	{
		// Säger till att spelet körs nu
		isRunning = true;
		
		// Fixar till fps
		int frames = 0;
		long frameCounter = 0;
		
		// Tiden mellan varje renderad bild
		final double frameTime = 1.0 / FRAME_CAP;
		
		// Tidpunken allting börjar
		long lastTime = Time.getTime();
		// Tid mellan varje renderad bild
		double unprocessedTime = 0;
		
		while( isRunning )
		{
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			// Lägger till tiden som har gått i sekunder till unprocessedTime
			unprocessedTime  += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			// När det är dags för en ny renderad bild
			while( unprocessedTime > frameTime )
			{
				render = true;
				unprocessedTime -= frameTime;
			
				if( Window.isCloseRequested() )
				{
					stop();
				}
				
				Time.setDelta(frameTime);
				Input.update();
				
				game.input();
				game.update();
				
				// När det har gått en sekund
				if( frameCounter >= Time.SECOND )
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			// Kollar om en ny bild behöver renderas in
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
		game.render();
		Window.render();
	}
	
	private void cleanUp()
	{
		Window.dispose();
	}
	
	public static void main(String[] args)
	{
		Window.createWindow(WIDHT, HEIGHT, TITLE);
		
		MainComponent game = new MainComponent();
		
		game.start();
	}
}
