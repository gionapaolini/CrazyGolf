package entities;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import toolbox.Maths;


/**
 * Created by giogio on 13/05/16.
 */
public class Camera {
    private Vector3f position = new Vector3f(0,10,10);
    private float pitch = 40;
    private float yaw;
    private float roll;
    private float ZLimit = 10;
    private float XLimit = 10;

    public Camera(){
    }


    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(position.z>(-ZLimit+10))
                position.z -= 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if(position.z<ZLimit+8)
                position.z += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if(position.x<XLimit)
                position.x += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if(position.x>-XLimit)
                position.x -= 0.2f;
        }

        if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
            if(position.y>1)
                position.y -= 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_E)){
            if(position.y<15)
                position.y += 0.2f;
        }


        if (Mouse.isButtonDown(1)){
            Matrix4f matrix = Maths.createViewMatrix(this);
            System.out.println();
            System.out.println();
            System.out.println(matrix.m00 + "    " + matrix.m01 + "    " + matrix.m02 + "    " + matrix.m03);
            System.out.println(matrix.m10 + "    " + matrix.m11 + "    " + matrix.m12 + "    " + matrix.m13);
            System.out.println(matrix.m20 + "    " + matrix.m21 + "    " + matrix.m22 + "    " + matrix.m23);
            System.out.println(matrix.m30 + "    " + matrix.m31 + "    " + matrix.m32 + "    " + matrix.m33);


            float angleChange = Mouse.getDX() * 0.1f;
            yaw -= angleChange;
            yaw%=360;
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch += pitchChange;
            pitch%=360;
        }




    }

    public void setXLimit(float sQlimit){
        this.XLimit = sQlimit/2;
    }
    public void setZLimit(float sQlimit){
        this.ZLimit = sQlimit/2;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
