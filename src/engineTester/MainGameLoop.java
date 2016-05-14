package engineTester;

import entities.Camera;
import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 * Created by giogio on 13/05/16.
 */
public class MainGameLoop {
    public static void main(String[] args){

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        RawModel model = OBJLoader.loadObjModel("tree",loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));
        TexturedModel texturedModel = new TexturedModel(model, texture);


        Entity entity = new Entity(texturedModel, new Vector3f(0,0,-1),0,0,0,1);

        Camera camera = new Camera();


        while(!Display.isCloseRequested()){
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            DisplayManager.updateDisplay();

        }

        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
