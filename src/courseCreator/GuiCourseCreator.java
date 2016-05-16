package courseCreator;


import guis.GuiRenderer;
import guis.GuiTexture;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giogio on 16/05/16.
 */
public class GuiCourseCreator{
    List<GuiTexture> guis;
    Loader loader;
    GuiRenderer guiRenderer;

    public GuiCourseCreator(Loader loader){
        this.loader = loader;
        guis = new ArrayList<GuiTexture>();
        GuiButton button1 = new GuiButton("testButton","flower",loader,0.5f,0.5f,0.25f);
        guis.add(button1.getGuiTexture());

        guiRenderer = new GuiRenderer(loader);
    }

    public void render(){
        guiRenderer.render(guis);
    }
    public void cleanUp(){
        guiRenderer.cleanUp();
    }

    public void checkClick(){
        
    }



}
