package guis;

import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

/**
 * Created by giogio on 15/05/16.
 */
public class GuiTexture {

    private int texture;
    private Vector2f position;
    private Vector2f scale;

    public GuiTexture(int texture, Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.position = position;
        this.scale = scale;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int textureID){
        texture = textureID;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }
}
