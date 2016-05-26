package physics;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;

import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import terrains.Terrain;
import toolbox.MousePicker;

/**
 * Created by giogio on 23/05/16.
 */
public class ControlShot {

    private MousePicker picker;
    private Ball ball;
    private PutHole puthole;
    private long timeClickShot;

    public ControlShot(MousePicker picker, Ball ball, PutHole puthole){
        this.picker = picker;
        this.ball = ball;
        this.puthole = puthole;
    }

    public void shot(){
        long current_time = System.currentTimeMillis();
        picker.update();
        if((current_time - timeClickShot)>250 &&picker.getCurrentTerrainPoint()!=null&& !ball.isMoving && Mouse.isButtonDown(0)){

            Vector3f pick = picker.getCurrentTerrainPoint();
            Vector3D pointer = new Vector3D(pick.x,pick.y,pick.z);
            Vector3D distance = pointer.subtract(ball.pos);
            System.out.println(distance.x);
            System.out.println(distance.y);
            System.out.println(distance.z);

            ball.vel = distance.divide(20);
            ball.isMoving = true;
            timeClickShot = System.currentTimeMillis();
        }
    }
    
    public void aiShot(int counter){
    	if (counter <= 3){
    		directShot();
    		}
    	else{
    		randomShot();
    	}
    		
    	
    	
    }
    
    private void directShot(){
    	Vector3f target = puthole.getPutHole().getPosition();
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
    	
    }
    
    
    private void randomShot(){
    	Random r = new Random();
    	Vector3f origin = ball.getBall().getPosition();
    	Vector3f target = puthole.getPutHole().getPosition();
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
    	
    }


}
