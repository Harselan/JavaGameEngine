package com.base.game;

import com.base.engine.components.Camera;
import com.base.engine.components.DirectionalLight;
import com.base.engine.components.MeshRenderer;
import com.base.engine.components.PointLight;
import com.base.engine.components.SpotLight;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public void init()
	{
		float fieldDepth = 10.0f;
		float fieldWidth = 10.0f;

		Vertex[] vertices = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))};

		int indices[] = { 0, 1, 2,
				2, 1, 3};
		
		Vertex[] vertices2 = new Vertex[] { 	new Vertex( new Vector3f(-fieldWidth / 10, 0.0f, -fieldDepth / 10), new Vector2f(0.0f, 0.0f)),
				new Vertex( new Vector3f(-fieldWidth / 10, 0.0f, fieldDepth / 10 * 3), new Vector2f(0.0f, 1.0f)),
				new Vertex( new Vector3f(fieldWidth / 10 * 3, 0.0f, -fieldDepth / 10), new Vector2f(1.0f, 0.0f)),
				new Vertex( new Vector3f(fieldWidth / 10 * 3, 0.0f, fieldDepth / 10 * 3), new Vector2f(1.0f, 1.0f))};

		int indices2[] = { 0, 1, 2,
				2, 1, 3};
		
		Mesh mesh2 = new Mesh(vertices2, indices2, true);
		
		Mesh mesh = new Mesh(vertices, indices, true);
		Material material = new Material(); //new Texture("test.png"), new Vector3f(1,1,1), 1, 8);
		material.addTexture( "diffuse", new Texture( "test.png" ) );
		material.addFloat("specularIntensity", 1.0f);
		material.addFloat("specularPower", 8.0f);
		
		Mesh tempMesh = new Mesh( "monkey3.obj" );

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		
		GameObject planeObject = new GameObject();
		
		planeObject.addComponent( meshRenderer );
		planeObject.getTransform().getPos().set( 0, -1, 5 );
		
		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight( new Vector3f( 0, 0, 1 ), 0.4f );
		directionalLightObject.addComponent( directionalLight );
		
		GameObject pointLightObject = new GameObject();
		pointLightObject.addComponent( new PointLight( new Vector3f( 0, 1, 0 ), 0.4f, new Vector3f( 0, 0, 1 ) ) );
		
		SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,
				new Vector3f(0,0,0.1f), 0.7f);
		
		GameObject spotLightObject = new GameObject();
		
		spotLightObject.addComponent( spotLight );
		
		spotLight.getTransform().getPos().set( 5, 0, 5 );
		spotLight.getTransform().setRot( new Quaternion( new Vector3f( 0, 1, 0 ) , (float)Math.toRadians( 90.0f ) ) );
		
		addObject( planeObject );
		addObject( directionalLightObject );
		addObject( pointLightObject );
		addObject( spotLightObject );
		
		//getRootObject().addChild( new GameObject().addComponent( new Camera( (float)Math.toRadians( 70.0f ), (float)Window.getWidth() / (float)Window.getHeight(), 0.01f, 100.0f  ) ) );
		
		GameObject testMesh1 = new GameObject().addComponent( new MeshRenderer( mesh2, material ) );
		GameObject testMesh2 = new GameObject().addComponent( new MeshRenderer( mesh2, material ) );
		
		testMesh1.getTransform().getPos().set(0, 2, 0);
		testMesh1.getTransform().setRot( new Quaternion( new Vector3f( 0, 1, 0 ), 0.4f ) );
		
		testMesh2.getTransform().getPos().set(0, 0, 5);
		testMesh2.addChild( new GameObject().addComponent( new Camera( (float)Math.toRadians( 70.0f ), (float)Window.getWidth() / (float)Window.getHeight(), 0.01f, 100.0f  ) ) );
		
		testMesh1.addChild( testMesh2 );
		
		addObject( testMesh1 );
		
		directionalLight.getTransform().setRot( new Quaternion( new Vector3f( 1, 0, 0 ), (float)Math.toRadians( -45 ) ) );
	}
}