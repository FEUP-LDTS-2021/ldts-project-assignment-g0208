package SpaceWars.Controller.Command.ButtonCommand;

import SpaceWars.Game;
import SpaceWars.Model.Menu.StartMenu;
import SpaceWars.State.StateMenu;

import java.io.IOException;

public class StartCommand extends ButtonCommand {

    public StartCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() throws IOException {
       /* game.changeState(new StatePlay(game.getLevel(), 1));*/
        game.changeState(new StateMenu(new StartMenu(game)));
    }

}
