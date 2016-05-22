package ObjectsGolf;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

/**
 * Created by giogio on 21/05/16.
 */
public class Ball {


    private Entity ball;

    public Ball(Loader loader){
        RawModel model = OBJLoader.loadObjModel("pallo",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("white"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        ball = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
    }

    public Entity getBall(){
        return ball;
    }

}
