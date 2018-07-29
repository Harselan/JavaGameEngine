package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderUtil 
{
	public static void clearScreen()
	{
		//TODO: Stencil Buffer
		glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	}
	
	public static void setTextures( boolean enabled )
	{
		if( enabled )
		{
			glEnable( GL_TEXTURE_2D );
		}
		else
		{
			glDisable( GL_TEXTURE_2D );
		}
	}
	
	public static void unbindTextures()
	{
		glBindTexture( GL_TEXTURE_2D, 0 );
	}
	
	public static void setClearColor( Vector3f color )
	{
		glClearColor( color.getX(), color.getY(), color.getZ(), 1.0f );
	}
	
	public static void initGraphics()
	{
		// S�tter s� att sk�rmen rensas till en svart f�rg
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
		
		// S�ger att den fr�mre delen best�ms av GL_CW vilket d� �r i klockans h�ll
		glFrontFace( GL_CW );
		// G�r s� att den bakre renderingen inte skapas f�r den visas inte f�r kameran
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		// F�r programmet att h�lla koll p� hur l�ngt alla renderingar �r fr�n kameran 
		glEnable( GL_DEPTH_TEST );
		
		//TODO: Depth clamp for later
		glEnable( GL_DEPTH_CLAMP );
		// Fixar s� att texturer anv�nds
		glEnable( GL_TEXTURE_2D );
		// Fixar s� att f�rgerna inte blir allt f�r m�rka
		//glEnable( GL_FRAMEBUFFER_SRGB );
	}
	
	public static String getOpenGlVersion()
	{
		return glGetString( GL_VERSION );
	}
}
