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
import player.AiPlayer;
import player.HumanPlayer;
import player.Player;
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
        Ball ball1 = new Ball(loader);
        Ball ball2 = new Ball(loader);
        //ball.getBall().setPosition(new Vector3f(4,0,5));
        PutHole putHole = new PutHole(loader);

        MousePicker picker = new MousePicker(camera,renderer.getProjectionMatrix(),terrain);
        
        ball1.setPos( new Vector3D(1,0,-2));
        ball1.setDir( new Vector3D(1,1,1));
        ball1.vel = new Vector3D(0.0f,0,0f);
        
        ball2.setPos( new Vector3D(5,0,4));
        ball2.setDir( new Vector3D(1,1,1));
        ball2.vel = new Vector3D(0,0,0);
        


        //ControlShot shot = new ControlShot(picker,ball1, putHole);
        Player player1 = new HumanPlayer(ball1, putHole, picker);
        //Player player1 = new AiPlayer(ball1, putHole);
        //Player player2 = new HumanPlayer(ball2, putHole, picker);
        Player player2 = new AiPlayer(ball2, putHole);
        //byte turn = 1;
        //int i = 0;

        while(!Display.isCloseRequested()){
            //shot.shot();
            //shot.aiShot(1);
        	playerLogic(player1, player2);
        	
        	Physics.applyBallPhysics(ball1, putHole,new Vector3D(0,1,0));
        	Physics.applyBallPhysics(ball2, putHole,new Vector3D(0,1,0));
        	
        	camera.move();
        	renderer.render(light, camera);
        	renderer.processEntity(ball1.getBall());
        	renderer.processEntity(ball2.getBall());
        	renderer.processEntity(putHole.getPutHole());
        	renderer.processTerrain(terrain);
            DisplayManager.updateDisplay();
            //i++;
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

	}
	
	public static void playerLogic(Player p1, Player p2){
		if(!p1.ball.isMoving && !p2.ball.isMoving){
			if(p1.getStrokes() == p2.getStrokes())
				p1.shot();
			else
				p2.shot();
		}
	}
}
