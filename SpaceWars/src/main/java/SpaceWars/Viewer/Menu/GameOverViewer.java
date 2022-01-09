package SpaceWars.Viewer.Menu;

import SpaceWars.GUI.FileReader;
import SpaceWars.GUI.GUI;
import SpaceWars.Model.Menu.GameOver;
import SpaceWars.Viewer.Viewer;

import java.io.File;
import java.io.IOException;


public class GameOverViewer extends Viewer<GameOver> {

    public GameOverViewer(GameOver gameOver) {
        super(gameOver);
    }

    public void drawElements(GUI gui) throws IOException {
        gui.drawbackground();
        gui.drawTitle(new FileReader().readFile(".src/main/resources/Files/GameOver.txt"), "#000000");
        for(int i=0; i < getModel().getNumberOptions(); i++) {
            gui.drawButton(getModel().getOptions().get(i));
        }

    }

}
