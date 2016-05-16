package courseCreator;

import org.lwjgl.input.Keyboard;
import renderEngine.Loader;
import terrains.Terrain;
import textures.ModelTexture;

/**
 * Created by giogio on 15/05/16.
 */
public class Control {

    private Terrain terrain;
    private Loader loader;
    private float width;
    private float height;
    private boolean widthMode;

    public Control(Loader loader){
        widthMode = true;
        this.loader = loader;
        width = 20;
        height = 20;
        terrain = new Terrain(-10,-10,width,height,loader,new ModelTexture(loader.loadTexture("grassy2")));
    }

    public Terrain getTerrain(){
        return terrain;
    }

    public void moveCamera(){

    }

    public void changeSize(){
        if(Keyboard.isKeyDown(Keyboard.KEY_DIVIDE)){
            widthMode = !widthMode;
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

}
