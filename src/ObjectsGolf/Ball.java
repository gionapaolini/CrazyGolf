package ObjectsGolf;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import physics.Vector3D;

import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

/**
 * Created by giogio on 21/05/16.
 */
public class Ball {
	
	public Vector3D pos; // position
	public Vector3D vel; // velocity
	public Vector3D dir; // direction
	public boolean isMoving;
	
	private float radius;
	private int angle;
    private Entity ball;

    public Ball(Loader loader){
        RawModel model = OBJLoader.loadObjModel("pallo",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("white"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        ball = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
        isMoving = true;
    }
    
    
    public void move(){
    	ball.setPosition(pos.toVector3f());
    }
    
    public float getRadius(){
    	return radius;
    }

    public Entity getBall(){
        return ball;
    }
    
    public void setPos(Vector3D v){
    	pos =v;
    	move();
    }
    
    public void setDir(Vector3D v){
    	dir = v;
    	dir.normalize();
    }

}
