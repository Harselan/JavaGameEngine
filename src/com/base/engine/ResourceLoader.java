package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceLoader 
{
	public static String loadShader( String fileName )
	{
		// Ska ladda in all text
		StringBuilder shaderSource = new StringBuilder();
		// �r till f�r att h�mta filen senare
		BufferedReader shaderReader = null;
		
		try 
		{	
			// �ppna upp filen
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
		
			String line;
			
			// Loopa igenom varje rad av filen och l�gg till den p� resultatet
			while( ( line = shaderReader.readLine() ) != null )
			{
				shaderSource.append(line).append("\n");
			}
			
			// St�ng ner filen f�r sparandet av resurser
			shaderReader.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		// Skicka tillbaka resultatet i str�ngformat
		return shaderSource.toString();
	}
}
