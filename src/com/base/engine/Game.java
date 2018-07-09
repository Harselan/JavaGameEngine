package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game 
{
	public Game()
	{
		
	}
	
	public void input()
	{
		if( Input.getKeyDown(Keyboard.KEY_UP) )
		{
			System.out.println("We have just pressed up!");
		}
		
		if( Input.getKeyUp(Keyboard.KEY_UP) )
		{
			System.out.println("We have just released up!");
		}
		
		if( Input.getMouseDown(1) )
		{
			System.out.println("We have just rightclicked at " + Input.getMousePosition().toString() + "!");
		}
		
		if( Input.getMouseUp(1) )
		{
			System.out.println("We have just released rightclick!");
		}
	}
	
	public void update()
	{
		
	}
	
	public void render()
	{
		
	}
}
