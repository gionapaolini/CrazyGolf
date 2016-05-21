package CourseCreator;


import guis.GuiRenderer;
import guis.GuiTexture;
import renderEngine.Loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giogio on 16/05/16.
 */
public class GuiCourseCreator{
    private List<GuiTexture> guis;
    private Loader loader;
    private GuiRenderer guiRenderer;
    private GuiButton ballButton, putholeButton;


    public GuiCourseCreator(Loader loader){
        this.loader = loader;
        guis = new ArrayList<GuiTexture>();
        ballButton = new GuiButton("ballButton_on","ballButton_off",loader,-0.5f,0.85f,0.15f);
        putholeButton = new GuiButton("putholeButton_on","putholeButton_off",loader,0.5f,0.85f,0.15f);

        guis.add(ballButton.getGuiTexture());
        guis.add(putholeButton.getGuiTexture());
        guiRenderer = new GuiRenderer(loader);
    }

    public void render(){
        guiRenderer.render(guis);
    }
    public void cleanUp(){
        guiRenderer.cleanUp();
    }

    public GuiButton getBallButton() {
        return ballButton;
    }

    public GuiButton getPutholeButton() {
        return putholeButton;
    }
}
