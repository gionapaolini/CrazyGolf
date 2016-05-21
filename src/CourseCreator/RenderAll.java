package CourseCreator;

import ObjectsGolf.Ball;
import ObjectsGolf.Obstacle;
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
    private Terrain terrain;

    public RenderAll(Light light, Camera camera, GuiCourseCreator guiCourseCreator, Ball ball, List<Obstacle> obstaclesList,Terrain terrain){
        masterRenderer = new MasterRenderer();
        this.light = light;
        this.camera = camera;
        this.guiCourseCreator = guiCourseCreator;
        this.ball = ball;
        this.obstaclesList = obstaclesList;
        this.terrain = terrain;
    }

    public void render(){
        masterRenderer.render(light,camera);
        masterRenderer.processEntity(ball.getBall());
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
