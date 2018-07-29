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
		// Sätter så att skärmen rensas till en svart färg
		glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
		
		// Säger att den främre delen bestäms av GL_CW vilket då är i klockans håll
		glFrontFace( GL_CW );
		// Gör så att den bakre renderingen inte skapas för den visas inte för kameran
		glCullFace( GL_BACK );
		glEnable( GL_CULL_FACE );
		// Får programmet att hålla koll på hur långt alla renderingar är från kameran 
		glEnable( GL_DEPTH_TEST );
		
		//TODO: Depth clamp for later
		glEnable( GL_DEPTH_CLAMP );
		// Fixar så att texturer används
		glEnable( GL_TEXTURE_2D );
		// Fixar så att färgerna inte blir allt för mörka
		//glEnable( GL_FRAMEBUFFER_SRGB );
	}
	
	public static String getOpenGlVersion()
	{
		return glGetString( GL_VERSION );
	}
}
