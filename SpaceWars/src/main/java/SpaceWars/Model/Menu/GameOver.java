package SpaceWars.Model.Menu;

import SpaceWars.Game;
import SpaceWars.Controller.Command.ButtonCommand.ExitCommand;
import SpaceWars.Controller.Command.ButtonCommand.RestartCommand;
import SpaceWars.GUI.FileReader;
import SpaceWars.Model.Position;

import java.io.IOException;

public class GameOver extends Menu {

    public GameOver(Game game) throws IOException {
        super(game);
        options.add(new Button(new FileReader().readFile("./src/main/resources/textFiles/GameOver/Restart.txt"), new Position(20, 90), new Position(220,60), "#5500cc", "#00d8b4", "#000000"));
        options.add(new Button(new FileReader().readFile(".src/main/resources/textFile/Menu/Exit.txt"), new Position(20,50), new Position(220, 20), "#00cccc","#0062d8", "#000000"));
        options.get(1).setAction(new RestartCommand(game));
        options.get(2).setAction(new ExitCommand(game));
        options.get(0).setActive(true);
    }

}
