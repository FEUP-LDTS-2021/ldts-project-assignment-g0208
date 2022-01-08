package SpaceWars.Controller.Command.buttonCommand;

import java.io.IOException;

public class StartCommand extends ButtonCommand {

    public StartCommand (Game game) {
        super(game);
    }

    @Override
    public void execute() throws IOException {
        game.changeState(new StatePlay(game.getLevel(),1));
    }

}
