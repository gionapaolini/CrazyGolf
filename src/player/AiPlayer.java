package player;

import java.util.Random;

import org.lwjgl.util.vector.Vector3f;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;
import physics.Physics;
import physics.Vector3D;

public class AiPlayer extends Player{

	public AiPlayer(Ball b, PutHole p) {
		super(b, p);
	}

	@Override
	public void shot() {
		Vector3f target = hole.getPutHole().getPosition();
	    Vector3f origin = ball.getBall().getPosition();
	   	float a = Physics.ROLL_FRIC;
	   	float x = target.x - origin.x;
	   	float z = target.z - origin.z;
	   	float xdif, zdif;
	   	if (target.x > origin.x)
	    	xdif = -(float)Math.sqrt(x*x);
    	else	    		
    		xdif = (float)Math.sqrt(x*x);
	    if (target.z > origin.z)
	    	zdif = -(float)Math.sqrt(z*z);
	    else
	    	zdif = (float)Math.sqrt(z*z);
	    float xvel = -2 *a * xdif;
	   	float zvel = -2 *a * zdif;
	   	Vector3D vel = new Vector3D(xvel,0,zvel);
	   	ball.vel = vel.divide(20).divide(2.8f);
	   	strokes++;
	   	ball.isMoving=true;
	}
	
	 private void directShot(){
		Vector3f target = hole.getPutHole().getPosition();
	    Vector3f origin = ball.getBall().getPosition();
	   	float a = Physics.ROLL_FRIC;
	   	float x = target.x - origin.x;
	   	float z = target.z - origin.z;
	   	float xdif, zdif;
	   	if (target.x > origin.x)
	    	xdif = -(float)Math.sqrt(x*x);
    	else	    		
    		xdif = (float)Math.sqrt(x*x);
	    if (target.z > origin.z)
	    	zdif = -(float)Math.sqrt(z*z);
	    else
	    	zdif = (float)Math.sqrt(z*z);
	    float xvel = -2 *a * xdif;
	   	float zvel = -2 *a * zdif;
	   	Vector3D vel = new Vector3D(xvel,0,zvel);
	   	ball.vel = vel.divide(20);
	   	strokes++;
	    }
	 
	 private void randomShot(){
	    Random r = new Random();
	    Vector3f origin = ball.getBall().getPosition();
	    Vector3f target = hole.getPutHole().getPosition();
	   	float a = Physics.ROLL_FRIC;
	   	float x = target.x - origin.x;
	   	float z = target.z - origin.z;
	   	float xdif = (float)Math.sqrt(x*x);
	   	float zdif = (float)Math.sqrt(z*z);
	   	float xvel = -2 *a * xdif;
	   	float zvel = -2 *a * zdif;
	   	float rX, rZ;
	   	if (target.x > origin.x)
	   		rX =-((float)r.nextInt((int) Math.abs(xvel*1000)))/1000;
	   	else
	   		rX =((float)r.nextInt((int) Math.abs(xvel*1000)))/1000;
	   	if (target.z > origin.z)
	   		rZ =-((float)r.nextInt((int) Math.abs(zvel*1000)))/1000;
	   	else 
	   		rZ =((float)r.nextInt((int) Math.abs(zvel*1000)))/1000;
	   	Vector3D vel = new Vector3D(rX,0,rZ);
	   	ball.vel = vel.divide(20);
	   	strokes++;
	    }

}
