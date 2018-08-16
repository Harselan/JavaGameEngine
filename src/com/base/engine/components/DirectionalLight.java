package com.base.engine.components;

import com.base.engine.rendering.RenderingEngine;
import com.base.engine.rendering.Shader;
import com.base.engine.core.Vector3f;

public class DirectionalLight extends BaseLight
{
	private Vector3f direction;
	
	public DirectionalLight( Vector3f color, float intensity )
	{
		super( color, intensity );
		
		setShader( new Shader( "forward-directional" ) );
	}

	public Vector3f getDirection() 
	{
		return getTransform().getTransformedRot().getForward();
	}
}
