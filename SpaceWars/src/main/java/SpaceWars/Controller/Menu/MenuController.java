package SpaceWars.Controller.Menu;

import SpaceWars.Game;
import SpaceWars.Controller.Controller;
import SpaceWars.Model.Menu.StartMenu;

import SpaceWars.GUI.GUI;

import java.io.IOException;

public class MenuController extends Controller<StartMenu> {
    public MenuController(StartMenu menuModel) {
        super(menuModel);
    }

    @Override
    public void step(Game game, GUI.PressedKey action, long time) throws IOException {
        switch (action) {
            case UP:
            case LEFT:
                getModel().previousOption();
                break;
            case DOWN:
            case RIGHT:
                getModel().nextOption();
                break;
            case ENTER:
                getModel().getSelectedButton().getAction().execute();
                break;
            case ESCAPE:
                getModel().getOptions().get(1).getAction().execute();
            default:
                break;
        }
    }
}
