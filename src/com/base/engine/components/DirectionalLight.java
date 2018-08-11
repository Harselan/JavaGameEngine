package com.base.engine.components;

import com.base.engine.core.Vector3f;
import com.base.engine.rendering.ForwardDirectional;
import com.base.engine.rendering.RenderingEngine;

public class DirectionalLight extends BaseLight
{
	private Vector3f direction;
	
	public DirectionalLight( Vector3f color, float intensity )
	{
		super( color, intensity );
		
		setShader( ForwardDirectional.getInstance() );
	}

	public Vector3f getDirection() 
	{
		return getTransform().getTransformedRot().getForward();
	}
}
