package engineTester;

import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;

/**
 * Created by giogio on 13/05/16.
 */
public class MainGameLoop {
    public static void main(String[] args){

        DisplayManager.createDisplay();

        while(!Display.isCloseRequested()){

            DisplayManager.updateDisplay();

        }

        DisplayManager.closeDisplay();

    }
}
