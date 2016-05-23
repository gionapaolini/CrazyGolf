package CourseCreator;

import ObjectsGolf.Ball;
import ObjectsGolf.Obstacle;
import ObjectsGolf.PutHole;
import entities.Camera;
import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.MousePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giogio on 21/05/16.
 */
public class ControlPanel {

    private MousePicker picker;
    private Loader loader;
    private Terrain terrain;
    private Ball ball;
    private PutHole putHole;
    private Camera camera;
    private GuiCourseCreator guiCourseCreator;
    private List<Obstacle> obstaclesList;
    private Entity currentEntity;

    //****

    private boolean widthMode = false;
    private long timeTerrainSize;
    private long timeClickGui;

    private float mostNegativeX;
    private float mostNegativeZ;
    private float mostPositiveX;
    private float mostPositiveZ;

    private GuiButton ballButton;
    private GuiButton putHoleButton;
    private GuiButton obs1Button;
    private GuiButton obs2Button;
    private GuiButton obs3Button;
    private GuiButton obs4Button;

    private boolean collide;

    private ModelTexture collisionText;

    private Vector3f previousCoordBall;
    private Vector3f previousCoordPutHole;


    public ControlPanel(MousePicker picker, Loader loader, Terrain terrain, Ball ball, PutHole putHole, Camera camera, GuiCourseCreator guiCourseCreator) {
        this.picker = picker;
        this.loader = loader;
        this.terrain = terrain;
        this.ball = ball;
        this.putHole = putHole;
        this.camera = camera;
        this.guiCourseCreator = guiCourseCreator;
        this.obstaclesList = new ArrayList<Obstacle>();
        ballButton = guiCourseCreator.getBallButton();
        putHoleButton = guiCourseCreator.getPutholeButton();
        obs1Button = guiCourseCreator.getObstacle1Button();
        obs2Button = guiCourseCreator.getObstacle2Button();
        obs3Button = guiCourseCreator.getObstacle3Button();
        obs4Button = guiCourseCreator.getObstacle4Button();
        currentEntity = null;

        collisionText = new ModelTexture(loader.loadTexture("red"));


        ball.getBall().setCollisionTexture(collisionText);
        putHole.getPutHole().setCollisionTexture(collisionText);

    }

    public void checkUpdates(){
        picker.update();
        moveCamera();
        changeSizeTerrain();
        checkButtonClicks();
        moveAround();
    }

    public List<Obstacle> getObstaclesList(){
        return obstaclesList;
    }

