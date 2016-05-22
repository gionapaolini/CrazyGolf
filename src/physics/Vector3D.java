package physics;

import org.lwjgl.util.vector.Vector3f;

public class Vector3D {
	
	
	public float x;
	public float y;
	public float z;
	float magnitude;
	
	public Vector3D(Vector3f v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public Vector3D(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		magnitude = (float) Math.sqrt(x*x+y*y+z*z);
	}
	
	public Vector3f toVector3f(){
		return new Vector3f(x,y,z);
	}
	
	public Vector3D copy(){
		Vector3D temp = new Vector3D(x,y,z);
		return temp;
	}
	
	public Vector3D add(float c){
		Vector3D temp = copy();
		temp.x += c;
		temp.y += c;
		temp.z += c;
		return temp;
	}
	public Vector3D add(Vector3D c){
		Vector3D temp = copy();
		temp.x += c.x;
		temp.y += c.y;
		temp.z += c.z;
		return temp;
	}
	public Vector3D subtract(float c){
		Vector3D temp = copy();
		temp.x -= c;
		temp.y -= c;
		temp.z -= c;
		return temp;
	}
	public Vector3D subtract(Vector3D c){
		Vector3D temp = copy();
		temp.x -= c.x;
		temp.y -= c.y;
		temp.z -= c.z;
		return temp;
	}
	public Vector3D mulitply(float c){
		Vector3D temp = copy();
		temp.x *= c;
		temp.y *= c;
		temp.z *= c;
		return temp;
	}
	public Vector3D multiply(Vector3D c){
		Vector3D temp = copy();
		temp.x *= c.x;
		temp.y *= c.y;
		temp.z *= c.z;
		return temp;
	}
	public Vector3D divide(float c){
		Vector3D temp = copy();
		temp.x /= c;
		temp.y /= c;
		temp.z /= c;
		return temp;
	}
	public Vector3D divide(Vector3D c){
		Vector3D temp = copy();
		temp.x /= c.x;
		temp.y /= c.y;
		temp.z /= c.z;
		return temp;
	}
	
	public Vector3D absolute(){
		return new Vector3D(Math.abs(x) ,Math.abs(y), Math.abs(z));
	}
	
	public void normalize(){
		divide(magnitude);
	}
	
	public Vector3D cross(Vector3D v){
		float ansx = y*v.z - z*v.y;
		float ansy = v.x*z - x*v.z;
		float ansz = x*v.y - v.x*y;
		return new Vector3D(ansx, ansy,ansz);
	}
	
	public float dot(Vector3D v){
		return x*v.x + y*v.y + z*v.z;
	}
	
	public void set(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3D get(){
		return this;
	}

}
