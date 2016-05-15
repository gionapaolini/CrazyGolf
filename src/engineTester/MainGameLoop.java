package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import models.RawModel;
import shaders.StaticShader;
import textures.ModelTexture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by giogio on 13/05/16.
 */
public class MainGameLoop {
    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        RawModel model = OBJLoader.loadObjModel("tree",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));
        TexturedModel texturedModel = new TexturedModel(model, texture);

        Random r = new Random();
        List<Entity> trees = new ArrayList<Entity>();
        for(int i=0;i<100;i++){

            float x = (r.nextFloat()*50)-25;
            float y = (r.nextFloat()*20)-10;
            float z = (r.nextFloat()*50)-25;
            float rx = r.nextFloat()*180;
            float ry = r.nextFloat()*180;
            float rz = r.nextFloat()*180;
            float scale = r.nextFloat();

            trees.add(new Entity(texturedModel, new Vector3f(x,y,z),rx,ry,rz,scale));

        }

        Light light = new Light(new Vector3f(0,1000,0), new Vector3f(1,1,1));

        Camera camera = new Camera();

        MasterRenderer renderer = new MasterRenderer();


        while(!Display.isCloseRequested()){
            camera.move();
            for(Entity entity:trees){
                renderer.processEntity(entity);
            }
            renderer.render(light,camera);
            DisplayManager.updateDisplay();

        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
