package entities;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;


/**
 * Created by giogio on 13/05/16.
 */
public class Camera {
    private Vector3f position = new Vector3f(0,10,10);
    private float pitch = 40;
    private float yaw;
    private float roll;
    private float SQlimit = 1000;

    public Camera(){}

    public void move(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            if(position.z>-SQlimit)
                position.z -= 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            if(position.z<SQlimit)
                position.z += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            if(position.x<SQlimit)
                position.x += 0.2f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            if(position.x>-SQlimit)
                position.x -= 0.2f;
        }


        if(Mouse.isButtonDown(1)){
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch += pitchChange;
            pitch%=360;
        }

        if (Mouse.isButtonDown(0)){
            float angleChange = Mouse.getDX() * 0.1f;
            yaw -= angleChange;
            yaw%=360;
        }




    }

    public void setSQlimit(float sQlimit){
        this.SQlimit = sQlimit/2;
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
