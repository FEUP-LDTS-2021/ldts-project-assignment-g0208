package SpaceWars.Controller.Command.ButtonCommand;

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
