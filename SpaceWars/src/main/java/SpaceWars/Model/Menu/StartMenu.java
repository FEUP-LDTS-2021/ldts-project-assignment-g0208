package SpaceWars.Model.Menu;

import SpaceWars.Game;
import SpaceWars.Controller.Command.buttonCommand.ExitCommand;
import SpaceWars.Controller.Command.buttonCommand.StartCommand;
import SpaceWars.GUI.FileReader;
import SpaceWars.Model.Position;

import java.io.IOException;

public class StartMenu extends Menu{
    public StartMenu(Game game) throws IOException {
        super(game);
        options.add(new Button(new FileReader().readFile("./src/main/resources/Files/menu/play.txt"),  new Position(20, 90), new Position(220, 60), "#0d0a0a","#595959", "#FFFFFF"));
        options.add(new Button(new FileReader().readFile("./src/main/resources/Files/menu/exit.txt"), new Position(20,  50),  new Position(220, 20),"#0d0a0a","#595959", "#FFFFFF"));
        options.get(0).setAction(new StartCommand(game));
        options.get(1).setAction(new ExitCommand(game));
        options.get(0).setActive(true);
    }

}
