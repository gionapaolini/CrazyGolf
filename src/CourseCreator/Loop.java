package CourseCreator;

import ObjectsGolf.Ball;
import ObjectsGolf.PutHole;
import entities.Camera;
import entities.Light;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.MousePicker;

/**
 * Created by giogio on 21/05/16.
 */
public class Loop {
    public static void main(String[] args){

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Light light = new Light(new Vector3f(0,20,0), new Vector3f(1,1,1));
        Camera camera = new Camera();
        MasterRenderer renderer = new MasterRenderer();
        GuiCourseCreator gui = new GuiCourseCreator(loader);
        Terrain terrain = new Terrain(-10,-10,20,20,loader,new ModelTexture(loader.loadTexture("grassy2")));
        MousePicker picker = new MousePicker(camera,renderer.getProjectionMatrix(),terrain);
        Ball ball = new Ball(loader);
        PutHole putHole = new PutHole(loader);
        ControlPanel controlPanel = new ControlPanel(picker,loader,terrain,ball,putHole,camera,gui);
        RenderAll renderAll = new RenderAll(light,camera,gui,ball,putHole,controlPanel.getObstaclesList(),terrain);


        while(!Display.isCloseRequested()){

            controlPanel.checkUpdates();
            renderAll.render();
            DisplayManager.updateDisplay();

        }
        renderAll.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
