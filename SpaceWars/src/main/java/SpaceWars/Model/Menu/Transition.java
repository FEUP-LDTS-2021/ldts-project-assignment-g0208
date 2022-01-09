package SpaceWars.Model.Menu;

import SpaceWars.Game;
import SpaceWars.Controller.Command.buttonCommand.ExitCommand;
import SpaceWars.GUI.FileReader;
import SpaceWars.Model.Position;

import java.io.IOException;

public class Transition extends Menu{
    public Transition(Game game) throws IOException {
        super(game);
        options.add(new Button(new FileReader().readFile("./src/main/resources/textFiles/transition/continuePlaying.txt"), new Position(20, 90), new Position(220, 60),  "#00cccc","#0062d8", "#000000"));
        options.add(new Button(new FileReader().readFile("./src/main/resources/textFiles/menu/exit.txt"), new Position(20, 50), new Position(220, 20), "#00cccc","#0062d8", "#000000"));
        options.get(1).setAction(new ExitCommand(game));
        options.get(0).setActive(true);
    }
}
