package courseCreator;


import guis.GuiRenderer;
import guis.GuiTexture;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;
import toolbox.MousePicker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giogio on 16/05/16.
 */
public class GuiCourseCreator{
    List<GuiTexture> guis;
    Loader loader;
    GuiRenderer guiRenderer;
    MousePicker pick;
    GuiButton button1;


    public GuiCourseCreator(Loader loader, MousePicker pick){
        this.loader = loader;
        guis = new ArrayList<GuiTexture>();
        button1 = new GuiButton("testButton","flower",loader,0,0,0.25f);

        guis.add(button1.getGuiTexture());
        //button1.select();
        this.pick = pick;
        guiRenderer = new GuiRenderer(loader);
    }

    public void render(){
        guiRenderer.render(guis);
    }
    public void cleanUp(){
        guiRenderer.cleanUp();
    }

    public void checkClick(){


            Vector2f mouseC = pick.getNormalCoord();

            if(mouseC.x>=((button1.getGuiTexture().getPosition().x)-(button1.getWidth()/2))){
                System.out.println("Inside!");

            }else{
                System.out.println("Not");
            }



    }



}
