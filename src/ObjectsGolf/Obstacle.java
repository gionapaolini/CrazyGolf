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
public class Obstacle {

    private Entity obstacle;

    public Obstacle(Loader loader, String nameModel, String nameTexture){
        RawModel model = OBJLoader.loadObjModel(nameModel,loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture(nameTexture));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        obstacle = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
    }

    public Entity getObstacle(){
        return obstacle;
    }
}
