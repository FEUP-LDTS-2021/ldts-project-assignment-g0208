package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.element.button.Button;

public class ButtonViewer implements ElementViewer<Button> {

    @Override
    public void drawElement(Button element, GUI gui) {
        gui.drawButton(element.getPosition(), element.getTextPosition(), element.getText(),
                element.getBgColor(), element.getColor(), element.getWidth(), element.getHeight());
    }
}