package toolbox;

import entities.Camera;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;
import terrains.Terrain;

import java.util.Vector;

/**
 * Created by giogio on 16/05/16.
 */
public class MousePicker {

    private static final int RECURSION_COUNT = 200;
    private static final int RAY_RANGE=600;

    private Vector3f currentRay;
    private Vector3f currentTerrainPoint;
    public Terrain terrain;

    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    private Camera camera;

    public MousePicker(Camera cam, Matrix4f projectionMatrix, Terrain terrain){
        this.camera = cam;
        this.projectionMatrix = projectionMatrix;
        this.viewMatrix = Maths.createViewMatrix(camera);
        this.terrain = terrain;
    }

    public Vector3f getCurrentRay(){
        return currentRay;
    }
    public Vector3f getCurrentTerrainPoint(){
        return currentTerrainPoint;
    }

    public void update(){
        viewMatrix = Maths.createViewMatrix(camera);
        currentRay = calculateMouseRay();
        if(intersectionInRange(0,RAY_RANGE,currentRay))
            currentTerrainPoint = binarySearch(0,0,RAY_RANGE,currentRay);


    }

    private Vector3f calculateMouseRay(){
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        Vector2f normalizedCoords  = getNormalizeDeviceCoords(mouseX,mouseY);
        Vector4f clipCoords = new Vector4f(normalizedCoords.x,normalizedCoords.y,-1.0f,1.0f);
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
        return new Vector4f(eyeCoords.x,eyeCoords.y,-1f,0);
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

    private Vector3f getPointOnRay(Vector3f ray, float distance){
        Vector3f cam = camera.getPosition();
        Vector3f scaledRay = new Vector3f(ray.x * distance,ray.y * distance,ray.z * distance);
        return Vector3f.add(cam,scaledRay,null);
    }

    private boolean intersectionInRange(float start, float end, Vector3f ray){
        Vector3f startPoint = getPointOnRay(ray, start);
        Vector3f endPoint = getPointOnRay(ray,end);

        if(!isUnderGround(startPoint) && isUnderGround(endPoint)){
            return true;
        }else{
            return false;
        }

    }

    private Vector3f binarySearch(int count, float start, float finish, Vector3f ray){
        float middlePoint = start + ((finish-start)/2f);
        if(count>=RECURSION_COUNT){
            Vector3f endPoint = getPointOnRay(ray,middlePoint);
            System.out.println(endPoint.x);
            System.out.println(terrain.getX());
            if(endPoint.x<terrain.getX() || endPoint.x>terrain.getX()+terrain.width ||
                    endPoint.z < terrain.getZ() || endPoint.z > terrain.getZ()+terrain.height){
                return null;
            }

            return endPoint;
        }
        if(intersectionInRange(start,middlePoint,ray)){
            return binarySearch(count+1,start,middlePoint,ray);
        }else{
            return binarySearch(count+1,middlePoint,finish,ray);
        }


    }

    private boolean isUnderGround(Vector3f testPoint){
        if (testPoint.y<0)
            return true;
        return false;
    }



}
