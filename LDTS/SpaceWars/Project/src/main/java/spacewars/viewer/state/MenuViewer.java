package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.viewer.element.ButtonViewer;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.List;

public class MenuViewer extends StateViewer{

    public MenuViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    @Override
    public void draw() throws IOException {
        gui.clear();
        drawBackground("#000000");
        drawButtons(buttons, new ButtonViewer());
        gui.refresh();
    }
}

