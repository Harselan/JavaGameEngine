package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
	
	public static Mesh loadMesh( String fileName )
	{
		String[] splitArray = fileName.split("\\.");
		
		String ext = splitArray[ splitArray.length - 1 ];
		
		if( !ext.equals("obj") )
		{
			System.err.println("Error: File format not supported for mesh data: " + ext);
			new Exception().printStackTrace();
			System.exit(1);
		}
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		
		BufferedReader meshReader = null;
		
		try 
		{	
			// Öppna upp filen
			meshReader = new BufferedReader( new FileReader("./res/models/" + fileName) );
			String line;
			
			// Loopa igenom varje rad av filen och lägg till den på resultatet
			while( ( line = meshReader.readLine() ) != null )
			{
				String[] tokens = line.split( " " );
				tokens = Util.removeEmptyStrings( tokens );
				
				if( tokens.length == 0 || tokens[0].equals("#") )
				{
					continue;
				}
				else if( tokens[0].equals("v") )
				{
					vertices.add( new Vertex( new Vector3f( 
							 Float.valueOf( tokens[1] ),
							 Float.valueOf( tokens[2] ),
							 Float.valueOf( tokens[3] ) 
					 ) ) );
				}
				else if( tokens[0].equals("f") )
				{
					indices.add( Integer.parseInt( tokens[1] ) - 1 );
					indices.add( Integer.parseInt( tokens[2] ) - 1 );
					indices.add( Integer.parseInt( tokens[3] ) - 1 );
				}	
			}
			
			// Stäng ner filen för sparandet av resurser
			meshReader.close();
			
			Mesh res = new Mesh();
			
			Vertex[] vertexData = new Vertex[ vertices.size() ];
			vertices.toArray( vertexData );
			
			Integer[] indexData = new Integer[ indices.size() ];
			indices.toArray( indexData );
			
			res.addVertices( vertexData, Util.toIntArray( indexData ) );
			
			return res;
		}
		catch( Exception e )
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
