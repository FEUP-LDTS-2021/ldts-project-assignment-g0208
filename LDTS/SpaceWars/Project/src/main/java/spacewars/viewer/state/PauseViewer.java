package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.model.element.button.Button;
import spacewars.viewer.element.ButtonViewer;

import java.io.IOException;
import java.util.List;

public class PauseViewer extends StateViewer{

    public PauseViewer(GUI gui, List<Button> buttons) {
        super(gui, buttons);
    }

    public void draw() throws IOException {
        gui.clear();

        drawBackground("#000000");
        drawButtons(buttons, new ButtonViewer());

        gui.refresh();
    }


}
