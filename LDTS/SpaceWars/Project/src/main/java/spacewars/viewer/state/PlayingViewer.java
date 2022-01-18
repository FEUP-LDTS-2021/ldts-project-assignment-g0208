package spacewars.viewer.state;

import spacewars.gui.GUI;
import spacewars.model.element.Player;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.List;

public class PlayingViewer extends StateViewer{
    private final Player player;

    public PlayingViewer(GUI gui, List<Button> buttons, Player player) {
        super(gui, buttons);
        this.player = player;
    }

    @Override
    public void draw() throws IOException {
        gui.drawInfo(player.getEnergy());
        gui.refresh();
    }
}
