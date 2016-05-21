package CourseCreator;

import ObjectsGolf.Ball;
import ObjectsGolf.Obstacle;
import entities.Camera;
import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import terrains.Terrain;
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
    private Camera camera;
    private GuiCourseCreator guiCourseCreator;
    private List<Obstacle> obstaclesList;
    private Entity currentEntity;

    //****

    private boolean widthMode = false;
    private long timeTerrainSize;
    private long timeClickGui;

    private GuiButton ballButton;
    private GuiButton putHoleButton;

    private boolean holeAttached;


    public ControlPanel(MousePicker picker, Loader loader, Terrain terrain, Ball ball, Camera camera, GuiCourseCreator guiCourseCreator) {
        this.picker = picker;
        this.loader = loader;
        this.terrain = terrain;
        this.ball = ball;
        this.camera = camera;
        this.guiCourseCreator = guiCourseCreator;
        this.obstaclesList = new ArrayList<Obstacle>();
        ballButton = guiCourseCreator.getBallButton();
        putHoleButton = guiCourseCreator.getPutholeButton();
        currentEntity = null;

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
            if(widthMode && width>1) {
                width -= 1;
            }else if(!widthMode && height>1){
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

                if ((mouseC.x >= -0.648) && (mouseC.y <= 0.897) && (mouseC.x <= -0.35) && (mouseC.y >= 0.783)) {
                    ballButton.swap();
                    putHoleButton.deselect();
                }else if ((mouseC.x >= (1-0.648)) && (mouseC.y <= 0.897) && (mouseC.x <= (1-0.35)) && (mouseC.y >= 0.783)) {
                    putHoleButton.swap();
                    ballButton.deselect();
                }else{
                    putHoleButton.deselect();
                    ballButton.deselect();
                }
                timeClickGui = System.currentTimeMillis();

                checkStateButton();

            }
        }
    }

    private void checkStateButton(){

        if(ballButton.isSelected()){
            attachBallToPointer();
        }else if(putHoleButton.isSelected()){
            currentEntity = null;
            attachPutHoleToPointer();
        }else {
            holeAttached = false;
            currentEntity = null;
        }

    }


    private void attachBallToPointer(){
        currentEntity = ball.getBall();
    }

    private void attachPutHoleToPointer(){
        holeAttached = true;
    }

    private void moveAround(){
        Vector3f terrainPoint = picker.getCurrentTerrainPoint();
        if(terrainPoint!=null) {
            if (currentEntity != null) {
                currentEntity.setPosition(terrainPoint);
            } else if (holeAttached) {
                terrain.setPutholePosition(new Vector2f(terrainPoint.x, terrainPoint.z));
            }
        }
    }

}
