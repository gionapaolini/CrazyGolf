package courseCreator;

import entities.Camera;
import entities.Entity;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import terrains.Terrain;
import textures.ModelTexture;
import toolbox.MousePicker;

/**
 * Created by giogio on 15/05/16.
 */
public class Control {

    private Terrain terrain;
    private Loader loader;
    private float width;
    private float height;
    private boolean widthMode;
    Entity currentObj;
    long time = 0;
    MousePicker picker;

    public Control(Loader loader, Camera cam, Matrix4f projectionMatrix){
        widthMode = true;
        this.loader = loader;
        width = 20;
        height = 20;
        terrain = new Terrain(-10,-10,width,height,loader,new ModelTexture(loader.loadTexture("grassy2")));
        picker = new MousePicker(cam,projectionMatrix,terrain);
    }

    public Terrain getTerrain(){
        return terrain;
    }

    public void moveCamera(){

    }

    public void changeSize(){
        long current_time = System.currentTimeMillis();

        if((current_time - time)>250) {
            if (Keyboard.isKeyDown(Keyboard.KEY_DIVIDE)) {
                widthMode = !widthMode;
                time = System.currentTimeMillis();
            }


        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ADD)){
            if(widthMode && width<50) {
                width += 1;
            }else if(!widthMode && height<50){
                height+=1;
            }
            terrain.changeSize(width,height,loader);

        }
        if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)){
            if(widthMode && width>1) {
                width -= 1;
            }else if(!widthMode && height>1){
                height-=1;
            }
            terrain.changeSize(width,height,loader);
        }


    }

    public void setCurrentObj(Entity obj){
        currentObj = obj;
    }

    public void moveObject(){
        if(currentObj.moving && picker.getCurrentTerrainPoint()!=null){
            currentObj.setPosition(new Vector3f(picker.getCurrentTerrainPoint().x,0,picker.getCurrentTerrainPoint().z));
        }
    }

}
