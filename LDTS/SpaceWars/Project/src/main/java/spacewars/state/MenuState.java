package spacewars.state;

import spacewars.Game;
import spacewars.controller.MenuController;
import spacewars.gui.GUI;
import spacewars.model.Position;
import spacewars.model.item.command.MenuButtonCommand;
import spacewars.model.element.button.Button;

import java.io.IOException;
import java.util.Arrays;

public class MenuState extends GameState {
    private MenuController menuController;

    public MenuState(Game game, GUI gui) throws IOException {
        super(game, Arrays.asList(
                new Button(new Position(Button.getButtonX(game.getWidth(), "PLAY"),10),
                            "PLAY",new MenuButtonCommand(new PlayingState(game,gui,1)),Arrays.asList("#FFFFFF", "#FFFF00")),
                new Button(new Position(Button.getButtonX(game.getWidth(), "EXIT"),17),
                            "EXIT", new MenuButtonCommand(null), Arrays.asList("#FFFFFF", "#FFFF00"))
        ));

        menuController = new MenuController(this, gui);
    }

    @Override
    public void start() {
        game.getMouseObserver().setListener(menuController);
        game.getKeyBoardObserver().setListener(menuController);
    }

    @Override
    public void step(Game game, long time) throws IOException {
        this.menuController.step();
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
