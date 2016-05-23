package terrains;

import models.RawModel;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.LoadableImageData;
import renderEngine.Loader;
import textures.ModelTexture;

/**
 * Created by giogio on 15/05/16.
 */
public class Terrain {

    public float width = 400;
    public float height = 400;
    private static final int VERTEX_COUNT = 128;

    private float x;
    private float z;
    private RawModel model;
    private ModelTexture texture;
    private Vector2f putholePosition;
    private Loader loader;

    public Terrain(int gridX, int gridZ, float width, float height,Loader loader, ModelTexture texture){
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.x = gridX;
        this.z = gridZ;
        this.loader = loader;
        model = generateTerrain(loader);
    }

    public void changeSize(float width, float height, Loader loader){
        this.width = width;
        this.height = height;
        x = -width/2;
        z = -height/2;
        model = generateTerrain(loader);
    }

    private RawModel generateTerrain(Loader loader){
        int count = VERTEX_COUNT * VERTEX_COUNT;
        float[] vertices = new float[count * 3];
        float[] normals = new float[count * 3];
        float[] textureCoords = new float[count*2];
        int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT-1)];
        int vertexPointer = 0;
        for(int i=0;i<VERTEX_COUNT;i++){
            for(int j=0;j<VERTEX_COUNT;j++){
                vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * width;
                if(i == 0 || j==0 || i==VERTEX_COUNT-1 || j == VERTEX_COUNT-1)
                    vertices[vertexPointer*3+1] = 3;
                else
                    vertices[vertexPointer*3+1] = 0;
                vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * height;
                if(i==0){
                    normals[vertexPointer*3] = 0;
                    normals[vertexPointer*3+1] = 0;
                    normals[vertexPointer*3+2] = 1;
                }else if(j==0) {
                    normals[vertexPointer * 3] = 1;
                    normals[vertexPointer * 3 + 1] = 0;
                    normals[vertexPointer * 3 + 2] = 0;
                }else if(i==VERTEX_COUNT-1) {
                    normals[vertexPointer * 3] = 0;
                    normals[vertexPointer * 3 + 1] = 0;
                    normals[vertexPointer * 3 + 2] = -1;
                }else if(j==VERTEX_COUNT-1) {
                    normals[vertexPointer * 3] = -1;
                    normals[vertexPointer * 3 + 1] = 0;
                    normals[vertexPointer * 3 + 2] = 0;
                }else{
                    normals[vertexPointer * 3] = 0;
                    normals[vertexPointer * 3 + 1] = 1;
                    normals[vertexPointer * 3 + 2] = 0;
                }
                textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
                textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
                vertexPointer++;
            }
        }


        int pointer = 0;
        for(int gz=0;gz<VERTEX_COUNT-1;gz++){
            for(int gx=0;gx<VERTEX_COUNT-1;gx++){
                int topLeft = (gz*VERTEX_COUNT)+gx;
                int topRight = topLeft + 1;
                int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
                int bottomRight = bottomLeft + 1;
                indices[pointer++] = topLeft;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = topRight;
                indices[pointer++] = topRight;
                indices[pointer++] = bottomLeft;
                indices[pointer++] = bottomRight;
            }
        }
        return loader.loadToVAO(vertices, textureCoords, normals, indices);
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    public RawModel getModel() {
        return model;
    }

    public ModelTexture getTexture() {
        return texture;
    }

    public Vector2f getPutholePosition(){
        return putholePosition;
    }

    public void setPutholePosition(Vector2f position){
        putholePosition = position;
        model = generateTerrain(loader);
    }
}
