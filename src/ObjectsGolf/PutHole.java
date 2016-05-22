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
public class PutHole {

    private Entity putHole;

    public PutHole(Loader loader){
        RawModel model = OBJLoader.loadObjModel("bucone",loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("black"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        putHole = new Entity(texturedModel, new Vector3f(0,0,0),0,0,0,1);
    }

    public Entity getPutHole(){
        return putHole;
    }

}
