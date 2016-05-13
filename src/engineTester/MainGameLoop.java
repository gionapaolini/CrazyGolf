package engineTester;

import models.TexturedModel;
import org.lwjgl.opengl.Display;
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

        RawModel model = loader.loadToVAO(vertices, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("mud"));
        TexturedModel texturedModel = new TexturedModel(model, texture);


        while(!Display.isCloseRequested()){

            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            DisplayManager.updateDisplay();

        }

        loader.cleanUp();
        shader.cleanUp();
        DisplayManager.closeDisplay();

    }
}
