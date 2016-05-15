package courseCreator;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by giogio on 13/05/16.
 */
public class CourseCreatorLoop {
    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        Light light = new Light(new Vector3f(0,1000,0), new Vector3f(1,1,1));

        Camera camera = new Camera();

        MasterRenderer renderer = new MasterRenderer();

        Control control = new Control(loader);

        while(!Display.isCloseRequested()){
            camera.move();
            control.changeSize();
            renderer.processTerrain(control.getTerrain());
            renderer.render(light,camera);
            DisplayManager.updateDisplay();

        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