    private void moveCamera(){
        camera.move();
    }
    private void changeSizeTerrain(){
        float width = terrain.width;
        float height = terrain.height;
        long current_time = System.currentTimeMillis();

        if((current_time - timeTerrainSize)>250) {
            if (Keyboard.isKeyDown(Keyboard.KEY_DIVIDE)) {
                widthMode = !widthMode;
                timeTerrainSize = System.currentTimeMillis();
            }


        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ADD)){
            if(widthMode && width<50) {
                width += 1;
            }else if(!widthMode && height<50){
                height+=1;
            }
            terrain.changeSize(width,height,loader);
            camera.setXLimit(width);
            camera.setZLimit(height);


        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
            if(widthMode && (width/2)>mostPositiveX+1 && (-width/2)<mostNegativeX-1 && width>1) {
                width -= 1;
            }else if(!widthMode && (height/2)>mostPositiveZ+1 && (-height/2)<mostNegativeZ-1 && height>1){
                height-=1;
            }
            terrain.changeSize(width,height,loader);
            camera.setXLimit(width);
            camera.setZLimit(height);

        }
    }
    private void checkButtonClicks(){

        long current_time = System.currentTimeMillis();

        if((current_time - timeClickGui)>250) {

            if (Mouse.isButtonDown(0)) {

                Vector2f mouseC = picker.getNormalCoord();


                if ((mouseC.x >= -0.898) && (mouseC.x <= -0.6) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    ballButton.swap();
                    putHoleButton.deselect();
                    obs1Button.deselect();
                    obs2Button.deselect();
                    obs3Button.deselect();
                    obs4Button.deselect();
                }else if ((mouseC.x >= -0.598) && (mouseC.x <= -0.3) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    putHoleButton.swap();
                    ballButton.deselect();
                    obs1Button.deselect();
                    obs2Button.deselect();
                    obs3Button.deselect();
                    obs4Button.deselect();
                }else if ((mouseC.x >= -0.298) && (mouseC.x <= 0) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    obs1Button.swap();
                    ballButton.deselect();
                    putHoleButton.deselect();
                    obs2Button.deselect();
                    obs3Button.deselect();
                    obs4Button.deselect();
                }else if ((mouseC.x >= 0) && (mouseC.x <= 0.299) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    obs2Button.swap();
                    ballButton.deselect();
                    putHoleButton.deselect();
                    obs1Button.deselect();
                    obs3Button.deselect();
                    obs4Button.deselect();
                }else if ((mouseC.x >= 0.301) && (mouseC.x <= 0.6) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    obs3Button.swap();
                    ballButton.deselect();
                    putHoleButton.deselect();
                    obs2Button.deselect();
                    obs1Button.deselect();
                    obs4Button.deselect();
                }else if ((mouseC.x >= 0.601) && (mouseC.x <= 0.9) && (mouseC.y <= 0.894) && (mouseC.y >= 0.783)) {
                    obs4Button.swap();
                    ballButton.deselect();
                    putHoleButton.deselect();
                    obs2Button.deselect();
                    obs3Button.deselect();
                    obs1Button.deselect();
                }else{
                    fixObject();
                    ballButton.deselect();
                    putHoleButton.deselect();
                    obs1Button.deselect();
                    obs2Button.deselect();
                    obs3Button.deselect();
                    obs4Button.deselect();
                    setLimitsTerrainSize();
                }
                timeClickGui = System.currentTimeMillis();

                checkStateButton();

            }
        }
    }

    private void fixObject(){
        if(currentEntity!=null && collide){
            if(ballButton.isSelected()) {
                ball.getBall().setPosition(previousCoordBall);
                ball.getBall().setCollideColor(false);
            }else if(putHoleButton.isSelected()) {
                putHole.getPutHole().setPosition(previousCoordPutHole);
                putHole.getPutHole().setCollideColor(false);
            }else{
                obstaclesList.remove(obstaclesList.size()-1);
            }
        }
    }

    private void checkStateButton(){

        if(ballButton.isSelected()){
            attachBallToPointer();
        }else if(putHoleButton.isSelected()){
            attachPutHoleToPointer();
        }else if(obs1Button.isSelected()){
            attachNewObsToPointer("tree");
        }else if(obs2Button.isSelected()){

        }else if(obs3Button.isSelected()){

        }else if(obs4Button.isSelected()){

        }else {
            currentEntity = null;
        }

    }

    private void attachNewObsToPointer(String name){
        Obstacle g = new Obstacle(loader,name,"white");
        currentEntity = g.getObstacle();
        currentEntity.setCollisionTexture(collisionText);
        obstaclesList.add(g);
    }

    private void attachBallToPointer(){
        previousCoordBall = ball.getBall().getPosition();
        currentEntity = ball.getBall();
    }

    private void attachPutHoleToPointer(){
        previousCoordPutHole = putHole.getPutHole().getPosition();
        currentEntity = putHole.getPutHole();
    }

    private void moveAround(){
        Vector3f terrainPoint = picker.getCurrentTerrainPoint();
        if(terrainPoint!=null) {
            if (currentEntity != null) {
                currentEntity.setPosition(terrainPoint);
                collide = false;
                for(Obstacle obs: obstaclesList){
                    if(obs.getObstacle()!=currentEntity) {
                        Vector3f originOBS = obs.getObstacle().getPosition();
                        double distance = Math.sqrt(Math.pow(originOBS.x - terrainPoint.x, 2) +
                                Math.pow(originOBS.y - terrainPoint.y, 2) + Math.pow(originOBS.z - terrainPoint.z, 2));
                        if (distance < 1) {
                            collide = true;
                            break;
                        }
                    }
                }
                if(ball.getBall()!=currentEntity) {
                    Vector3f originBall = ball.getBall().getPosition();
                    double distance = Math.sqrt(Math.pow(originBall.x - terrainPoint.x, 2) +
                            Math.pow(originBall.y - terrainPoint.y, 2) + Math.pow(originBall.z - terrainPoint.z, 2));
                    if (distance < 1)
                        collide = true;
                }
                if(putHole.getPutHole()!=currentEntity){
                    Vector3f originBall = putHole.getPutHole().getPosition();
                    double distance = Math.sqrt(Math.pow(originBall.x - terrainPoint.x, 2) +
                            Math.pow(originBall.y - terrainPoint.y, 2) + Math.pow(originBall.z - terrainPoint.z, 2));
                    if (distance < 1)
                        collide = true;

                }


                currentEntity.setCollideColor(collide);
            }
        }

    }

    private void setLimitsTerrainSize(){
        mostPositiveX = 0;
        mostNegativeX = 0;
        mostNegativeZ = 0;
        mostPositiveZ = 0;
        if(ball.getBall().getPosition().x > mostPositiveX)
            mostPositiveX = ball.getBall().getPosition().x;
        if(putHole.getPutHole().getPosition().x > mostPositiveX)
            mostPositiveX = putHole.getPutHole().getPosition().x;
        if(ball.getBall().getPosition().x < mostNegativeX)
            mostNegativeX = ball.getBall().getPosition().x;
        if(putHole.getPutHole().getPosition().x < mostNegativeX)
            mostNegativeX = putHole.getPutHole().getPosition().x;

        if(ball.getBall().getPosition().z > mostPositiveZ)
            mostPositiveZ = ball.getBall().getPosition().z;
        if(putHole.getPutHole().getPosition().z > mostPositiveZ)
            mostPositiveZ = putHole.getPutHole().getPosition().z;
        if(ball.getBall().getPosition().z < mostNegativeZ)
            mostNegativeZ = ball.getBall().getPosition().z;
        if(putHole.getPutHole().getPosition().z < mostNegativeZ)
            mostNegativeZ = putHole.getPutHole().getPosition().z;

    }

}
