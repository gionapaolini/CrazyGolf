package toolbox;

import entities.Camera;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

/**
 * Created by giogio on 16/05/16.
 */
public class MousePicker {

    private Vector3f currentRay;

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Camera camera;

    public MousePicker(Camera cam, Matrix4f projectionMatrix){
        this.camera = cam;
        this.projectionMatrix = projectionMatrix;
        this.viewMatrix = Maths.createViewMatrix(camera);
    }

    public Vector3f getCurrentRay(){
        return currentRay;
    }

    public void update(){
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
    }

    private Vector3f calculateMouseRay(){
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        Vector2f normalizedCoords  = getNormalizeDeviceCoords(mouseX,mouseY);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x,normalizedCoords.y,-1f,1f);
        Vector4f eyeCoords = toEyeCoords(clipCoords);
        Vector3f worldRay = toWorldCoords(eyeCoords);
        return worldRay;

    }

    private Vector3f toWorldCoords(Vector4f eyeCoords){
        Matrix4f invertedView = Matrix4f.invert(viewMatrix,null);
        Vector4f rayWorld = Matrix4f.transform(invertedView,eyeCoords,null);
        Vector3f mouseRay = new Vector3f(rayWorld.x,rayWorld.y,rayWorld.z);
        mouseRay.normalise();
        return mouseRay;


    }

    private Vector4f toEyeCoords(Vector4f clipCoords){
        Matrix4f invertedProjection = Matrix4f.invert(projectionMatrix,null);
        Vector4f eyeCoords = Matrix4f.transform(invertedProjection,clipCoords,null);
        return new Vector4f(eyeCoords.x,eyeCoords.y,-1f,1f);
    }

    private Vector2f getNormalizeDeviceCoords(float mouseX, float mouseY){
        float x = (2f*mouseX)/ Display.getWidth() -1f;
        float y = (2f*mouseY)/Display.getHeight() -1f;
        return new Vector2f(x,y);
    }

    public Vector2f getNormalCoord(){
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        return getNormalizeDeviceCoords(mouseX,mouseY);
    }

}
