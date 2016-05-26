package player;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;
import physics.Vector3D;
import toolbox.MousePicker;

public class HumanPlayer extends Player{
	
	 private MousePicker picker;
	 private long timeClickShot;

	public HumanPlayer(Ball b, PutHole p, MousePicker picker) {
		super(b, p);
		this.picker = picker;
	}

	@Override
	public void shot() {
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
            strokes++;
        }
        
		
	}

}
