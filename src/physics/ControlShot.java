package physics;

import ObjectsGolf.Ball;
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
    private long timeClickShot;

    public ControlShot(MousePicker picker, Ball ball){
        this.picker = picker;
        this.ball = ball;
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


}
