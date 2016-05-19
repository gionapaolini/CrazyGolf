package courseCreator;

import guis.GuiTexture;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by giogio on 16/05/16.
 */
public class GuiButton {

    private int textureSelected;
    private int textureDeselected;
    private GuiTexture currentText;
    private Vector2f position;
    private Vector2f scale;
    private float width;
    private float height;
    boolean selected;

    public GuiButton(String selected, String notSelected, Loader loader, float x, float y, float scale){
        position = new Vector2f(x,y);
        this.scale = new Vector2f(scale,scale);
        textureSelected = loader.loadTexture(selected);
        textureDeselected = loader.loadTexture(notSelected);
        currentText = new GuiTexture(textureDeselected,position,this.scale);
        this.selected=false;

    }

    public void swap(){
        selected = !selected;
        if(selected){
            select();
        }else{
            deselect();
        }
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void select(){
        currentText.setTexture(textureSelected);
        selected = true;
    }
    public void deselect(){
        currentText.setTexture(textureDeselected);
        selected = false;
    }
    public GuiTexture getGuiTexture(){
        return currentText;
    }

}
