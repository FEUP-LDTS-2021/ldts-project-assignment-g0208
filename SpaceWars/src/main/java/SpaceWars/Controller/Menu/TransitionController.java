package SpaceWars.Controller.Menu;

import SpaceWars.Controller.Controller;
import SpaceWars.GUI.GUI;
import SpaceWars.Game;
import SpaceWars.Model.Menu.Transition;

import java.io.IOException;

public class TransitionController extends Controller<Transition> {

    public TransitionController(Transition transitionModel) {
        super(transitionModel);
    }

    @Override
    public void step(Game game, GUI.PressedKey action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
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
