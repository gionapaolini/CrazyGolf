package CourseCreator;

import guis.GuiTexture;
import org.lwjgl.util.vector.Vector2f;
import renderEngine.Loader;

/**
 * Created by giogio on 21/05/16.
 */
public class GuiButton {
        private int textureSelected;
        private int textureDeselected;
        private GuiTexture currentText;
        private Vector2f position;
        private Vector2f scale;
        private boolean selected;

        public GuiButton(String selected, String notSelected, Loader loader, float x, float y, float scale){
            position = new Vector2f(x,y);
            this.scale = new Vector2f(scale,scale);
            textureSelected = loader.loadTexture(selected);
            textureDeselected = loader.loadTexture(notSelected);
            currentText = new GuiTexture(textureDeselected,position,this.scale);
            this.selected=false;

        }

        public void swap(){
            selected = !selected;
            if(selected){
                select();
            }else{
                deselect();
            }
        }

        public void select(){
            currentText.setTexture(textureSelected);
            selected = true;
        }
        public void deselect(){
            currentText.setTexture(textureDeselected);
            selected = false;
        }

        public boolean isSelected(){
            return selected;
        }
        public GuiTexture getGuiTexture(){
            return currentText;
        }

}

