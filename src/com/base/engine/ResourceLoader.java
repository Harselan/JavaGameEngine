package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;

public class ResourceLoader 
{
	public static String loadShader( String fileName )
	{
		// Ska ladda in all text
		StringBuilder shaderSource = new StringBuilder();
		// Är till för att hämta filen senare
		BufferedReader shaderReader = null;
		
		try 
		{	
			// Öppna upp filen
			shaderReader = new BufferedReader(new FileReader("./res/shaders/" + fileName));
		
			String line;
			
			// Loopa igenom varje rad av filen och lägg till den på resultatet
			while( ( line = shaderReader.readLine() ) != null )
			{
				shaderSource.append(line).append("\n");
			}
			
			// Stäng ner filen för sparandet av resurser
			shaderReader.close();
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		// Skicka tillbaka resultatet i strängformat
		return shaderSource.toString();
	}
}
