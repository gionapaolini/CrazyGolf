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
    private GuiButton ballButton, putholeButton, obstacle1Button,obstacle2Button,obstacle3Button,obstacle4Button;


    public GuiCourseCreator(Loader loader){
        this.loader = loader;
        guis = new ArrayList<GuiTexture>();
        ballButton = new GuiButton("ballButton_on","ballButton_off",loader,-0.75f,0.85f,0.15f);
        putholeButton = new GuiButton("putholeButton_on","putholeButton_off",loader,-0.45f,0.85f,0.15f);
        obstacle1Button = new GuiButton("obstacle1Button_on","obstacle1Button_off",loader,-0.15f,0.85f,0.15f);
        obstacle2Button = new GuiButton("obstacle1Button_on","obstacle1Button_off",loader,0.15f,0.85f,0.15f);
        obstacle3Button = new GuiButton("obstacle1Button_on","obstacle1Button_off",loader,0.45f,0.85f,0.15f);
        obstacle4Button = new GuiButton("obstacle1Button_on","obstacle1Button_off",loader,0.75f,0.85f,0.15f);

        guis.add(ballButton.getGuiTexture());
        guis.add(putholeButton.getGuiTexture());
        guis.add(obstacle1Button.getGuiTexture());
        guis.add(obstacle2Button.getGuiTexture());
        guis.add(obstacle3Button.getGuiTexture());
        guis.add(obstacle4Button.getGuiTexture());
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

    public GuiButton getObstacle1Button(){
        return obstacle1Button;
    }
    public GuiButton getObstacle2Button(){
        return obstacle2Button;
    }
    public GuiButton getObstacle3Button(){
        return obstacle3Button;
    }
    public GuiButton getObstacle4Button(){
        return obstacle4Button;
    }

}
