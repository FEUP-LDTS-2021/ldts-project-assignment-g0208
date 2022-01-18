package spacewars.viewer.element;

import spacewars.gui.GUI;
import spacewars.model.element.Player;

public class PlayerViewer implements ElementViewer<Player> {

    @Override
    public void drawElement(Player element, GUI gui) {
        gui.drawPlayer(element.getPosition(), element.getColor());
    }
}

