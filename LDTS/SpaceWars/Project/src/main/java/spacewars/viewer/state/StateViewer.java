package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.model.element.button.Button;
import spacewars.viewer.element.ElementViewer;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.io.IOException;
import java.util.List;

public abstract class StateViewer {
    protected final GUI gui;
    private final TextGraphics textGraphics;
    protected final List<Button> buttons;

    public StateViewer(GUI gui, List<Button> buttons) {
        this.gui = gui;
        this.textGraphics = gui.createTextGraphics();
        this.buttons = buttons;
    }

    public abstract void draw() throws IOException;

    protected void drawButtons(List<Button> buttons, ElementViewer<Button> viewer){
        for (int i = buttons.size()-1; i >= 0; i--) {
            viewer.drawElement(buttons.get(i), gui);
        }
    }


    protected void drawBackground(String color){
        gui.drawBackground(this.textGraphics, color);
    }
}
