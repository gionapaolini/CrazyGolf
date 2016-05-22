package CourseCreator;

import ObjectsGolf.Ball;
import ObjectsGolf.Obstacle;
import ObjectsGolf.PutHole;
import entities.Camera;
import entities.Light;
import renderEngine.MasterRenderer;
import terrains.Terrain;

import java.util.List;

/**
 * Created by giogio on 21/05/16.
 */
public class RenderAll {
    private MasterRenderer masterRenderer;
    private Light light;
    private Camera camera;
    private GuiCourseCreator guiCourseCreator;
    private List<Obstacle> obstaclesList;
    private Ball ball;
    private PutHole putHole;
    private Terrain terrain;

    public RenderAll(Light light, Camera camera, GuiCourseCreator guiCourseCreator, Ball ball, PutHole putHole, List<Obstacle> obstaclesList,Terrain terrain){
        masterRenderer = new MasterRenderer();
        this.light = light;
        this.camera = camera;
        this.guiCourseCreator = guiCourseCreator;
        this.ball = ball;
        this.putHole = putHole;
        this.obstaclesList = obstaclesList;
        this.terrain = terrain;
    }

    public void render(){
        masterRenderer.render(light,camera);
        masterRenderer.processEntity(ball.getBall());
        masterRenderer.processEntity(putHole.getPutHole());
        masterRenderer.processTerrain(terrain);
        for(Obstacle obstacle: obstaclesList){
            masterRenderer.processEntity(obstacle.getObstacle());
        }
        guiCourseCreator.render();
    }

    public void cleanUp(){
        masterRenderer.cleanUp();
        guiCourseCreator.cleanUp();
    }




}
