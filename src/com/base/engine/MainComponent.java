package com.base.engine;

public class MainComponent 
{
	public static final int WIDHT = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "HarCom Game Engine";
	
	private boolean isRunning;
	
	public MainComponent()
	{
		isRunning = false;
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
		isRunning = true;
		
		while( isRunning )
		{
			if( Window.isCloseRequested() )
			{
				stop();
			}
			
			render();
		}
		
		cleanUp();
	}
	
	private void render()
	{
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
