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
    GuiButton ballButton, putholeButton;
    long time = 0;


    public GuiCourseCreator(Loader loader, MousePicker pick){
        this.loader = loader;
        guis = new ArrayList<GuiTexture>();
        ballButton = new GuiButton("ballButton_on","ballButton_off",loader,-0.5f,0.85f,0.15f);
        putholeButton = new GuiButton("putholeButton_on","putholeButton_off",loader,0.5f,0.85f,0.15f);

        guis.add(ballButton.getGuiTexture());
        guis.add(putholeButton.getGuiTexture());
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

            long current_time = System.currentTimeMillis();

            if((current_time - time)>250) {

                if (Mouse.isButtonDown(0)) {

                    Vector2f mouseC = pick.getNormalCoord();
                    if ((mouseC.x >= -0.648) && (mouseC.y <= 0.897) && (mouseC.x <= -0.35) && (mouseC.y >= 0.783)) {
                        ballButton.swap();
                        putholeButton.deselect();
                    }else if ((mouseC.x >= (1-0.648)) && (mouseC.y <= 0.897) && (mouseC.x <= (1-0.35)) && (mouseC.y >= 0.783)) {
                        putholeButton.swap();
                        ballButton.deselect();
                    }else {
                        System.out.println(mouseC);
                    }
                    time = System.currentTimeMillis();

                }
            }


    }



}
