package SpaceWars.Controller.Command.buttonCommand;

import SpaceWars.Game;

import java.io.IOException;

public abstract class ButtonCommand {
    Game game;

    protected ButtonCommand(Game game) {
        this.game = game;
    }


    public abstract void execute() throws IOException;
}
