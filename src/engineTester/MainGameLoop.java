package engineTester;

import entities.Entity;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
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
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = { -0.5f, 0.5f, 0f,
                             -0.5f, -0.5f, 0f,
                             0.5f, -0.5f, 0f,
                             0.5f, 0.5f, 0f};
        int[] indices = {0,1,3,3,1,2};

        float[] textureCoords = {
                0,0,
                0,1,
                1,1,
                1,0};

        RawModel model = loader.loadToVAO(vertices, textureCoords,indices);

        ModelTexture texture = new ModelTexture(loader.loadTexture("mud"));
        TexturedModel texturedModel = new TexturedModel(model, texture);


        Entity entity = new Entity(texturedModel, new Vector3f(-1,0,0),0,0,0,1);


        while(!Display.isCloseRequested()){
            entity.increasePosition(0.002f,0,0);
            entity.increaseRotation(1,0,0);
            renderer.prepare();
            shader.start();
            renderer.render(entity,shader);
            shader.stop();
            DisplayManager.updateDisplay();

        }

        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
