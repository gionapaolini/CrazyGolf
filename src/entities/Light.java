package entities;

import org.lwjgl.util.vector.Vector3f;

/**
 * Created by giogio on 14/05/16.
 */
public class Light {
    Vector3f position;
    Vector3f colour;

    public Light(Vector3f position, Vector3f color) {
        this.position = position;
        this.colour = color;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getColour() {
        return colour;
    }

    public void setColour(Vector3f color) {
        this.colour = color;
    }
}
