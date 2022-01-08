package SpaceWars.Controller.Command.buttonCommand;

import SpaceWars.Game;

public class ExitCommand extends ButtonCommand {

    public ExitCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.exit();
    }

}
