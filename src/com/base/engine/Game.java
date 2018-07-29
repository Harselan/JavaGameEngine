package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game 
{
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Material material;
	private Camera camera;
	
	//Här lägger man till ljus som ska till en riktning som t.ex. ljus från en lampa till en vägg
	PointLight pLight1 = new PointLight( new BaseLight( new Vector3f( 1, 0.5f, 0 ), 0.8f ), new Attenuation( 0, 0, 1 ), new Vector3f( -2, 0, 5f ), 10 );
	PointLight pLight2 = new PointLight( new BaseLight( new Vector3f( 0, 0.5f, 1 ), 0.8f ), new Attenuation( 0, 0, 1 ), new Vector3f( 2, 0, 5f ), 10 );
	PointLight pLight3 = new PointLight( new BaseLight( new Vector3f( 0, 1, 0 ), 0.8f ), new Attenuation( 0, 0, 1 ), new Vector3f( 2, 0, 5f ), 10 );
	
	SpotLight sLight1 = new SpotLight( new PointLight( new BaseLight( new Vector3f( 0, 1f, 1f ), 0.8f ), new Attenuation( 0, 0, 0.1f ), new Vector3f( -2, 0, 5f ), 30 ),
						new Vector3f( 1, 1, 1 ), 0.7f );
	
	public Game()
	{
		mesh   = new Mesh(); //ResourceLoader.loadMesh( "box.obj" );
		material = new Material( ResourceLoader.loadTexture("test.png"), new Vector3f( 1, 1, 1 ), 1, 8 );
		shader = PhongShader.getInstance();
		camera = new Camera();
		transform = new Transform();
		
		/*Vertex[] vertices = new Vertex[] { 
				new Vertex( new Vector3f( -1, -1, 0 ), new Vector2f( 0, 0 ) ),
				new Vertex( new Vector3f( 0, 1, 0 ), new Vector2f( 0.5f, 0 ) ),
				new Vertex( new Vector3f( 1, -1, 0 ), new Vector2f( 1.0f, 0 ) ),
				new Vertex( new Vector3f( 0, -1, 1 ), new Vector2f( 0.5f, 1.0f ) ),
		};
		
		int[] indices = new int[] { 
				3, 1, 0,
				2, 1, 3,
				0, 1, 2,
				0, 2, 3,
		};
		
		Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f(-1.0f, -1.0f, 0.5773f),	new Vector2f(0.0f, 0.0f)),
		        new Vertex( new Vector3f(0.0f, -1.0f, -1.15475f),		new Vector2f(0.5f, 0.0f)),
		        new Vertex( new Vector3f(1.0f, -1.0f, 0.5773f),		new Vector2f(1.0f, 0.0f)),
		        new Vertex( new Vector3f(0.0f, 1.0f, 0.0f),      new Vector2f(0.5f, 1.0f)) };

				int indices[] = { 0, 3, 1,
				1, 3, 2,
				2, 3, 0,
				1, 2, 0 };
		*/
		
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;
		
		Vertex[] vertices = new Vertex[] {
			new Vertex( new Vector3f( -fieldWidth, 0.0f, -fieldDepth ), new Vector2f( 0.0f, 0.0f ) ),
			new Vertex( new Vector3f( -fieldWidth, 0.0f, fieldDepth * 3 ), new Vector2f( 0.0f, 1.0f ) ),
			new Vertex( new Vector3f( fieldWidth * 3, 0.0f, -fieldDepth ), new Vector2f( 1.0f, 0.0f ) ),
			new Vertex( new Vector3f( fieldWidth * 3, 0.0f, fieldDepth * 3 ), new Vector2f( 1.0f, 1.0f ) ),
		};
		
		int indices[] = {
			0, 1, 2,
			2, 1, 3
		};
		
		
		mesh.addVertices( vertices, indices, true );
		
		transform.setProjection( 70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000 );
		transform.setCamera(camera);
		
		PhongShader.setAmbientLight( new Vector3f( 0.1f, 0.1f, 0.1f ) );
		//PhongShader.setDirectionalLight( new DirectionalLight( new BaseLight( new Vector3f( 1, 1, 1 ), 0.8f ), new Vector3f( 1, 1, 1 ) ) );
	
		//PhongShader.setPointLight( new PointLight[] { pLight1, pLight2, pLight3 } );
		PhongShader.setSpotLight( new SpotLight[] { sLight1 } );
	}
	
	public void input()
	{
		camera.input();
		
		//if( Input.getKeyDown(Keyboard.KEY_UP) )
		//{
		//	System.out.println("We have just pressed up!");
		//}
		//
		//if( Input.getKeyUp(Keyboard.KEY_UP) )
		//{
		//	System.out.println("We have just released up!");
		//}
		//
		//if( Input.getMouseDown(1) )
		//{
		//	System.out.println("We have just rightclicked at " + Input.getMousePosition().toString() + "!");
		//}
		//
		//if( Input.getMouseUp(1) )
		//{
		//	System.out.println("We have just released rightclick!");
		//}
	}
	
	float temp = 0.0f;
	
	public void update()
	{
		temp += Time.getDelta();
		
		float sinTemp = (float)Math.sin(temp);
		
		transform.setTranslation( 0, -1, 5 );
		//transform.setRotation( 0, sinTemp * 180, 0 );
		//transform.setScale( 0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp );
		
		pLight1.setPosition( new Vector3f( 3, 0, 3.0f * (float)( Math.sin( temp ) + 1.0/2.0 ) + 10 ) );
		pLight2.setPosition( new Vector3f( 7, 0, 7.0f * (float)( Math.sin( temp ) + 1.0/2.0 ) + 10 ) );
		pLight3.setPosition( new Vector3f( 5, 0, 8.0f * (float)( Math.sin( temp ) + 1.0/2.0 ) + 10 ) );
		
		sLight1.getPointLight().setPosition( camera.getPos() );
		sLight1.setDirection( camera.getForward() );
	}
	
	public void render()
	{
		RenderUtil.setClearColor( Transform.getCamera().getPos().div(2048f).abs() );
		shader.bind();
		shader.updateuniforms( transform.getTransformation(), transform.getProjectedTransformation() , material );
		mesh.draw();
	}
}
