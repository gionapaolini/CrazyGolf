package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;


/**
 * Created by giogio on 13/05/16.
 */
public class Camera {
    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(){}

    public void move(){

        float nx = (float)(Math.sin(yaw));
        float ny = (float)(Math.sin(pitch));
        float nz = (float)(Math.cos(yaw));

        float t = 0.2f;

        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            position.x += nx*t;
            position.y -= ny*t;
            position.z -= nz*t;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S)){
            position.x -= nx*t;
            position.y += ny*t;
            position.z += nz*t;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            yaw+=1f;
            if(yaw>360)
                yaw = 0;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            yaw-=1f;
            if(yaw<0)
                yaw = 360;
        }


        if(Mouse.isButtonDown(1)){
            float pitchChange = Mouse.getDY() * 0.1f;
            pitch += pitchChange;
        }

        if (Mouse.isButtonDown(0)){
            float angleChange = Mouse.getDX() * 0.1f;
            yaw += angleChange;
        }

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
