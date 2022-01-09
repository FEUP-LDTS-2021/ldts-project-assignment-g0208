package SpaceWars.Controller.Command.ButtonCommand;

import SpaceWars.Game;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.State.StateMenu;

import java.io.IOException;


public class RestartCommand extends ButtonCommand {

    public RestartCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() throws IOException {
        game.getLevel();
        game.changeState(new StateMenu(new StartMenu(game)));
    }

}
