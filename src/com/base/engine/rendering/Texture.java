package com.base.engine.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

import java.io.File;
import java.io.FileInputStream;

import org.newdawn.slick.opengl.TextureLoader;

public class Texture 
{
	private int id;
	
	public Texture( String fileName )
	{
		this( loadTexture( fileName ) );
	}
	
	public Texture( int id )
	{
		this.id = id;
	}
	
	public void bind( int samplerSlot )
	{
		assert( samplerSlot >= 0 && samplerSlot <= 31 );
		glActiveTexture(  GL_TEXTURE0 + samplerSlot );
		glBindTexture( GL_TEXTURE_2D, id );
	}
	
	public void bind()
	{
		bind(0);
	}
	
	public int getID()
	{
		return id;
	}
	
	private static int loadTexture( String fileName )
	{
		String[] splitArray = fileName.split("\\.");
		String ext = splitArray[ splitArray.length - 1 ];
		
		try
		{
			// F�rs�ker ladda in texturen av filen
			int id = TextureLoader.getTexture( ext, new FileInputStream( new File( "./res/textures/" + fileName ) ) ).getTextureID();
			
			return id;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return 0;
	}
}
