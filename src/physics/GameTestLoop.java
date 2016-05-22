package physics;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import CourseCreator.ControlPanel;
import CourseCreator.GuiCourseCreator;
import CourseCreator.RenderAll;
import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;
import entities.Camera;
import entities.Light;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.MousePicker;

public class GameTestLoop {

	public static void main(String[] args) {
		DisplayManager.createDisplay();

        Loader loader = new Loader();
        Light light = new Light(new Vector3f(0,1000,0), new Vector3f(1,1,1));
        Camera camera = new Camera();
        
        MasterRenderer renderer = new MasterRenderer();
        
        Terrain terrain = new Terrain(-10,-10,20,20,loader,new ModelTexture(loader.loadTexture("grassy2")));
        Ball ball = new Ball(loader);
        ball.getBall().setPosition(new Vector3f(5,0,0));
        PutHole putHole = new PutHole(loader);
        
        ball.pos = new Vector3D(5,0,0);
        ball.dir = new Vector3D(0,0,0);
        ball.vel = new Vector3D(-0.0f,0.01f,0.0f);
        
    


        while(!Display.isCloseRequested()){
        	Physics.applyBallPhysics(ball, putHole,new Vector3D(0,1,0));
        	camera.move();
        	renderer.render(light, camera);
        	renderer.processEntity(ball.getBall());
        	renderer.processEntity(putHole.getPutHole());
        	renderer.processTerrain(terrain);
            DisplayManager.updateDisplay();

        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

	}
}
