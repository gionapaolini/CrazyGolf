package courseCreator;

import entities.Camera;
import entities.Entity;
import entities.Light;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.MousePicker;

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

        RawModel model = OBJLoader.loadObjModel("tree",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Entity tree = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);

        Camera camera = new Camera();
        camera.setSQlimit(50);

        MasterRenderer renderer = new MasterRenderer();

        MousePicker picker = new MousePicker(camera,renderer.getProjectionMatrix());
        Control control = new Control(loader, picker);
        GuiCourseCreator gui = new GuiCourseCreator(loader,picker);


        while(!Display.isCloseRequested()){
            camera.move();
            picker.update();
            gui.checkClick();
            checkSpace(tree);
            //System.out.println(picker.getCurrentRay());
            control.changeSize();
            control.setCurrentObj(tree);
            tree.setMoving(true);
            control.moveObject();

            renderer.processEntity(tree);
            renderer.processTerrain(control.getTerrain());
            renderer.render(light,camera);
            gui.render();
            DisplayManager.updateDisplay();

        }
        renderer.cleanUp();
        loader.cleanUp();
        gui.cleanUp();
        DisplayManager.closeDisplay();

    }
    public static void checkSpace(Entity tree){
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            tree.setState(!tree.render);
        }
    }
}
