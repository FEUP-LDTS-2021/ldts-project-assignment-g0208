package SpaceWars.State;

import SpaceWars.Controller.Controller;
import SpaceWars.Controller.Menu.MenuController;
import SpaceWars.Model.Menu.Menu;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.Viewer.Viewer;
import SpaceWars.Viewer.Menu.MenuViewer;

import java.io.IOException;


public class StateMenu extends State<StartMenu> {

    public StateMenu(StartMenu model) throws IOException {
        super(model);
    }

    @Override
    protected Viewer<StartMenu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<StartMenu> getController() {
        return new MenuController(getModel());
    }
}
