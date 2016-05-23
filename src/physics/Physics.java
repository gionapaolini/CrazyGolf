package physics;

import org.lwjgl.util.vector.Vector3f;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;

public class Physics {
	

	private static final Vector3D WORLD_UP = new Vector3D(0, 1 , 0);
	private static final float GRAVITY = -0.0098f;
	private static final float ROLL_FRIC = 0.9670f;
	private static float minVelocity = 0.001f;
	
	
	public static void applyBallPhysics(Ball b, PutHole p,Vector3D normal){
		
		calcSlope(b, normal);
		
		if(b.isMoving){
			b.vel = b.vel.mulitply(ROLL_FRIC);
			b.pos = b.pos.add(b.vel);
			b.move();
			
			if((b.vel.absolute().x<=minVelocity 
			   && b.vel.absolute().y<=minVelocity 
			   && b.vel.absolute().z<=minVelocity)
			  //|| checkBallInHole(b,p)
			   ){
					b.isMoving = false;
			}
			//TODO: Handle Collision all together
		}
		
	}
	
	public static boolean checkBallInHole(Ball b, PutHole p){
		Vector3f e = p.getPutHole().getPosition();
		Vector3f a = b.pos.toVector3f();
		if(a.x>e.x-0.00005f && a.x<e.x+00005f && a.z>e.z-0.00005f && a.z<e.z+00005){
			return true;
		}
		return false;
	}
	
	
	
	public static boolean collision(Vector3D p1, Vector3D p2, float r1, float r2) {
		float dist =(float) Math.sqrt(((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y)) + ((p1.z - p2.z) * (p1.z - p2.z)));
		if (dist <= r2) {
			return true;
		}
		return false;
	}
	
	public void reflect(Ball b, Vector3D normal){
		Vector3D bounce = new Vector3D(b.dir.x, 0, b.dir.z);
		bounce = normal.mulitply(2).mulitply(b.dir.dot(normal)).subtract(b.dir).mulitply(-1);
		b.dir.x =bounce.x;
		b.dir.z= bounce.z;
	}
	
	public static void calcSlope(Ball b, Vector3D normal){
		Vector3D xaxis = WORLD_UP.cross(normal);
		Vector3D slope = normal.cross(xaxis);
		
		slope.normalize();
		
		if (slope.y != 0.0) {
			if (normal.dot(b.dir) > 0.0) { // rolling down
				b.dir.y = slope.y * -1;
			} else if (normal.dot(b.dir) < 0.0) { // rolling up
				b.dir.y = slope.y;
			} else {
			}
		}else {
			b.dir.y = 0.0f; 
		}
	}

}
