package SpaceWars.Viewer.Menu;

import SpaceWars.GUI.FileReader;
import SpaceWars.GUI.GUI;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.Viewer.Viewer;


import java.io.IOException;

public class MenuViewer extends Viewer<StartMenu> {

    public MenuViewer(StartMenu menu) {
        super(menu);
    }

@   Override
    public void drawElements(GUI gui) throws IOException {
        gui.drawMenuBackground();
        gui.drawTitle(new FileReader().readFile("./src/main/resources/Files/menu/title.txt"), "#adadad");

        for(int i = 0; i < getModel().getNumberOptions() ; i++){
            gui.drawButton(getModel().getOptions().get(i));
        }
    }
}
